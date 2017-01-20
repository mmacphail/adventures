package com.macphail.adventures.business.game.repository;

import com.macphail.adventures.business.game.model.adventure.Adventure;
import com.macphail.adventures.business.game.model.adventure.GameMaster;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

/**
 * Created by alexandre.fruchaud on 11/01/2017.
 */
@Stateless
public class GameMasterRepository {

    @PersistenceContext
    EntityManager em;

    public GameMaster findById(long id) {
        return em.find(GameMaster.class, id);
    }

    public void delete(long id) {
        try {
            GameMaster reference = em.getReference(GameMaster.class, id);
            em.remove(reference);
        } catch(EntityNotFoundException e) {
            // ignoring exception
        }
    }

    public GameMaster create(GameMaster entity) {
        em.persist(entity);
        return entity;
    }

    public GameMaster findByAdventure(Adventure adventure) {
        return em.createNamedQuery(GameMaster.findForAdventure, GameMaster.class)
                .setParameter("adventure", adventure)
                .getSingleResult();
    }

    public boolean isUserGameMaster(String userId) {
        return !em.createNamedQuery(GameMaster.isGameMaster, GameMaster.class)
                .setParameter("userId", userId)
                .getResultList().isEmpty();
    }

    public GameMaster save(GameMaster entity) {
        return em.merge(entity);
    }
}
