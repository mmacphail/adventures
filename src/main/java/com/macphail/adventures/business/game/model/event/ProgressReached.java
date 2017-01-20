package com.macphail.adventures.business.game.model.event;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class ProgressReached extends Event {

    public ProgressReached() {
    }

    public ProgressReached(long adventureId, String description) {
        super(adventureId);
        this.description = description;
        this.eventType = EventType.PROGRESS;
    }

    @NotNull
    private String description;

    public String getDescription() {
        return description;
    }
}
