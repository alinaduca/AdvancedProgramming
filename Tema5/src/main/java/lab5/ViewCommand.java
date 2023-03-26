package lab5;

import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class ViewCommand implements Command {
    public static void command(Document document) throws InvalidCommandException {
        String location = document.getLocation();
        try {
            File file = new File(location);
            if(file.exists()) {
                Desktop.getDesktop().open(file);
            }
            else {
                URI uri = new URI(location);
                if (uri.isAbsolute()) { // external URL
                    Desktop.getDesktop().browse(uri);
                }
                else { // location is a path on the local file system
                    Exception e = new Exception();
                    throw new InvalidCommandException(e);
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new InvalidCommandException(e);
        }
    }
}