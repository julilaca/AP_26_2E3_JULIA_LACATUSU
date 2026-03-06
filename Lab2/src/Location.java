import java.util.Objects;
/**
 * represents a location.
 */
public abstract class Location {

    private String name;
    private double x;
    private double y;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(x, location.x) == 0 && Double.compare(y, location.y) == 0 && Objects.equals(name, location.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, x, y);
    }
    public Location(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public String getName() {
        return name; }

    public double getX() {
        return x; }

    public double getY() {
        return y;}

    @Override
    public String toString() {
        return name + "(" + x + "," + y + ")";
    }
}