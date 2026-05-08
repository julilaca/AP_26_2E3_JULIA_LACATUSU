package lab9;

public class Bunny implements Runnable {
    private Maze maze;
    private SharedMemory memory;
    private GameControl control;
    private Position position;

    Bunny(Maze maze, SharedMemory memory, GameControl control) {
        this.maze = maze;
        this.memory = memory;
        this.control = control;

        position = maze.randomFreePosition();
        maze.place("B", position);
    }

    public void run() {
        while (!control.finished && !maze.isFinished()) {
            try {
                control.waitIfPaused();

                maze.move("B", position);
                memory.setBunnyPosition(position);

                Thread.sleep(control.bunnySpeed);
            } catch (InterruptedException e) {
                return;
            }
        }

        control.stopGame();
    }
}