package lab7.movie_manager.dao;

import lab7.movie_manager.Database;
import lab7.movie_manager.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public List<Movie> findAll() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies";

        try (Connection con = Database.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setReleaseDate(rs.getDate("release_date"));
                movie.setDuration(rs.getInt("duration"));
                movie.setScore(rs.getDouble("score"));
                movie.setGenreId(rs.getInt("genre_id"));
                movies.add(movie);
            }
        }

        return movies;
    }

    public Movie findById(int id) throws SQLException {
        String sql = "SELECT * FROM movies WHERE id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Movie movie = new Movie();
                    movie.setId(rs.getInt("id"));
                    movie.setTitle(rs.getString("title"));
                    movie.setReleaseDate(rs.getDate("release_date"));
                    movie.setDuration(rs.getInt("duration"));
                    movie.setScore(rs.getDouble("score"));
                    movie.setGenreId(rs.getInt("genre_id"));
                    return movie;
                }
            }
        }

        return null;
    }

    public void create(Movie movie) throws SQLException {
        String sql = "INSERT INTO movies (title, release_date, duration, score, genre_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, movie.getTitle());
            pstmt.setDate(2, movie.getReleaseDate());
            pstmt.setInt(3, movie.getDuration());
            pstmt.setDouble(4, movie.getScore());
            pstmt.setInt(5, movie.getGenreId());
            pstmt.executeUpdate();
        }
    }

    public void update(int id, Movie movie) throws SQLException {
        String sql = "UPDATE movies SET title = ?, release_date = ?, duration = ?, score = ?, genre_id = ? WHERE id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, movie.getTitle());
            pstmt.setDate(2, movie.getReleaseDate());
            pstmt.setInt(3, movie.getDuration());
            pstmt.setDouble(4, movie.getScore());
            pstmt.setInt(5, movie.getGenreId());
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        }
    }

    public void updateScore(int id, double score) throws SQLException {
        String sql = "UPDATE movies SET score = ? WHERE id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDouble(1, score);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM movies WHERE id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}

