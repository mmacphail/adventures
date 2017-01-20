package com.macphail.adventures.business.game.model.adventure;

import com.macphail.adventures.business.game.model.adventure.exceptions.NotThisPlayerTimeToSpeakException;
import com.macphail.adventures.business.game.model.adventure.exceptions.PartyIsFullException;
import com.macphail.adventures.business.game.model.adventure.exceptions.PlayerAlreadyInAdventureException;
import com.macphail.adventures.business.game.model.adventure.exceptions.PlayerNotInAdventureException;
import com.macphail.adventures.business.game.model.event.DomainEventPublisher;
import com.macphail.adventures.business.game.service.exceptions.AdventureAlreadyEndedException;
import com.macphail.adventures.business.game.service.exceptions.AdventureAlreadyStartedException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.macphail.adventures.business.game.model.adventure.CommandsValidator.assureThat;

@Entity
@NamedQuery(name = Adventure.findAll, query = "SELECT a FROM Adventure a ORDER BY a.createdDate, a.startDate DESC")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Adventure {

    public static final int MAX_OPENED_ADVENTURES = 10;

    public static Adventure create(String creatorUserId,
                                   String title,
                                   String description,
                                   String gameMasterName,
                                   int maxNumberOfParticipants,
                                   int progressionPoints) {
        Adventure adventure = new Adventure();
        adventure.createdDate = LocalDateTime.now();
        adventure.setGameMaster(GameMaster.createAdventure(creatorUserId, adventure, gameMasterName, progressionPoints));
        adventure.title = title;
        adventure.description = description;
        adventure.currentNumberOfPlayer = 0;
        adventure.maxNumberOfPlayers = maxNumberOfParticipants;
        return adventure;
    }

    static final String PREFIX = "game.model.Adventure";
    public static final String findAll = PREFIX + ".findAll";

    @Id
    @GeneratedValue
    private long id;

    @Transient
    private Optional<DomainEventPublisher> eventPublisher = Optional.empty();

    @OneToOne(mappedBy = "adventure", optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private GameMaster gameMaster;

    @OneToMany(mappedBy = "adventure", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Player> players = new ArrayList<>();

    @NotNull
    private String title;

    @Size(max = 1000)
    @NotNull
    private String description;

    private int currentNumberOfPlayer;

    private int maxNumberOfPlayers;

    private long lastPlayerWhoReactedId;

    private LocalDateTime createdDate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private EndReason endReason;

    @Version
    private long version;

    public Adventure() {
    }

    public void start() {
        assureThat(this.isNotStarted(), AdventureAlreadyStartedException::new);
        assureThat(this.isNotDone(), AdventureAlreadyEndedException::new);

        this.startDate = LocalDateTime.now();

        this.eventPublisher.ifPresent(e -> e.publishAdventureStartedEvent(this.id));
    }

    public void playerJoins(Player player) {
        assureThat(this.isNotStarted(), AdventureAlreadyStartedException::new);
        assureThat(this.isNotDone(), AdventureAlreadyEndedException::new);
        assureThat(!this.isPartyFull(), PartyIsFullException::new);
        assureThat(!isUserInThisAdventure(player.getUserId()), PlayerAlreadyInAdventureException::new);

        this.currentNumberOfPlayer += 1;
        this.players.add(player);

        eventPublisher.ifPresent(d -> d.publishPlayerJoinedAdventureEvent(this.id, player.getUserId()));

        if(isPartyFull()) {
            this.start();
        }
    }

    public void cancel(EndReason reason) {
        assureThat(this.isNotDone(), AdventureAlreadyEndedException::new);

        this.endDate = LocalDateTime.now();
        this.endReason = reason;

        eventPublisher.ifPresent(e -> e.publishAdventureCancelledEvent(this.id, this.endReason));
    }

    public void end() {
        assureThat(this.isStarted(), AdventureAlreadyStartedException::new);
        assureThat(this.isNotDone(), AdventureAlreadyEndedException::new);

        this.endDate = LocalDateTime.now();
        this.endReason = EndReason.ALL_PROGRESSION_POINTS_SPENT;

        eventPublisher.ifPresent(e -> e.publishAdventureEndedEvent(this.id));
    }

    public void notifyPlayerLeft(Player player) {
        assureThat(this.isNotDone(), AdventureAlreadyEndedException::new);
        assureThat(isUserInThisAdventure(player.getUserId()), PlayerNotInAdventureException::new);

        this.currentNumberOfPlayer -= 1;

        if(this.hasNoPlayersLeft() && this.isStarted()) {
            this.cancel(EndReason.ALL_PLAYERS_LEFT);
        }
    }

    public void notifyPlayerReacted(long playerId) {
        if(isLastPersonWhoReacted(playerId)) {
            throw new NotThisPlayerTimeToSpeakException();
        }

        this.lastPlayerWhoReactedId = playerId;
    }

    @Transient
    public boolean isNotDone() {
        return endDate == null;
    }

    @Transient
    public boolean isDone() {
        return endDate != null;
    }

    @Transient
    public boolean isCancelledBecauseAllPlayersLeft() {
        return isDone() && this.endReason == EndReason.ALL_PLAYERS_LEFT;
    }

    @Transient
    public boolean isNotStarted() {
        return !isStarted();
    }

    @Transient
    public boolean isStarted() {
        return startDate != null;
    }

    public long getId() {
        return id;
    }

    public GameMaster getGameMaster() {
        return gameMaster;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> getActivePlayers() {
        return getPlayers().stream()
                .filter(Player::isPlayerActive)
                .collect(Collectors.toList());
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

    public long getLastPlayerWhoReactedId() {
        return lastPlayerWhoReactedId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public EndReason getEndReason() {
        return endReason;
    }

    public void setGameMaster(GameMaster gameMaster) {
        this.gameMaster = gameMaster;
    }

    @Transient
    public boolean isPartyFull() {
        return currentNumberOfPlayer == maxNumberOfPlayers;
    }

    @Transient
    public boolean isUserInThisAdventure(String userId) {
        return this.getActivePlayers().stream()
                .anyMatch(p -> p.getUserId().equals(userId));
    }

    public Optional<Player> playerFor(long playerId) {
        return players.stream()
                .filter(p -> p.getId() == playerId)
                .findFirst();
    }

    @Transient
    private boolean isLastPersonWhoReacted(long playerId) {
        if(lastPlayerWhoReactedId == playerId) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasNoPlayersLeft() {
        return currentNumberOfPlayer == 0;
    }

    public void allowAnyoneToReact() {
        this.lastPlayerWhoReactedId = -1;
    }

    @Transient
    public void setDomainEventPublisher(DomainEventPublisher DomainEventPublisher) {
        this.eventPublisher = Optional.of(DomainEventPublisher);
    }

    @Override
    public String toString() {
        return "Adventure{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Optional<DomainEventPublisher> getDomainEventPublisher() {
        return eventPublisher;
    }
}
