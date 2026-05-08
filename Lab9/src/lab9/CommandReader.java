package lab9;

import java.util.Scanner;

public class CommandReader implements Runnable {
    private GameControl control;

    CommandReader(GameControl control) {
        this.control = control;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (!control.finished) {
            String command = scanner.nextLine();

            if (command.equals("pause")) {
                control.pauseGame();
            } else if (command.equals("resume")) {
                control.resumeGame();
            } else if (command.equals("stop")) {
                control.stopGame();
            } else if (command.equals("faster robots")) {
                control.robotSpeed = Math.max(100, control.robotSpeed - 200);
            } else if (command.equals("slower robots")) {
                control.robotSpeed += 200;
            } else if (command.equals("faster bunny")) {
                control.bunnySpeed = Math.max(100, control.bunnySpeed - 200);
            } else if (command.equals("slower bunny")) {
                control.bunnySpeed += 200;
            }
        }
    }
}
