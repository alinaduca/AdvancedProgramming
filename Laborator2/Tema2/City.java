/**
 * O specializare a clasei Location.
 * 
 * @author Duca Alina
 * @version 1.0
 * @see Location
 */
public class City extends Location {
    private int population;

    public City(String name, int population, double x, double y) {
        super(name, "city", x, y);
        this.population = population;
    }

    public int getPopulation() {
        return this.population;
    }
}