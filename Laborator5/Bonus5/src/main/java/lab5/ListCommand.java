package lab5;
import java.util.*;

public class ListCommand implements Command {
    public static void command(List<Document> docs) {
        for(Document doc : docs)
            System.out.println(doc);
    }
}
