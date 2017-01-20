package com.macphail.adventures.business.game.model.event;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class HeroicDeedHappened extends Event {

    public HeroicDeedHappened() {
    }

    public HeroicDeedHappened(long adventureId, long playerId, String description) {
        super(adventureId);
        this.playerId = playerId;
        this.description = description;
        this.eventType = EventType.HEROIC_DEED;
    }

    @NotNull
    private long playerId;

    @NotNull
    private String description;

    public long getPlayerId() {
        return playerId;
    }

    public String getDescription() {
        return description;
    }
}
