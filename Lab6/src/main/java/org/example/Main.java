package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            GenreDAO genres = new GenreDAO();

            genres.create("Sci-Fi");
            genres.create("Fantasy");

            System.out.println("ID for Sci-Fi: " + genres.findByName("Sci-Fi"));
            System.out.println("Name for ID 1: " + genres.findById(1));

            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
