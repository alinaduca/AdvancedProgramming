/**
 * Clasa principala.
 * 
 * @author Duca Alina
 * @version 1.0
 * @see Harta
 */
public class Main {
    public static void main(String[] args) {
        Location l1 = new City("Pascani", 30000, 30, 40);
        Location l2 = new City("Vaslui", 20000, 40.5, 50.2);
        System.out.println(l1.toString());
        System.out.println(l2.toString());
        Road r1 = new Highway(20, 30, l1, l2);
        System.out.println(r1.toString());
        Harta map1 = new Harta();
        map1.addRoad(r1);
        if(map1.existsPath(l1, l2)) {
            System.out.println("Exista drum intre Pascani si Vaslui");
        }
        else {
            System.out.println("Nu exista drum intre Pascani si Vaslui");
        }
        Location l3 = new Airport("Henri Coanda", 12, 30.5, 55.7);
        Road r2 = new Express(40, 90, l1, l3);
        map1.addRoad(r2);
        if(map1.existsPath(l2, l3)) {
            System.out.println("Exista drum intre aeroportul Henri Coanda si Vaslui");
        }
        else {
            System.out.println("Nu exista drum intre aeroportul Henri Coanda si Vaslui");
        }
    }
}
