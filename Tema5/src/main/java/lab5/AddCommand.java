package lab5;
public class AddCommand implements Command {
    public static void command(Catalog catalog, Document doc) throws InvalidCatalogException, InvalidDataException {
        if(catalog == null) {
            Exception e = new Exception();
            throw new InvalidCatalogException(e);
        }
        if(doc == null) {
            Exception e = new Exception();
            throw new InvalidDataException(e);
        }
        catalog.add(doc);
    }
}
