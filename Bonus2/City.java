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