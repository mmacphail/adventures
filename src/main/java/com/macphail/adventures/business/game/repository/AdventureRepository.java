package com.macphail.adventures.business.game.repository;

import com.macphail.adventures.business.game.model.adventure.Adventure;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AdventureRepository {

    @PersistenceContext
    EntityManager em;

    public Adventure findById(long id) {
        Adventure adventure = em.find(Adventure.class, id);
        if(adventure == null) {
            throw new AdventureNotFoundException();
        }
        return adventure;
    }

    public List<Adventure> all() {
        return em.createNamedQuery(Adventure.findAll, Adventure.class)
                .getResultList();
    }

    public Adventure create(Adventure entity) {
        em.persist(entity);
        return entity;
    }

    public Adventure save(Adventure entity) {
        return em.merge(entity);
    }
}
