package lab5;

import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException, InvalidCatalogException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    //Pentru a crea un catalog si pentru a-l salva intr-un fisier JSON.
    private void testCreateSave() throws IOException {
        Catalog catalog = new Catalog("MyDocuments");
        var book = new Document("book1", "955-172", "https://profs.info.uaic.ro/~acf/java/labs/slides/lab_05.pdf");
        var article = new Document("article1", "852-799", "https://profs.info.uaic.ro/~acf/java/labs/lab_05.html");
        catalog.add(book);
        catalog.add(article);
        CatalogUtil.save(catalog, "catalog.json");
    }
    
    //Pentru a incarca continutul fisierului JSON si pentru a afisa un document dupa ID-ul specificat.
    private void testLoadView() throws InvalidCatalogException {
        Catalog catalog = CatalogUtil.load("catalog.json");
        CatalogUtil.view(catalog.findById("955-172"));
    }
}