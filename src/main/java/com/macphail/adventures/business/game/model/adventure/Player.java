package com.macphail.adventures.business.game.model.adventure;

import com.macphail.adventures.business.game.model.adventure.exceptions.NotEnoughHeroicPointsLeftException;
import com.macphail.adventures.business.game.model.adventure.exceptions.PlayerAlreadyLeftAdventureException;
import com.macphail.adventures.business.game.model.event.DomainEventPublisher;
import com.macphail.adventures.business.game.model.capability.Capability;
import com.macphail.adventures.business.game.model.capability.ConditionalCapability;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.macphail.adventures.business.game.model.adventure.CommandsValidator.assureThat;

@Entity
public class Player {
    public static Player joinAdventure(String userId, Adventure adventure, String name, String description) {
        Player player = new Player();
        player.userId = userId;
        player.setAdventure(adventure);
        player.name = name;
        player.description = description;
        player.dateJoined = LocalDateTime.now();
        player.heroicPoints = 1;

        adventure.playerJoins(player);
        return player;
    }

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String userId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Adventure adventure;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private int heroicPoints;

    @NotNull
    private LocalDateTime dateJoined;

    private LocalDateTime dateLeft;

    @Version
    private long version;

    public Player() {
    }

    public void leaveAdventure() {
        assureThat(!this.hasPlayerLeft(), PlayerAlreadyLeftAdventureException::new);

        this.adventure.notifyPlayerLeft(this);
        this.dateLeft = LocalDateTime.now();

        this.eventPublisher().ifPresent(e -> e.publishPlayerLeftAdventureEvent(adventure.getId(), this.id));
    }

    public void react(String content) {
        this.adventure.notifyPlayerReacted(id);

        eventPublisher().ifPresent(e -> e.publishPlayerReactedEvent(adventure.getId(), id, content));
    }

    public void doHeroicDeeds(String description) {
        assureThat(this.hasHeroicPointsLeft(), NotEnoughHeroicPointsLeftException::new);

        this.spendAHeroicPoint();
        this.adventure.allowAnyoneToReact();
        eventPublisher().ifPresent(e -> e.publishHeroicDeedHappenedEvent(adventure.getId(), this.id, description));
    }

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHeroicPoints() {
        return heroicPoints;
    }

    public LocalDateTime getDateJoined() {
        return dateJoined;
    }

    public LocalDateTime getDateLeft() {
        return dateLeft;
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }

    @Transient
    public boolean isPlayerActive() {
        return dateLeft == null;
    }

    @Transient
    public boolean hasPlayerLeft() {
        return dateLeft != null;
    }

    @Transient
    public boolean hasHeroicPointsLeft() {
        return heroicPoints > 0;
    }

    private void spendAHeroicPoint() {
        this.heroicPoints -= 1;
    }

    @Transient
    public Capability canSpeak() {
        return new ConditionalCapability("Express yourself !"
                ,    this::adventureIsStarted, "Wait for the adventure to start")
                .and(this::adventureIsNotDone, "This adventure has already ended")
                .and(this::lastPlayerWhoSpoke, "You must wait for the game master or another player");
    }

    @Transient
    public Capability canDoHeroicDeed() {
        return new ConditionalCapability("Make yourself memorable by doing something heroic"
                ,    this::adventureIsStarted, "Wait for the adventure to start")
                .and(this::adventureIsNotDone, "This adventure has already ended")
                .and(this::hasHeroicPointsLeft, "You've already done something heroic");
    }

    @Transient
    public Capability canLeaveAdventure() {
        return new ConditionalCapability("Leave this adventure and never come back"
                ,() -> !this.hasPlayerLeft(), "You already left this adventure");
    }

    private boolean lastPlayerWhoSpoke() {
        return this.adventure.getLastPlayerWhoReactedId() != this.id;
    }

    private boolean adventureIsStarted() {
        return this.adventure.isStarted();
    }

    private boolean adventureIsNotDone() {
        return this.adventure.isNotDone();
    }

    @Transient
    public Optional<DomainEventPublisher> eventPublisher() {
        return adventure.getDomainEventPublisher();
    }
    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
