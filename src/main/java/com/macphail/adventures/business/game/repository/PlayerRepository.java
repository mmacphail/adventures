package com.macphail.adventures.business.game.repository;

import com.macphail.adventures.business.game.model.adventure.Player;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@Stateless
public class PlayerRepository {

    @PersistenceContext
    EntityManager em;

    public Player findById(long id) {
        return em.find(Player.class, id);
    }

    public void delete(long id) {
        try {
            Player reference = em.getReference(Player.class, id);
            em.remove(reference);
        } catch(EntityNotFoundException e) {
            // ignoring exception
        }
    }

    public Player create(Player entity) {
        em.persist(entity);
        return entity;
    }

    public Player save(Player entity) {
        return em.merge(entity);
    }
}
