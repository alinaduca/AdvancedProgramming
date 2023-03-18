import java.util.*;

public class Network {
    private List<Node> nodes;
    private Map<Node, List<Node>> network;
    private int time;
    
    public Network() {
        this.nodes = new ArrayList<Node>();

        //initializez lista de adiacenta
        this.network = new HashMap<>();
    }
    
    public void addNode(Node n) {
        //Verific daca persoana a fost deja adaugata
        if(n instanceof Person) { 
            Person p = (Person) n;
            for(Node node : nodes) {
                if(node instanceof Person) {
                    Person p1 = (Person) node;
                    if(p1.compareTo(p) == 0) {
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

    public void createAdjacencyList() {
        //construiesc lista de adiacenta
        for(Node node : nodes) {
            if(node instanceof Person) {
                Person p = (Person) node;
                List<Node> list = p.getConnections();
                this.network.put(node, list);
                for(Node element : list) {
                    if(element instanceof Company) {
                        List<Node> newList = this.network.get((Company) element);
                        if(newList == null) {
                            newList = new ArrayList<>();
                            newList.add(node);
                        }
                        else {
                            if(!newList.contains(node)) {
                                newList.add(node);
                            }
                        }
                        
                        this.network.put(element, newList);
                    }
                }
            }
        }
    }
    
    public void showAP() {
        createAdjacencyList();
        this.time = 0;

        //apelez AP pentru a afla si afisa punctele de articulatie   
        for(Node n : articulationPoints()) {
            System.out.println(n.getName());
        }
    }

    public void DFS(Map<Node, List<Node>> adj, Node u, Map<Node, Boolean> visited, Map<Node, Integer> disc, Map<Node, Integer> low, Node parent, Map<Node, Boolean> isArticulationPoint) {
        time++;
        int children = 0;
        low.put(u, time);
        disc.put(u, time);
        visited.put(u, true);
        for(Node v : adj.get(u)) {
            if(visited.get(v) != null && visited.get(v) == false) {
                children++;
                DFS(adj, v, visited, disc, low, u, isArticulationPoint);
                low.put(u, Math.min(low.get(u), low.get(v)));
                
                //Daca u nu este radacina, iar timpul minim de vizitare al unui copil de-al lui este
                //mai mare decat timpul lui, atunci u este punct de articulatie.
                
                if(parent != null && low.get(v) >= disc.get(u)) {
                    isArticulationPoint.put(u, true);
                }
            }
            else {
                if((v instanceof Person && parent instanceof Company) || (parent instanceof Person && v instanceof Company) || parent == null) {
                    low.put(u, Math.min(low.get(u), disc.get(v)));
                }
                else {
                    if(v instanceof Person) {
                        Person p1 = (Person) v;
                        Person p2 = (Person) parent;
                        if(p1.compareTo(p2) != 0) {
                            if(!low.keySet().contains(u)) {
                                low.put(u, disc.get(v));
                            }
                            else {
                                if(!disc.keySet().contains(v)) {
                                    low.put(u, low.get(u));
                                }
                                else {
                                    low.put(u, Math.min(low.get(u), disc.get(v)));
                                }
                            }
                        }
                    }
                    else {
                        Company c1 = (Company) v;
                        Company c2 = (Company) parent;
                        if(c1.compareTo(c2) != 0) {
                            low.put(u, Math.min(low.get(u), disc.get(v)));
                        }
                    }
                }
            }
        }

        //Daca u este radacina arborelui DFS si mai mult de un descendent
        if(parent == null && children > 1) {
            isArticulationPoint.put(u, true);
        }
    }

    public List<Node> articulationPoints() {
        Map<Node, List<Node>> adj = network;
        List<Node> listArticulationPoints = new ArrayList<>();
        Map<Node, Boolean> isArticulationPoint = new HashMap<>();
        Map<Node, Boolean> visited = new HashMap<>();
        Map<Node, Integer> disc = new HashMap<>();
        Map<Node, Integer> low = new HashMap<>();

		Node parent = null;

        for(Node u : adj.keySet()) {
            visited.put(u, false);
            isArticulationPoint.put(u, false);
        }

        for(Node u : adj.keySet()) {
            if(visited.get(u) == false) {
                DFS(adj, u, visited, disc, low, parent, isArticulationPoint);
            }
        }

        for(Node u : adj.keySet()) {
            if(isArticulationPoint.get(u) == true) {
                listArticulationPoints.add(u);
            }
        }
        return listArticulationPoints;
    }

    public boolean isConnected() {
        Map<Node, List<Node>> adj = network;
        Map<Node, Boolean> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        
        for(Node u : adj.keySet()) {
            visited.put(u, false);
        }
        if(adj.keySet().size() > 2) {
            Object obj = adj.keySet().toArray()[0];
            Node start = (Node) obj;
            queue.offer(start);
            while(!queue.isEmpty()) {
                Node v = queue.poll();
                visited.put(v, true);
                for(Node u : adj.get(v)) {
                    if(visited.get(u) != null && visited.get(u) == false) {
                        queue.offer(u);
                    }
                }
            }
            for(Node u : adj.keySet()) {
                if(visited.get(u) == false) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void removeVertex(Node vertex) {
        this.nodes.remove(vertex);
        List<Node> list = this.network.get(vertex);
        for(Node element : list) {
            List<Node> newList = this.network.get(element);
            newList.remove(vertex);
            this.network.put(element, newList);
        }
        this.network.remove(vertex);
    }

    public void addEdge(Node vertex1, Node vertex2) {
        List<Node> newList;
        if(this.network.keySet().contains(vertex1)) {
            newList = this.network.get(vertex1);
        }
        else {
            newList = new ArrayList<>();
        }
        newList.add(vertex2);
        this.network.put(vertex1, newList);
        if(this.network.keySet().contains(vertex2)) {
            newList = this.network.get(vertex2);
        }
        else {
            newList = new ArrayList<>();
        }
        newList.add(vertex1);
        this.network.put(vertex2, newList);
    }

    private void dfs(Node vertex, Set<Node> visited, Network component) {
        visited.add(vertex);
        component.addNode(vertex);
        if(this.network.keySet().contains(vertex)) {
            for(Node neighbor : this.network.get(vertex)) {
                if(!visited.contains(neighbor)) {
                    component.addNode(neighbor);   
                    component.addEdge(vertex, neighbor);
                    dfs(neighbor, visited, component);
                }
            }
        }
    }

    public boolean Equals(Node n1, Node n2) {
        if((n1 instanceof Person && n2 instanceof Company) || (n2 instanceof Person && n1 instanceof Company) || n1 == null || n2 == null) {
            return false;
        }
        else {
            if(n1 instanceof Person) {
                Person p1 = (Person) n1;
                Person p2 = (Person) n2;
                if(p1.compareTo(p2) != 0) {
                    return false;
                }
                return true;
            }
            else {
                Company c1 = (Company) n1;
                Company c2 = (Company) n2;
                if(c1.compareTo(c2) != 0) {
                    return false;
                }
                return true;
            }
        }
    }

    public boolean existEdge(Node n1, Node n2) {
        if(this.network.get(n1).contains(n2) || this.network.get(n2).contains(n1)) {
            return true;
        }
        return false;
    }

    public void addTheRestOfEdges(Network parent) {
        for(Node n1 : this.nodes) {
            for(Node n2 : this.nodes) {
                if(!Equals(n1, n2) && parent.existEdge(n1, n2)) {
                    if(!this.network.get(n1).contains(n2)) {
                        List<Node> newList = this.network.get(n1);
                        newList.add(n2);
                        this.network.put(n1, newList);
                    }
                    if(!this.network.get(n2).contains(n1)) {
                        List<Node> newList = this.network.get(n2);
                        newList.add(n1);
                        this.network.put(n2, newList);
                    }
                }
            }
        }
    }

    public List<Network> getConexComponents() {
        List<Network> components = new ArrayList<>();
        Set<Node> visited = new HashSet<>();
        for(Node n : this.nodes) {
            if(!visited.contains(n)) {
                Network component = new Network();
                dfs(n, visited, component);
                component.addTheRestOfEdges(this);
                components.add(component);
            }
        }
        return components;
    }
    
    //Asta se va apela prima data.
    public List<Network> findFinalBlocks() {
        createAdjacencyList();
        return findBlocks();
    }
    
    public List<Network> findBlocks() {
        this.time = 0;
        List<Node> artPoints = articulationPoints();
        List<Network> blocks = new ArrayList<>();
        for(Node ap : artPoints) {
            if(NullAdjList() == true) {
                System.out.println(blocks.size());
                return blocks;
            }
            Network subgraph = this;
            subgraph.removeVertex(ap); //subgraful va contine cel putin 2 comp conexe
            for(Network sub : subgraph.getConexComponents()) {
                if(sub.isConnected() && sub.articulationPoints().isEmpty()) {
                    blocks.add(sub);
                }
                else {
                    List<Network> recursiveSubgraphs = sub.findBlocks();
                    blocks.addAll(recursiveSubgraphs);
                }
            }
        }
        return blocks;
    }

    public boolean NullAdjList() {
        for(Node n : this.network.keySet()) {
            if(this.network.get(n).size() > 0) {
                return false;
            }
        }
        return true;
    }
}