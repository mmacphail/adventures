package com.macphail.adventures.business.game.model.event;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class PlayerLeftAdventure extends Event {

    public PlayerLeftAdventure(long adventureId, long playerId) {
        super(adventureId);
        this.playerId = playerId;
        this.eventType = EventType.PLAYER_LEFT_ADVENTURE;
    }

    public PlayerLeftAdventure(){ }

    @NotNull
    private long playerId;

    public long getPlayerId() {
        return playerId;
    }
}
