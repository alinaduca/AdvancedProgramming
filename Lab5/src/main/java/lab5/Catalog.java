package lab5;

import java.io.Serializable;
import java.util.*;

public class Catalog implements Serializable {
    private String name;
    private List<Document> docs;

    public Catalog(String name) {
        this.name = name;
        docs = new ArrayList<>();
    }

    public Catalog() {
        docs = new ArrayList<>();
    }

    public void add(Document doc) {
        docs.add(doc);
    }

    public String getName() {
        return this.name;
    }

    public List<Document> getDocs() {
        return docs;
    }

   public Document findById(String id) {
       return docs.stream()
               .filter(d -> d.getId().equals(id))
               .findFirst()
               .orElse(null);
   }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Catalog:\n");
        for (Document document : docs) {
            sb.append(document.toString()).append("\n");
        }
        return sb.toString();
    }
}
