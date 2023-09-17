package lab5;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

public class CatalogUtil {
    public static void save(Catalog catalog, String path) throws InvalidCatalogException {
         ObjectMapper objectMapper = new ObjectMapper();
         try {
            objectMapper.writeValue(new File(path), catalog);
         }
         catch (IOException e) {
             throw new InvalidCatalogException(e);
         }
    }
}
