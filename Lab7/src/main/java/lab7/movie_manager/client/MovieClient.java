package lab7.movie_manager.client;


import lab7.movie_manager.Movie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

@Component
public class MovieClient implements CommandLineRunner {

    @Override
    public void run(String... args) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8081/movies";

        Movie movie = new Movie("Test movie", Date.valueOf("2024-06-06"), 120, 8.5, 1);

        ResponseEntity<String> response = restTemplate.postForEntity(url, movie, String.class);
        System.out.println(response.getBody());
    }
}