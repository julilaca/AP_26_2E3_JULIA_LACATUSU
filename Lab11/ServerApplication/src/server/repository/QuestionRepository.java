package server.repository;

import server.entity.Question;
import javax.persistence.EntityManager;

public class QuestionRepository {
    private EntityManager em;

    public QuestionRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Question question) {
        em.getTransaction().begin();
        em.persist(question);
        em.getTransaction().commit();
    }

    public Question findById(Long id) {
        return em.find(Question.class, id);
    }
}