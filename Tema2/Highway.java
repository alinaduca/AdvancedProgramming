/**
 * O specializare a clasei Road.
 * 
 * @author Duca Alina
 * @version 1.0
 * @see Road
 */
public class Highway extends Road {
    public Highway(double length, double speedLimit, Location l1, Location l2) {
        super("highway", length, speedLimit, l1, l2);
    }
}