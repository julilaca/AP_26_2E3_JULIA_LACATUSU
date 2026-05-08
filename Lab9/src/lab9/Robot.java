package lab9;

public class Robot implements Runnable {
    private String name;
    private Maze maze;
    private SharedMemory memory;
    private GameControl control;
    private Position position;
    private int direction = 0;

    Robot(String name, Maze maze, SharedMemory memory, GameControl control) {
        this.name = name;
        this.maze = maze;
        this.memory = memory;
        this.control = control;

        position = maze.randomFreePosition();
        maze.place("R", position);
    }

    public void run() {
        while (!control.finished && !maze.isFinished()) {
            try {
                control.waitIfPaused();

                Position bunny = memory.getBunnyPosition();

                if (bunny != null) {
                    int distance = Math.abs(position.row - bunny.row)
                            + Math.abs(position.col - bunny.col);

                    if (distance <= 2) {
                        System.out.println(name + " sees the bunny.");
                    }
                }

                maze.systematicMove("R", position, direction);
                direction = (direction + 1) % 4;

                Thread.sleep(control.robotSpeed);
            } catch (InterruptedException e) {
                return;
            }
        }

        control.stopGame();
    }
}