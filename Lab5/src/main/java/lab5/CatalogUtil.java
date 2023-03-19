package lab5;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

public class CatalogUtil {
    public static void save(Catalog catalog, String path) throws IOException {
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.writeValue(new File(path), catalog);
    }

    public static Catalog load(String path) throws InvalidCatalogException {
       try {
           ObjectMapper objectMapper = new ObjectMapper();
           Catalog catalog = objectMapper.readValue(
                   new File(path),
                   Catalog.class);
           return catalog;
       } catch (IOException ex) {
           throw new InvalidCatalogException(ex);
       }
    }

    public static void view(Document doc) {
        System.out.println(doc);
    }
}
