/**
 * Road este folosit pentru a memora drumurile si locatiile pe care acestea le conecteaza.
 * 
 * @author Duca Alina
 * @version 1.0
 * @see Location
 */
public abstract class Road {
    private String type;
    private double length;
    private double speedLimit;
    private Location location1;
    private Location location2;

    /**
     * In constructor verific mai intai daca lungimea drumului este mai mare decat distanta euclidiana dintre cele doua localitati, in caz contrar lungimea acelui drum fiind necorespunzatoare.
     * Daca lungimea este conforma si nu am eroare, initializarile vor avea loc cu succes.
     * 
     * @param type tipul drumului
     * @param length lungimea drumului
     * @param speedLimit limita de viteza pe segmentul respectiv
     * @param location1 prima locatie
     * @param location2 a doua locatie
     */
    public Road(String type, double length, double speedLimit, Location location1, Location location2) {
        if(length <= 0 || length * length < (location1.getX() - location2.getX()) * (location1.getX() - location2.getX()) + (location1.getY() - location2.getY()) * (location1.getY() - location2.getY())) {
            System.out.println("Lungimea este necorespunzatoare!");
            System.exit(1);
        }
        this.type = type;
        this.length = length;
        this.speedLimit = speedLimit;
        this.location1 = location1;
        this.location2 = location2;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLength(double length) {
        this.length = length;
    }
    
    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = speedLimit;
    }

    public String getRoadType() {
        return this.type;
    }

    public double getLength() {
        return this.length;
    }
    
    public double getSpeedLimit() {
        return this.speedLimit;
    }

    public Location getLocation1() {
        return this.location1;
    }

    public Location getLocation2() {
        return this.location2;
    }

    public boolean instantaValida() {
        if(this.length <= 0 || this.length * this.length < (this.location1.getX() - this.location2.getX()) * (this.location1.getX() - this.location2.getX()) + (this.location1.getY() - this.location2.getY()) * (this.location1.getY() - this.location2.getY())) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Road type " + this.type + ", with length = " + this.length + " and speed limit = " + this.speedLimit + " between " + this.location1.getName() + " and " + this.location2.getName() + ".";
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Location))
            return false;
        Road road = (Road) obj;
        return road.type == this.type && road.length == this.length && road.speedLimit == this.speedLimit && ((road.location1 == this.location1 && road.location2 == this.location2) || (road.location1 == this.location2 && road.location2 == this.location1));
    }
}

