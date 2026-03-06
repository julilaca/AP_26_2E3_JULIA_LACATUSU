/**
 * Represents an airport.
 */
public class Airport extends Location {

    private int terminals;

    public Airport(String name, double x, double y, int terminals) {
        super(name, x, y);
        this.terminals = terminals;
    }
    public int getTerminals() {
        return terminals;
    }
    @Override
    public String toString() {
        return "Airport " + getName() + " terminals=" + terminals;
    }
}