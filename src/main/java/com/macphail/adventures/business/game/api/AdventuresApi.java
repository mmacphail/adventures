package com.macphail.adventures.business.game.api;

import com.macphail.adventures.business.game.model.adventure.Adventure;

public interface AdventuresApi {

    Adventure createAdventure(String creatorUserId, String title, String description, String gameMasterName,
                              int maxNumberOfParticipants, int progressionPoints);

}
