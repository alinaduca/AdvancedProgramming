public class Main {
    public static void main(String[] args) {
        //Declar persoanele si companiile
        Person p1 = new Programmer("Alina", "06/02/2002", "Visual Studio Code");
        Person p2 = new Designer("Maria", "PhotoShop");
        Person p3 = new Person("Ioana");
        Person p4 = new Person("Ana-Maria");
        Company c1 = new Company("Bytex");

        //Adaug relatii intre o persoana si o alta persoana/o companie
        p1.addRelationship(p2, "friends");
        p1.addRelationship(p3, "enemies");
        p2.addRelationship(p4, "friendship");
        p3.addRelationship(p4, "friendship");
        p2.addRelationship(p3, "friends");
        p1.addRelationship(c1, "employee");
        
        //Declar o retea
        Network retea = new Network();
        
        //Introduc persoanele in retea
        retea.addNode(p1);
        retea.addNode(p2);
        retea.addNode(p3);
        retea.addNode(p4);
        retea.addNode(c1);

        //Afisez punctele de articulatie din retea
        System.out.println("Punctele de articulatie sunt:");
        retea.showAP();
        assert retea.articulationPoints().size() == 1;
        
        //Afisez blocurile
        for(Network network : retea.findFinalBlocks()) {
            System.out.println("Un bloc din retea este:");
            network.printNetwork();
        }
        
        //JUnit tests
        assert retea.getImportance(p4) == 2;
        assert retea.existEdge(p2, p4) == true; 
        assert p1.getBirthDate() == "06/02/2002";
    }
}



//schema;person
//scholarly html//mateimicu