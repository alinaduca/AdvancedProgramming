enum locationType {
    CITY,
    AIRPORT,
    GAS_STATION,
};

public class Location {
    private String name;
    private locationType type;
    private double x;
    private double y;

    public Location() {
        this.name = "";
        this.type = locationType.CITY;
        this.x = 0;
        this.y = 0;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setType(locationType type) {
        this.type = type;
    }

    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return this.name;
    }

    public locationType getLocationType() {
        return this.type;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "Location " + this.name + ", type " + this.type + ", x = " + this.x + ", y = " + this.y;
    }
}
