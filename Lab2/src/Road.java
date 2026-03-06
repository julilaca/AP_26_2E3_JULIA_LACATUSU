import java.util.Objects;

public class Road {

    private RoadType type;
    private double length;
    private double speedLimit;
    private Location start;
    private Location end;

    public Road(RoadType type, Location start, Location end, double speedLimit) {

        this.type = type;
        this.start = start;
        this.end = end;
        this.speedLimit = speedLimit;

        double dx = start.getX() - end.getX();
        double dy = start.getY() - end.getY();

        length = Math.sqrt(dx * dx + dy * dy);
    }
    public Location getStart() {
        return start; }
    public Location getEnd() {
        return end; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Double.compare(length, road.length) == 0 && Double.compare(speedLimit, road.speedLimit) == 0 && type == road.type && Objects.equals(start, road.start) && Objects.equals(end, road.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, length, speedLimit, start, end);
    }
    @Override
    public String toString() {
        return "Road " + type +
                " from " + start.getName() +
                " to " + end.getName() +
                " length=" + length;
    }
}