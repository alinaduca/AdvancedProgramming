/**
 * O specializare a clasei Road.
 * 
 * @author Duca Alina
 * @version 1.0
 * @see Road
 */
public class Express extends Road {
    public Express(double length, double speedLimit, Location l1, Location l2) {
        super("express", length, speedLimit, l1, l2);
    }
}