package lab5;

import org.jgrapht.Graph;
import java.io.IOException;
import org.graph4j.GraphBuilder;
import java.net.URISyntaxException;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import freemarker.template.TemplateException;

public class Main {
    public static void main(String args[]) throws InvalidCommandException, InvalidDataException, InvalidCatalogException, URISyntaxException, IOException, TemplateException {
        Main app = new Main();
        app.testCreateSave();
    }
    private void testCreateSave() throws InvalidCommandException, InvalidDataException, InvalidCatalogException, URISyntaxException, IOException, TemplateException {
        Catalog catalog = new Catalog("MyDocuments");
        Document book = new Document("book1", "955-172", "/home/alina/Downloads/curs4.pdf");
        Document article = new Document("article1", "852-799", "https://profs.info.uaic.ro/~acf/java/labs/lab_05.html");
        AddCommand.command(catalog, book);
        AddCommand.command(catalog, article);
        InfoCommand.command(book);
        InfoCommand.command(article);
        int n = 10;
        for(int i = 0; i < n; i++) {
            StringBuilder title = new StringBuilder("book");
            title.append(i);
            Document book1 = new Document(title.toString(), "955-173", "/home/alina/Downloads/curs4.pdf");
            StringBuilder title2 = new StringBuilder("book");
            title2.append(i);
            Document article1 = new Document(title.toString(), "852-798", "https://www.youtube.com");
            AddCommand.command(catalog, book1);
            AddCommand.command(catalog, article1);
            InfoCommand.command(book1);
            InfoCommand.command(article1);
        }
        org.graph4j.Graph graph = GraphBuilder.empty().estimatedNumVertices(10 * n).buildGraph();
        Graph<Document, DefaultEdge> graph2 = new SimpleGraph<>(DefaultEdge.class);
        for(Document doc : catalog.getDocs()) {
            graph.addVertex(doc);
            graph2.addVertex(doc);
        }
        for(Document doc1 : catalog.getDocs()) {
            for(Document doc2 : catalog.getDocs()) {
                if(!doc1.equals(doc2)) {
                    for(var key1 : doc1.getTags().keySet()) {
                        for(var key2 : doc2.getTags().keySet()) {
                            if(key1.equals(key2) && doc1.getTags().get(key1).equals(doc2.getTags().get(key2))) {
                                catalog.addRelatedDocuments(doc1, doc2);
                                graph.addEdge(doc1, doc2);
                                graph2.addEdge(doc1, doc2);
                            }
                        }
                    }
                }
            }
        }
        long startTime = System.currentTimeMillis();
        BrownBacktrackColoring coloring = new BrownBacktrackColoring(graph);
        System.out.println("Coloring with Graph4J:");
        System.out.println(coloring.getColoring());
        long endTime = System.currentTimeMillis();
        long elapsedTime1 = endTime - startTime;

        startTime = System.currentTimeMillis();
        Smth<Document, DefaultEdge> coloring2 = new Smth<>(graph2);
        System.out.println("Coloring with JGraphT:");
        System.out.println(coloring2.getColoring());
        endTime = System.currentTimeMillis();
        long elapsedTime2 = endTime - startTime;

        System.out.println("Coloring with Graph4J: " + elapsedTime1 + " ms.");
        System.out.println("Coloring with JGraphT: " + elapsedTime2 + " ms.");
    }
}