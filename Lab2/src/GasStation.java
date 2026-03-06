public class GasStation extends Location {

    private double gasPrice;

    public GasStation(String name, double x, double y, double gasPrice) {
        super(name, x, y);
        this.gasPrice = gasPrice;
    }

    public double getGasPrice() {
        return gasPrice;
    }

    @Override
    public String toString() {
        return "GasStation " + getName() + " price=" + gasPrice;
    }
}