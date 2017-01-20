package com.macphail.adventures.business.game.service;

import com.macphail.adventures.business.game.api.AdventuresApi;
import com.macphail.adventures.business.game.model.adventure.Adventure;
import com.macphail.adventures.business.game.repository.AdventureRepository;

import javax.inject.Inject;
import java.io.Serializable;

public class AdventuresService implements AdventuresApi, Serializable {

    @Inject
    private AdventureRepository adventureRepository;

    @Override
    public Adventure createAdventure(String creatorUserId, String title, String description, String gameMasterName,
                                     int maxNumberOfParticipants, int progressionPoints) {
        Adventure adventure = Adventure.create(creatorUserId, title, description, gameMasterName,
                maxNumberOfParticipants, progressionPoints);

        return adventureRepository.create(adventure);
    }

}
