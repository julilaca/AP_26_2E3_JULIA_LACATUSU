package lab9;

public class Manager implements Runnable {
    private Maze maze;
    private GameControl control;
    private long startTime;
    private int timeLimit;

    Manager(Maze maze, GameControl control, int timeLimit) {
        this.maze = maze;
        this.control = control;
        this.timeLimit = timeLimit;
        startTime = System.currentTimeMillis();
    }

    public void run() {
        while (!control.finished) {
            long seconds = (System.currentTimeMillis() - startTime) / 1000;

            System.out.println("Running time: " + seconds);
            maze.printMaze();

            if (seconds >= timeLimit) {
                control.stopGame();
                break;
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}