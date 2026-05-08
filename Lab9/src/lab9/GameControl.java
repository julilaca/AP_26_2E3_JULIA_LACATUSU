package lab9;

public class GameControl {
    volatile boolean finished = false;
    volatile boolean paused = false;

    volatile int bunnySpeed = 700;
    volatile int robotSpeed = 900;

    public synchronized void pauseGame() {
        paused = true;
    }

    public synchronized void resumeGame() {
        paused = false;
        notifyAll();
    }

    public synchronized void waitIfPaused() throws InterruptedException {
        while (paused && !finished) {
            wait();
        }
    }

    public void stopGame() {
        finished = true;
        resumeGame();
    }
}