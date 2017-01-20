package com.macphail.adventures.business.game.model.adventure;

import com.macphail.adventures.business.game.model.adventure.exceptions.AdventureNotStartedException;
import com.macphail.adventures.business.game.model.adventure.exceptions.NoProgressionPointsLeftException;
import com.macphail.adventures.business.game.model.capability.Capability;
import com.macphail.adventures.business.game.model.capability.ConditionalCapability;
import com.macphail.adventures.business.game.model.event.DomainEventPublisher;
import com.macphail.adventures.business.game.service.exceptions.AdventureAlreadyEndedException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.macphail.adventures.business.game.model.adventure.CommandsValidator.assureThat;

@NamedQueries ({
    @NamedQuery(name = GameMaster.findForAdventure,
            query = "SELECT gm FROM GameMaster gm WHERE gm.adventure = :adventure"),
    @NamedQuery(name = GameMaster.isGameMaster,
            query = "SELECT gm FROM GameMaster gm WHERE gm.userId = :userId")
})
@Entity
public class GameMaster {

    public static GameMaster createAdventure(String creatorUserId, Adventure adventure, String gameMasterName, int progressionPoints) {
        GameMaster gameMaster = new GameMaster();
        gameMaster.userId = creatorUserId;
        gameMaster.setAdventure(adventure);
        gameMaster.name = gameMasterName;
        gameMaster.progressionPoints = progressionPoints;
        return gameMaster;
    }

    static final String PREFIX = "game.model.GameMaster";
    public static final String findForAdventure = PREFIX + ".findForAdventure";
    public static final String isGameMaster = PREFIX + ".isGameMaster";

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(optional = false)
    private Adventure adventure;

    @NotNull
    private String userId;

    private String name;

    private int progressionPoints;

    @Version
    private long version;

    public GameMaster(){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public int getProgressionPoints() {
        return progressionPoints;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }

    public String getUserId() {
        return userId;
    }

    public void writeNarrative(String content) {
        assureThat(this.adventure.isNotDone(), AdventureAlreadyEndedException::new);

        this.adventure.allowAnyoneToReact();
        eventPublisher().ifPresent(e -> e.publishNarrativeWasWrittenEvent(adventure.getId(), content));
    }

    public void progressReached(String description) {
        assureThat(this.adventure.isStarted(), AdventureNotStartedException::new);
        assureThat(this.adventure.isNotDone(), AdventureAlreadyEndedException::new);
        assureThat(this.hasProgressionPointsLeft(), NoProgressionPointsLeftException::new);

        this.spendAProgressionPoint();
        this.adventure.allowAnyoneToReact();

        if(!hasProgressionPointsLeft()) {
            adventure.end();
        }

        eventPublisher().ifPresent(e -> e.publishProcessReachedEvent(id, description));
    }

    public void cancelAdventure() {
        adventure.cancel(EndReason.GAMEMASTER_CANCELLED);
    }

    @Transient
    public boolean hasProgressionPointsLeft() {
        return progressionPoints > 0;
    }

    private void spendAProgressionPoint() {
        this.progressionPoints -= 1;
    }

    public Capability canWriteNarrative() {
        return new ConditionalCapability("Describe what the players see",
                this::adventureIsNotDone, "This adventure has already ended");
    }

    public Capability canSpendProgressPoints() {
        return new ConditionalCapability("When the adventure reaches a high points, reward the players"
                ,    this::adventureIsStarted, "Wait for the adventure to start")
                .and(this::adventureIsNotDone, "This adventure has already ended")
                .and(this::hasProgressionPointsLeft, "You don't have any more progression points to spend");
    }

    public Capability canCancelAdventure() {
        return new ConditionalCapability("If you feel like you had enough, permanently cancel this adventure",
                this::adventureIsNotDone, "This adventure has already ended");
    }

    private boolean adventureIsStarted() {
        return this.adventure.isStarted();
    }

    private Boolean adventureIsNotDone() {
        return adventure.isNotDone();
    }

    @Transient
    public Optional<DomainEventPublisher> eventPublisher() {
        return adventure.getDomainEventPublisher();
    }
}
