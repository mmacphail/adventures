package com.macphail.adventures.business.game.api;

public interface AdventureApi {

    void playerJoinsAdventure(long adventureId, String userId, String playerName, String playerDescription);

    void gameMasterWritesNarrative(long adventureId, String content);

    void playersReact(long adventureId, long playerId, String content);

    void progressAdventure(long adventureId, String description);

    void playerDoesHeroicDeed(long adventureId, long playerId, String description);

    void gameMasterCancelsAdventure(long adventureId);

    void playerLeavesAdventure(long adventureId, long playerId);
}
