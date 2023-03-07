import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class Harta {
    private List<Road> roads;
    private List<Location> locations;
    private Map<Location, List<Road>> locationsAndRoads;
    
    public Harta() {
        this.roads = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.locationsAndRoads = new HashMap<>();
    }

    public void addRoad(Road road) {
        for(Road obj : this.roads) {
            if(road.equals(obj)) {
                System.out.println("Drumul a fost deja adaugat.");
                return;
            }
        }
        this.roads.add(road);
        addLocation(road.getLocation1());
        addLocation(road.getLocation2());

        List<Road> roads = this.locationsAndRoads.get(road.getLocation1());
        roads.add(road);
        locationsAndRoads.put(road.getLocation1(), roads);
        roads = this.locationsAndRoads.get(road.getLocation2());
        roads.add(road);
        locationsAndRoads.put(road.getLocation2(), roads);
    }

    public void addLocation(Location location) {
        for(Location obj : this.locations) {
            if(location.equals(obj)) {
                return;
            }
        }
        this.locations.add(location);
        this.locationsAndRoads.put(location, new ArrayList<>());
    }
    
    public List<Location> bestRoute(Location from, Location to) {
        List<Location> visited = new ArrayList<>();
        visited.add(from);
        List<Location> path = new ArrayList<>();
        List<Location> path1 = new ArrayList<>();
        Map<Location, Location> before = new HashMap<>();
        Map<Location, Double> speedAndLength = new HashMap<>();
        for(Location l : this.locations) {
            before.put(l, from);
        }
        List<Road> roads = this.locationsAndRoads.get(from);
        for(Road r : roads) {
            Location l2 = r.getLocation1();
            if(l2.equals(from)) {
                l2 = r.getLocation2();
            }
            if(speedAndLength.get(l2) == null) {
                speedAndLength.put(l2, r.getLength() / r.getSpeedLimit() );
            }
            else {
                if(speedAndLength.get(l2) > r.getLength() / r.getSpeedLimit()) {
                    speedAndLength.put(l2, r.getLength() / r.getSpeedLimit());
                }
            }
        }
        while(locations.size() != visited.size() && !visited.containsAll(locations)) {
            double minim = -1;
            Location locAdd = from;
            for(Location l : speedAndLength.keySet()) {
                if(!visited.contains(l)) {
                    if(speedAndLength.get(l) < minim || minim == -1) {
                        minim = speedAndLength.get(l);
                        locAdd = l;
                    }
                }
            }
            visited.add(locAdd);
            List<Road> rs = this.locationsAndRoads.get(locAdd);
            for(Road r : rs) {
                Location l1 = r.getLocation1();
                if(l1.equals(locAdd)) {
                    l1 = r.getLocation2();
                }
                if(!visited.contains(l1)) {
                    if(speedAndLength.get(l1) == null || (speedAndLength.get(l1) > speedAndLength.get(locAdd) + r.getLength() / r.getSpeedLimit())) {
                        speedAndLength.put(l1, speedAndLength.get(locAdd) + r.getLength() / r.getSpeedLimit());
                        before.put(l1, locAdd);
                    }
                }
            }
        }
        Location l, l1 = to;
        do {
            l = l1;
            path.add(l);
            l1 = before.get(l);
        } while(l != l1);
        for(int i = path.size() - 1; i >= 0; i--) {
            path1.add(path.get(i));
        }
        return path1;
    }
}
