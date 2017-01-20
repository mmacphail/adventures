package com.macphail.adventures.business.game.model.event;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class PlayerJoinedAdventure extends Event {

    public PlayerJoinedAdventure() {
    }

    public PlayerJoinedAdventure(long adventureId, String userId) {
        super(adventureId);
        this.eventType = EventType.PLAYER_JOINED_ADVENTURE;
        this.userId = userId;
    }

    @NotNull
    private String userId;

    public String getUserId() {
        return userId;
    }
}
