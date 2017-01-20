package com.macphail.adventures.business.game.model.event;

import javax.persistence.Entity;

@Entity
public class AdventureStarted extends Event {

    public AdventureStarted() {
    }

    public AdventureStarted(long adventureId) {
        super(adventureId);
        this.eventType = EventType.ADVENTURE_STARTED;
    }
}
