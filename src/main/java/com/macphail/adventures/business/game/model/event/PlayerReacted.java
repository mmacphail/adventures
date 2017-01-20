package com.macphail.adventures.business.game.model.event;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class PlayerReacted extends Event {

    public PlayerReacted(){}

    public PlayerReacted(long adventureId, long playerId, String content) {
        super(adventureId);
        this.content = content;
        this.eventType = EventType.PLAYER_REACTED;
        this.playerId = playerId;
    }

    private long playerId;

    @Size(max = 3000)
    private String content;

    public long getPlayerId() {
        return playerId;
    }

    public String getContent() {
        return content;
    }
}
