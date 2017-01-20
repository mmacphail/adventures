package com.macphail.adventures.business.game.repository;

import com.macphail.adventures.business.game.model.event.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EventRepository {

    @PersistenceContext
    EntityManager em;

    public List<Event> findForAdventure(long adventureId) {
        return em.createNamedQuery(Event.findForAdventure, Event.class)
                .setParameter("id", adventureId)
                .getResultList();
    }

    public Event persist(Event event) {
        em.persist(event);
        return event;
    }

}
