package labs.lab6;

import org.graph4j.Graph;
import org.graph4j.util.Clique;
import org.graph4j.alg.clique.MaximalCliqueIterator;

public class Triangles {
    public static long counter(Graph graph) {
        long count = 0;
        MaximalCliqueIterator alg = MaximalCliqueIterator.getInstance(graph);
        long t0 = System.currentTimeMillis();
        while(alg.hasNext()) {
            Clique clique = alg.next();
            System.out.println(clique);
            if(clique.numVertices() == 3) {
                count++;
            }
            long t1 = System.currentTimeMillis();
            if (t1 - t0 > 5000L) {
                break;
            }
        }
        return count;
    }
}
