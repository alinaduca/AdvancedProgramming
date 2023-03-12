import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Network {
    private List<Node> nodes;
    
    public Network() {
        this.nodes = new ArrayList<Node>();
    }

    public void addNode(Node n) {
        //Verific daca persoana a fost deja adaugata
        if(n instanceof Person) { 
            Person p = (Person) n;
            for(Node node : nodes) {
                if(node instanceof Person) {
                    Person p1 = (Person) node;
                    if(p1.compareTo(p) == 0) {
                        System.out.println("Persoana a fost deja adaugata.");
                        return;
                    }
                }
            }
        }
        else {
            //Verific daca compania a fost deja adaugata 
            Company c = (Company) n;
            for(Node node : nodes) {
                if(node instanceof Company) {
                    Company c1 = (Company) node;
                    if(c1.compareTo(c) == 0) {
                        System.out.println("Compania a fost deja adaugata.");
                        return;
                    }
                }
            }
        }
        //Daca nu am returnat nimic pana acum, pot adauga noul nod
        nodes.add(n);
    }

    public int getImportance(Node n) {
        if(n instanceof Person) {
            Person p = (Person) n;
            return p.numberOfConections();
        }
        else {
            Company c = (Company) n;
            int importance = 0;
            for(Node node : nodes) {
                if(node instanceof Person) {
                    //Voi afla importanta companiei iterand prin noduri si verificand daca persoana curenta are conexiune cu compania.
                    //Daca nodul este o companie, il voi ignora.
                    Person p = (Person) node;
                    if(p.existConection(c)) {
                        importance++;
                    }
                }
            }
            return importance;
        }
    }

    public void printNetwork() {
        //Sortez descrescator nodurile in functie de importanta lor in retea
        nodes.sort(Comparator.comparingInt(this::getImportance).reversed());
        for(Node node : nodes) {
            System.out.println(node.toString() + " (importance: " + getImportance(node) + ")");
        }
    }
}


