enum roadType {
    HIGHWAY,
    EXPRESS,
    COUNTRY,
}

public class Road {
    private roadType type;
    private double length;
    private double speedLimit;

    public Road() {
        this.type = roadType.HIGHWAY;
        this.length = 0;
        this.speedLimit = 0;
    }

    public void setType(roadType type) {
        this.type = type;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = speedLimit;
    }

    public roadType getRoadType() {
        return this.type;
    }

    public double getLength() {
        return this.length;
    }

    public double getSpeedLimit() {
        return this.speedLimit;
    }

    @Override
    public String toString() {
        return "Road type " + this.type + ", with length = " + this.length + " and speed limit = " + this.speedLimit;
    }
}
