public class Main {
    public static void main(String[] args) {
        //Declar persoanele si companiile
        Person p1 = new Programmer("Alina", "06/02/2002", "Visual Studio Code");
        Person p2 = new Designer("Maria", "PhotoShop");
        Person p3 = new Person("Ioana");
        Company c1 = new Company("Bytex");
        Company c2 = new Company("Endava");
        Company c3 = new Company("Continental");
        
        //Adaug relatii intre o persoana si o alta persoana/o companie
        p1.addRelationship(p2, "friends");
        p1.addRelationship(p3, "enemies");
        p2.addRelationship(p3, "friends");
        p1.addRelationship(c1, "employee");
        p2.addRelationship(c2, "employee");
        
        //Declar o retea
        Network retea = new Network();
        
        //Introduc persoanele in retea
        retea.addNode(p1);
        retea.addNode(p2);
        retea.addNode(p3);
        retea.addNode(p1); //Imi va afisa mesajul "Persoana a fost deja adaugata."
        retea.addNode(c1);
        retea.addNode(c2);
        retea.addNode(c3);

        //Afisez reteaua, in ordinea descrescatoare a importantei fiecarui nod
        retea.printNetwork();
    }
}
