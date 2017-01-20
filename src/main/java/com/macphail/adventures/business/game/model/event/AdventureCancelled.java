package com.macphail.adventures.business.game.model.event;

import com.macphail.adventures.business.game.model.adventure.EndReason;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
public class AdventureCancelled extends Event {

    public AdventureCancelled() {
    }

    public AdventureCancelled(long adventureId, EndReason reason) {
        super(adventureId);
        this.eventType = EventType.ADVENTURE_CANCELLED;
        this.reason = reason;
    }

    @NotNull
    @Enumerated
    private EndReason reason;

    public EndReason getReason() {
        return reason;
    }
}
