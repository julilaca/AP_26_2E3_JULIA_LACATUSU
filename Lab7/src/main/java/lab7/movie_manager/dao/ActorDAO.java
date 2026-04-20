package lab7.movie_manager.dao;

import lab7.movie_manager.Database;

import java.sql.*;

public class ActorDAO {
    public void create(String name) throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO actors (name) VALUES (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }
}