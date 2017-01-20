package com.macphail.adventures.business.game.model.event;

import com.macphail.adventures.business.LocalDateTimeXmlAdapter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@NamedQuery(name = Event.findForAdventure,
        query = "SELECT e FROM Event e WHERE e.adventureId = :id ORDER BY e.date ASC")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Event {

    public Event() {
    }

    public Event(long adventureId) {
        this.adventureId = adventureId;
        this.date = LocalDateTime.now();
    }

    static final String PREFIX = "game.model.Event";
    public static final String findForAdventure = PREFIX + ".findForAdventure";

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private long adventureId;

    @NotNull
    @Enumerated(EnumType.STRING)
    protected EventType eventType;

    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    protected LocalDateTime date;

    @Version
    private long version;

    public long getId() {
        return id;
    }

    public long getAdventureId() {
        return adventureId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public long getVersion() {
        return version;
    }

    public NarrativeWasWritten asNarrative() {
        return (NarrativeWasWritten) this;
    }

    public PlayerReacted asInterjection() {
        return (PlayerReacted) this;
    }

    public PlayerJoinedAdventure asPlayerJoined() {
        return (PlayerJoinedAdventure) this;
    }

    public AdventureStarted asAdventureStarted() {
        return (AdventureStarted) this;
    }

    public AdventureCancelled asAdventureCancelled() {
        return (AdventureCancelled) this;
    }

    public PlayerLeftAdventure asPlayerLeftAdventure() {
        return (PlayerLeftAdventure) this;
    }

    public AdventureEnded asAdventureEnded() {
        return (AdventureEnded) this;
    }

    public ProgressReached asProgress() {
        return (ProgressReached) this;
    }

    public HeroicDeedHappened asHeroicDeed() {
        return (HeroicDeedHappened) this;
    }
}
