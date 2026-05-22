package server.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime gameDate;

    @ManyToMany
    @JoinTable(
            name = "games_questions",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions = new ArrayList<>();

    public Game() {}

    public Game(LocalDateTime gameDate) { this.gameDate = gameDate; }

    public Long getId() {
        return id; }

    public void setId(Long id) {
        this.id = id; }

    public LocalDateTime getGameDate() {
        return gameDate; }

    public void setGameDate(LocalDateTime gameDate) {
        this.gameDate = gameDate; }

    public List<Question> getQuestions() {
        return questions; }

    public void setQuestions(List<Question> questions) {
        this.questions = questions; }
}