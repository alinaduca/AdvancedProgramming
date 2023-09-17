import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Queue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Harta este folosita pentru a memora drumurile, locatiile si conexiunile dintre ele.
 * De asemenea, aici pot verifica daca exista drum intre doua locatii, fie el direct sau care traverseaza si alte locatii.
 * Contine doua liste, una pentru locatii, iar cealalta pentru drumuri, precum si o matrice de adiacenta pentru memorarea vecinilor fiecarei locatii.
 * 
 * @author Duca Alina
 * @version 1.0
 * @see Location
 * @see Road 
 */
public class Harta {
    private List<Road> roads;
    private List<Location> locations;
    private Map<Location, List<Location>> map;
    
    public Harta() {
        this.map = new HashMap<>();
        this.roads = new ArrayList<>();
        this.locations = new ArrayList<>();
    }

    /**
     * Adauga un nou drum pe harta (daca acesta nu exista deja) si va adauga si locatiile de la capetele drumului, luand in calcul cazul in care acestea nu au fost adaugate la momentul potrivit.
     * 
     * @param road reprezinta drumul pe care vreau sa il adaug pe harta.
     */
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
        List<Location> neighbors = this.map.get(road.getLocation1());
        neighbors.add(road.getLocation2());
        map.put(road.getLocation1(), neighbors);
        neighbors = this.map.get(road.getLocation2());
        neighbors.add(road.getLocation1());
        map.put(road.getLocation2(), neighbors);
    }

    /**
     * addRoad se ocupa cu adaugarea drumurilor si implicit cu adaugarea locatiilor care sunt legate prin acel drum.
     * Totusi, in cazul in care am o locatie care nu este conectata cu nicio alta locatie prin drum, o voi adauga cu metoda addLocation
     * 
     * @param location reprezinta locatia care se doreste a fi adaugata
     */
    public void addLocation(Location location) {
        for(Location obj : this.locations) {
            if(location.equals(obj)) {
                System.out.println("Locatia a fost deja adaugata.");
                return;
            }
        }
        this.locations.add(location);
        this.map.put(location, new ArrayList<>());
    }

    /**
     * Metoda verifica daca pot ajunge de la o locatie la alta, realizand o parcurgere BFS.
     * Datele sunt stocate printr-o lista de adiacenta, deci parcurgerea BFS se realizeaza in O(n+m).
     * 
     * @param from - locatia de la care plec
     * @param to - locatia la care vreau sa ajung prin drumurile date
     * @return true daca pot ajunge de la prima locatie la a doua, mergand pe drumurile date; false altfel
     */
    public boolean existsPath(Location from, Location to) {
        if(from.equals(to))
            return true;
        Set<Location> visited = new HashSet<>();
        Queue<Location> queue = new LinkedList<>();
        visited.add(from);
        queue.add(from);
        while(!queue.isEmpty()) {
            Location u = queue.remove();
            if(this.map.get(u).contains(to)) {
                return true;
            }
            for(Location v : this.map.get(u)) {
                if(!visited.contains(v)) {
                    if(v.equals(to)) {
                        return true;
                    }
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
        return false;
    }
}
