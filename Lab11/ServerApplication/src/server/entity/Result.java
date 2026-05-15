package server.entity;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(nullable = false)
    private Integer score;

    public Result() {}

    public Result(Player player, Game game, Integer score) {
        this.player = player;
        this.game = game;
        this.score = score;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
}
