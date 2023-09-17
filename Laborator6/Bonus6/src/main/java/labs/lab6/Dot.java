package labs.lab6;

import java.io.Serializable;
import java.util.*;

public class Dot implements Serializable {
    private double x;
    private double y;

    private List<Dot> redConnections;
    private List<Dot> blueConnections;

    public Dot(double x, double y) {
        this.x = x;
        this.y = y;
        redConnections = new ArrayList<>();
        blueConnections = new ArrayList<>();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Dot))
            return false;
        Dot dot = (Dot) obj;
        if(dot.x == this.x && dot.y == this.y) return true;
        else if(Math.abs(dot.x-this.x) < 1 && Math.abs(dot.y-this.y) < 1) return true;
        return false;
    }

    public void addConnection(Dot dot, String color) {
        if(color.equals("red")) {
            redConnections.add(dot);
        }
        else {
            blueConnections.add(dot);
//            System.out.println();
//            System.out.print(this);
//            for(Dot d : blueConnections) {
//                System.out.print(d);
//            }
//            System.out.println();
//            System.out.println();
        }
    }

    public List<Dot> getConnections(String color) {
        if(color.equals("red")) {
            return redConnections;
        }
        else {
            return blueConnections;
        }
    }

    @Override
    public String toString() {
        return "Dot with x=" + x + "and y=" + y;
    }
}
