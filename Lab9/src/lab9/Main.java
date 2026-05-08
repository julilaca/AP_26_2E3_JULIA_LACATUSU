package lab9;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(8, 4);
        SharedMemory memory = new SharedMemory();
        GameControl control = new GameControl();

        Bunny bunny = new Bunny(maze, memory, control);
        Robot r1 = new Robot("robot1", maze, memory, control);
        Robot r2 = new Robot("robot2", maze, memory, control);
        Robot r3 = new Robot("robot3", maze, memory, control);

        Thread manager = new Thread(new Manager(maze, control, 30));
        manager.setDaemon(true);
        manager.start();

        new Thread(new CommandReader(control)).start();

        new Thread(bunny, "bunny").start();
        new Thread(r1, "robot1").start();
        new Thread(r2, "robot2").start();
        new Thread(r3, "robot3").start();
    }
}
