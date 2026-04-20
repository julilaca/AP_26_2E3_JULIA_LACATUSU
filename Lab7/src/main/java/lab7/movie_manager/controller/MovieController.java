package lab7.movie_manager.controller;

import lab7.movie_manager.Movie;
import lab7.movie_manager.dao.MovieDAO;
import lab7.movie_manager.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieDAO movieDAO = new MovieDAO();

    @GetMapping
    public List<Movie> getAllMovies() throws SQLException {
        return movieDAO.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> addMovie(@RequestBody Movie movie) throws SQLException {
        movieDAO.create(movie);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable int id, @RequestBody Movie movie) throws SQLException {
        if (movieDAO.findById(id) == null) {
            throw new ResourceNotFoundException("no movie  " + id);
        }
        movieDAO.update(id, movie);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/score")
    public ResponseEntity<Void> patchScore(@PathVariable int id, @RequestParam double score) throws SQLException {
        if (movieDAO.findById(id) == null) {
            throw new ResourceNotFoundException("no movie  " + id);
        }
        movieDAO.updateScore(id, score);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) throws SQLException {
        if (movieDAO.findById(id) == null) {
            throw new ResourceNotFoundException("no movie  " + id);
        }
        movieDAO.delete(id);
        return ResponseEntity.noContent().build();
    }
}