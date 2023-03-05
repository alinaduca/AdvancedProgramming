import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<Node>();
        Person p1 = new Person("Alina");
        Person p2 = new Person("Maria");
        Person p3 = new Person("Ioana");
        Company c1 = new Company("Bytex");
        Company c2 = new Company("Endava");
        Company c3 = new Company("Continental");
        nodes.add(p1);
        nodes.add(p2);
        nodes.add(p3);
        nodes.add(c1);
        nodes.add(c2);
        nodes.add(c3);
        for(Node node : nodes) {
            System.out.println(node.getName());
        }
    }
}
