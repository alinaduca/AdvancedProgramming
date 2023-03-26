package lab5;

public class Main {
    public static void main(String args[]) throws InvalidCommandException, InvalidDataException, InvalidCatalogException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() throws InvalidCommandException, InvalidDataException, InvalidCatalogException {
        Catalog catalog = new Catalog("MyDocuments");
        Document book = new Document("book1", "955-172", "/home/alina/Downloads/curs4.pdf");
        Document article = new Document("article1", "852-799", "https://profs.info.uaic.ro/~acf/java/labs/lab_05.html");
        AddCommand.command(catalog, book);
        AddCommand.command(catalog, article);
        ListCommand.command(catalog.getDocs());
        ViewCommand.command(article);
        ReportCommand.command(catalog);
        CatalogUtil.save(catalog, "catalog.json");
    }
    
     private void testLoadView() throws InvalidCatalogException {
         Catalog catalog = LoadCommand.command("catalog.json");
         System.out.println(catalog);
     }
}