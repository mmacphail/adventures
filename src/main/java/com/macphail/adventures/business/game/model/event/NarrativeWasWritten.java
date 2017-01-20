package com.macphail.adventures.business.game.model.event;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class NarrativeWasWritten extends Event {

    public NarrativeWasWritten() {}

    public NarrativeWasWritten(long adventureId, String content) {
        super(adventureId);
        this.eventType = EventType.NARRATIVE_WAS_WRITTEN;
        this.content = content;
    }

    @Size(max = 3000)
    private String content;

    public String getContent() {
        return content;
    }
}
