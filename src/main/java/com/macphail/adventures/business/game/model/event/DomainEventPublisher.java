package com.macphail.adventures.business.game.model.event;

import com.macphail.adventures.business.game.model.adventure.EndReason;

public interface DomainEventPublisher {
    void publishAdventureCancelledEvent(long adventureId, EndReason reason);

    void publishAdventureEndedEvent(long adventureId);

    void publishAdventureStartedEvent(long adventureId);

    void publishHeroicDeedHappenedEvent(long adventureId, long playerId, String description);

    void publishNarrativeWasWrittenEvent(long adventureId, String content);

    void publishPlayerJoinedAdventureEvent(long adventureId, String userId);

    void publishPlayerLeftAdventureEvent(long adventureId, long playerId);

    void publishPlayerReactedEvent(long adventureId, long playerId, String content);

    void publishProcessReachedEvent(long adventureId, String description);
}
