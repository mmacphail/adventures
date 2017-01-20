package com.macphail.adventures.business.game.model.event;

import com.macphail.adventures.business.game.repository.EventRepository;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Stateless
public class AdventureEventsSubscriber {

    @Inject
    EventRepository repository;

    public void onAdventureEnded(@Observes AdventureEnded event) {
        repository.persist(event);
    }

    public void onAdventureStarted(@Observes AdventureStarted event) {
        repository.persist(event);
    }

    public void onAdventureCancelled(@Observes AdventureCancelled event) {
        repository.persist(event);
    }

    public void onPlayerReacted(@Observes PlayerReacted event) {
        repository.persist(event);
    }

    public void onHeroicDeedHappened(@Observes HeroicDeedHappened event) {
        repository.persist(event);
    }

    public void onNarrativeWasWritten(@Observes NarrativeWasWritten event) {
        repository.persist(event);
    }

    public void onPlayerJoinedAdventure(@Observes PlayerJoinedAdventure event) {
        repository.persist(event);
    }

    public void onPlayerLeftAdventure(@Observes PlayerLeftAdventure event) {
        repository.persist(event);
    }

    public void onProgressReached(@Observes ProgressReached event) {
        repository.persist(event);
    }
}
