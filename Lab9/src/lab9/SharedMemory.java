package lab9;

public class SharedMemory {
    private Position bunnyPosition;

    public synchronized void setBunnyPosition(Position p) {
        bunnyPosition = new Position(p.row, p.col);
    }

    public synchronized Position getBunnyPosition() {
        return bunnyPosition;
    }
}
