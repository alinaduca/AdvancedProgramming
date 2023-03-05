/**
 * Location este folosit pentru locatii, fiind o clasa abstracta. Aceste locatii pot fi fie orase, fie aeroporturi, fie statii peco.
 * 
 * @author Duca Alina
 * @version 1.0
 * @see Road 
 */
public abstract class Location {
    private String name;
    private String type;
    private double x;
    private double y;

    public Location(String name, String type, double x, double y) {
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return this.name;
    }

    public String getLocationType() {
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

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Location))
            return false;
        Location location = (Location) obj;
        return location.x == this.x && location.y == this.y && location.name == this.name && location.type == type;
    }
}
