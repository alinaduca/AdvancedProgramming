public class Airport extends Location {
    private int numberOfTerminals;

    public Airport(String name, int numberOfTerminals, double x, double y) {
        super(name, "airport", x, y);
        this.numberOfTerminals = numberOfTerminals;
    }

    public int getNumberOfTerminals() {
        return this.numberOfTerminals;
    }
}