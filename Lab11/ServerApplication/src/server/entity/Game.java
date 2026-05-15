package server.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime gameDate;

    public Game() {}

    public Game(LocalDateTime gameDate) {
        this.gameDate = gameDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getGameDate() { return gameDate; }
    public void setGameDate(LocalDateTime gameDate) { this.gameDate = gameDate; }
}