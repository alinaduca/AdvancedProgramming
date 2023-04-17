package lab5;

import java.io.*;
import java.net.*;
import org.apache.tika.parser.*;
import org.xml.sax.SAXException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.exception.TikaException;

public class InfoCommand implements Command {
    public static void command(Document doc) throws InvalidDataException, InvalidCommandException {
        if(doc == null) {
            Exception e = new Exception();
            throw new InvalidDataException(e);
        }
        String location = doc.getLocation();
        try {
            URI uri = new URI(location);
            if (uri.isAbsolute()) { // external URL
                URL url = new URL(location);
                Parser parser = new AutoDetectParser();
                BodyContentHandler handler = new BodyContentHandler();
                Metadata metadata = new Metadata();   //empty metadata object
                InputStream inputstream = null;
                try {
                    inputstream = url.openStream();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                ParseContext context = new ParseContext();
                try {
                    parser.parse(inputstream, handler, metadata, context);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (SAXException e) {
                    throw new RuntimeException(e);
                } catch (TikaException e) {
                    throw new RuntimeException(e);
                }
//                System.out.println(handler.toString());
                //getting the list of all metadata elements
                String[] metadataNames = metadata.names();

                for(String name : metadataNames) {
                    System.out.println(name + ": " + metadata.get(name));
                    doc.addTag(name, metadata.get(name));
                }
            }
            else {
                File file = new File(doc.getLocation());
                Parser parser = new AutoDetectParser();
                BodyContentHandler handler = new BodyContentHandler();
                Metadata metadata = new Metadata();   //empty metadata object
                FileInputStream inputstream = null;
                try {
                    inputstream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                ParseContext context = new ParseContext();
                try {
                    parser.parse(inputstream, handler, metadata, context);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (SAXException e) {
                    throw new RuntimeException(e);
                } catch (TikaException e) {
                    throw new RuntimeException(e);
                }
                //getting the list of all metadata elements
                String[] metadataNames = metadata.names();
                for(String name : metadataNames) {
                    System.out.println(name + ": " + metadata.get(name));
                    doc.addTag(name, metadata.get(name));
                }
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}