package com.macphail.adventures.business.game.model.event;

import javax.persistence.Entity;

@Entity
public class AdventureEnded extends Event {
    public static AdventureEnded createEvent(long adventureId) {
        return new AdventureEnded(adventureId);
    }

    public AdventureEnded() {
    }

    public AdventureEnded(long adventureId) {
        super(adventureId);
        this.eventType = EventType.ADVENTURE_ENDED;
    }

}
