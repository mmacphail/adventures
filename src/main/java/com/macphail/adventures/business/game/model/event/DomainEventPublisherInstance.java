package com.macphail.adventures.business.game.model.event;

import com.macphail.adventures.business.game.model.adventure.EndReason;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DomainEventPublisherInstance implements DomainEventPublisher {

    @Inject
    private javax.enterprise.event.Event<AdventureEnded> adventureEnded;

    @Override
    public void publishAdventureEndedEvent(long adventureId) {
        adventureEnded.fire(new AdventureEnded(adventureId));
    }

    @Inject
    private javax.enterprise.event.Event<AdventureStarted> adventureStarted;

    @Override
    public void publishAdventureStartedEvent(long adventureId) {
        adventureStarted.fire(new AdventureStarted(adventureId));
    }

    @Inject
    private javax.enterprise.event.Event<AdventureCancelled> adventureCancelled;

    @Override
    public void publishAdventureCancelledEvent(long adventureId, EndReason reason) {
        adventureCancelled.fire(new AdventureCancelled(adventureId, reason));
    }

    @Inject
    private javax.enterprise.event.Event<HeroicDeedHappened> heroicDeedHappened;

    @Override
    public void publishHeroicDeedHappenedEvent(long adventureId, long playerId, String description) {
        heroicDeedHappened.fire(new HeroicDeedHappened(adventureId, playerId, description));
    }

    @Inject
    private javax.enterprise.event.Event<NarrativeWasWritten> narrativeWasWritten;

    @Override
    public void publishNarrativeWasWrittenEvent(long adventureId, String content) {
        narrativeWasWritten.fire(new NarrativeWasWritten(adventureId, content));
    }

    @Inject
    private javax.enterprise.event.Event<PlayerJoinedAdventure> playerJoinedAdventure;

    @Override
    public void publishPlayerJoinedAdventureEvent(long adventureId, String userId) {
        playerJoinedAdventure.fire(new PlayerJoinedAdventure(adventureId, userId));
    }

    @Inject
    private javax.enterprise.event.Event<PlayerLeftAdventure> playerLeftAdventure;

    @Override
    public void publishPlayerLeftAdventureEvent(long adventureId, long playerId) {
        playerLeftAdventure.fire(new PlayerLeftAdventure(adventureId, playerId));
    }

    @Inject
    private javax.enterprise.event.Event<PlayerReacted> playerReacted;

    @Override
    public void publishPlayerReactedEvent(long adventureId, long playerId, String content) {
        playerReacted.fire(new PlayerReacted(adventureId, playerId, content));
    }

    @Inject
    private javax.enterprise.event.Event<ProgressReached> progressReached;

    @Override
    public void publishProcessReachedEvent(long adventureId, String description) {
        progressReached.fire(new ProgressReached(adventureId, description));
    }
}
