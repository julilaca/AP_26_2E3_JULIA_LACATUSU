package server.repository;

import server.entity.Player;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PlayerRepository {
    private EntityManager em;

    public PlayerRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Player player) {
        em.getTransaction().begin();
        em.persist(player);
        em.getTransaction().commit();
    }

    public Player findByName(String name) {
        long startTime = System.currentTimeMillis();
        try {
            String jpql = "SELECT p FROM Player p WHERE p.name = :name";
            Player player = em.createQuery(jpql, Player.class)
                    .setParameter("name", name)
                    .getSingleResult();
            log("findByName executed  " + (System.currentTimeMillis() - startTime) + "ms");
            return player;
        } catch (Exception e) {
            log("exception in findByName: " + e.getMessage());
            return null;
        }
    }


    public int updatePlayerName(Long id, String newName) {
        long startTime = System.currentTimeMillis();
        em.getTransaction().begin();
        try {
            String jpql = "UPDATE Player p SET p.name = :name WHERE p.id = :id";
            Query query = em.createQuery(jpql);
            query.setParameter("name", newName);
            query.setParameter("id", id);

            int rowsUpdated = query.executeUpdate();
            em.getTransaction().commit();

            log("updatePlayerName executed " + (System.currentTimeMillis() - startTime) + "ms");
            return rowsUpdated;
        } catch (Exception e) {
            em.getTransaction().rollback();
            log("Exception in updatePlayerName: " + e.getMessage());
            return 0;
        }
    }

    private void log(String message) {
        System.out.println("[LOG] " + message);
        try (PrintWriter out = new PrintWriter(new FileWriter("db_operations.log", true))) {
            out.println("log " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
