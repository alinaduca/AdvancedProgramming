package lab5;

import java.io.Serializable;
import java.util.*;

public class Catalog implements Serializable {
    private String name;
    private List<Document> docs;
    private Map<Document, List<Document>> relatedDocuments;

    public Catalog(String name) {
        this.name = name;
        this.docs = new ArrayList<>();
        this.relatedDocuments = new HashMap<>();
    }

    public Catalog() {
        this.docs = new ArrayList<>();
        this.relatedDocuments = new HashMap<>();
    }

    public void add(Document doc) {
        this.docs.add(doc);
    }

    public String getName() {
        return this.name;
    }

    public List<Document> getDocs() {
        return this.docs;
    }

   public Document findById(String id) {
       return docs.stream()
               .filter(d -> d.getId().equals(id))
               .findFirst()
               .orElse(null);
   }

   public void addRelatedDocuments(Document doc1, Document doc2) {
        List<Document> newList;
        if(!relatedDocuments.containsKey(doc1)) {
            newList = new ArrayList<>();
        }
        else {
            newList = relatedDocuments.get(doc1);
        }
        newList.add(doc2);
        relatedDocuments.put(doc1, newList);
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
