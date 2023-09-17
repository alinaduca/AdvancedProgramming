import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class Person implements Node, Comparable<Person> {
    private String name;
    private String birthDate;
    private Map<Node, String> relationships;

    /*
     * Voi avea doi constructori, pentru a permite atat adaugarea unei persoane cu data ei de nastere, cat si adaugarea
     * ei fara data de nastere.
     */
    public Person(String name) {
        this.name = name;
        this.relationships = new HashMap<>();
    }

    public Person(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.relationships = new HashMap<>();
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public void addRelationship(Node n, String relationship) {
        /*
         * Atunci cand adaug o relatie intre doua persoane, trebuie sa am grija sa adaug relatia in Map-urile ambelor persoane.
         * In acest sens, de fiecare data cand adaug o relatie noua pentru this, o voi adauga si pentru Person p.
         * Ca sa nu reintru in this pentru a adauga iar relatia (cand apelez din p), voi verifica daca relatia dintre this si p nu exista deja.
         * Daca nu as face acest lucru, as intra intr-o bucla infinita.
         */
        if(relationships.get(n) == null) {
            relationships.put(n, relationship);
            if(n instanceof Person) {
                Person p = (Person) n;
                p.addRelationship((Node) this, relationship);
            }
        }
    }

    //Metoda imi va fi utila la aflarea importantei unei persoane in retea
    public int numberOfConections() { 
        return this.relationships.size();
    }
    
    //Metoda imi va fi utila la aflarea importantei unei companii in retea, si verifica daca persoana de fata are conexiune cu acea companie
    public boolean existConection(Company c) {
        if(this.relationships.get(c) != null) {
            return true;
        }
        return false;
    }

    //nodurile adiacente cu persoana curenta in graful retelei
    public List<Node> getConnections() {
        List<Node> connections = new ArrayList<>();
        for(Node n : relationships.keySet()) {
            connections.add(n);
        }
        return connections;
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        //Cand afisez o persoana, voi verifica daca i s-a setat data nasterii, in functie de asta returnand mesaje diferite.
        if(this.birthDate != null) {
            return this.name + " with birthdate " + this.birthDate;
        }
        else {
            return this.name;
        }
    }
}
