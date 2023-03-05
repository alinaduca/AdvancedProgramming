import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int number = 10000;
        Location l[] = new Location[number];
        Road r[] = new Road[number];
        Random rand = new Random();
        int randomNumber;
        l[0] = new City("City0", 30000, 30, 40);
        Harta map = new Harta();
        for(int i = 1; i < number; i++) {
            randomNumber = rand.nextInt(number);
            randomNumber += 3000;
            if(randomNumber % 3 == 0) {
                l[i] = new Airport("Airport" + Integer.toString(i), randomNumber, randomNumber / 4, randomNumber / 2);
                r[i] = new Express(randomNumber * 8, i, l[i], l[i - 1]);
            }
            else {
                if(randomNumber % 3 == 0) {
                    l[i] = new City("City" + Integer.toString(i), randomNumber, randomNumber / 3, randomNumber / 2);
                    r[i] = new Country(randomNumber * 8, i, l[i], l[i - 1]);
                }
                else {
                    l[i] = new GasStation("Gas Station" + Integer.toString(i), randomNumber, randomNumber, randomNumber / 2);
                    r[i] = new Highway(randomNumber * 8, i, l[i], l[i - 1]);
                }
            }
            map.addRoad(r[i]);
        }
        long startMil = System.currentTimeMillis();
        List<Location> list = map.bestRoute(l[0], l[number - 1]);
        long endMil = System.currentTimeMillis();
        for(Location l1 : list) {
            System.out.print(l1.getName() + " -> ");
        }
        System.out.println();
        System.out.println(endMil - startMil + " milisecunde");
    }
}
