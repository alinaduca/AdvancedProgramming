package lab5;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LoadCommand implements Command {
    public static Catalog command(String path) throws InvalidCatalogException {
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
}
