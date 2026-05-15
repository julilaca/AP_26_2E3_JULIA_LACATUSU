package server.repository;

import server.entity.Game;
import javax.persistence.EntityManager;

public class GameRepository {
    private EntityManager em;

    public GameRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Game game) {
        em.getTransaction().begin();
        em.persist(game);
        em.getTransaction().commit();
    }

    public Game findById(Long id) {
        return em.find(Game.class, id);
    }
}