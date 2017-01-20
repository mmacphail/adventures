package com.macphail.adventures.business.game.service;


import com.macphail.adventures.business.game.api.AdventureApi;
import com.macphail.adventures.business.game.model.adventure.Adventure;
import com.macphail.adventures.business.game.model.adventure.Player;
import com.macphail.adventures.business.game.model.adventure.exceptions.PlayerNotInAdventureException;
import com.macphail.adventures.business.game.model.event.DomainEventPublisher;
import com.macphail.adventures.business.game.repository.AdventureRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

@Stateless
public class AdventureService implements AdventureApi, Serializable {

    @Inject
    private AdventureRepository adventureRepository;

    @Inject
    DomainEventPublisher eventPublisher;

    @Override
    public void playerJoinsAdventure(long adventureId, String userId, String playerName, String playerDescription) {
        Adventure adventure = findAdventure(adventureId);

        Player.joinAdventure(userId, adventure, playerName, playerDescription);
    }

    @Override
    public void gameMasterWritesNarrative(long adventureId, String content) {
        Adventure adventure = findAdventure(adventureId);

        adventure.getGameMaster().writeNarrative(content);
    }

    @Override
    public void playersReact(long adventureId, long playerId, String content) {
        Player player = findPlayer(adventureId, playerId);

        player.react(content);
    }

    @Override
    public void progressAdventure(long adventureId, String description) {
        Adventure adventure = findAdventure(adventureId);

        adventure.getGameMaster().progressReached(description);
    }

    @Override
    public void playerDoesHeroicDeed(long adventureId, long playerId, String description) {
        Player player = findPlayer(adventureId, playerId);

        player.doHeroicDeeds(description);
    }

    @Override
    public void gameMasterCancelsAdventure(long adventureId) {
        Adventure adventure = findAdventure(adventureId);
        adventure.getGameMaster().cancelAdventure();
    }

    @Override
    public void playerLeavesAdventure(long adventureId, long playerId) {
        Player player = findPlayer(adventureId, playerId);
        player.leaveAdventure();
    }

    private Adventure findAdventure(long adventureId) {
        Adventure adventure = adventureRepository.findById(adventureId);
        adventure.setDomainEventPublisher(eventPublisher);
        return adventure;
    }

    private Player findPlayer(long adventureId, long playerId) {
        Adventure adventure = findAdventure(adventureId);

        return adventure.playerFor(playerId)
                .orElseThrow(PlayerNotInAdventureException::new);
    }
}
