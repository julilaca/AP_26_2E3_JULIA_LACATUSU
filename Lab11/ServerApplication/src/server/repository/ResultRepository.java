package server.repository;

import server.entity.Result;
import javax.persistence.EntityManager;

public class ResultRepository {
    private EntityManager em;

    public ResultRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Result result) {
        em.getTransaction().begin();
        em.persist(result);
        em.getTransaction().commit();
    }

    public Result findById(Long id) {
        return em.find(Result.class, id);
    }
}
