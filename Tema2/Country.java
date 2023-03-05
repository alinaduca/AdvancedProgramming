/**
 * O specializare a clasei Road.
 * 
 * @author Duca Alina
 * @version 1.0
 * @see Road
 */
public class Country extends Road {
    public Country(double length, double speedLimit, Location l1, Location l2) {
        super("country", length, speedLimit, l1, l2);
    }
}