public class GasStation extends Location {
    private int gasPrice;

    public GasStation(String name, int gasPrice, double x, double y) {
        super(name, "gas station", x, y);
        this.gasPrice = gasPrice;
    }

    public int getNumberOfTerminals() {
        return this.gasPrice;
    }
}