package labs.lab6;

import java.util.List;
import java.util.ArrayList;
import org.graph4j.GraphBuilder;
import java.util.concurrent.atomic.AtomicReference;

public class GameAI {
    private AtomicReference<Graph> graph;
    private String opponentColor;
    private String myColor;
    private List<Line> opponentLines;
    private List<Line> myLines;
    private List<Line> availableLines;

    public GameAI(AtomicReference<Graph> board, boolean isRedTurn) {
        this.graph = board;
        this.opponentLines = new ArrayList<>();
        this.myLines = new ArrayList<>();
        this.availableLines = new ArrayList<>();
        if(isRedTurn == true) {
            this.myColor = "blue";
            this.opponentColor = "red";
        }
        else {
            this.myColor = "red";
            this.opponentColor = "blue";
        }
        for(Line line : graph.get().getLines()) {
            if(line.getColor().equals(opponentColor)) {
                opponentLines.add(line);
            }
            else {
                if(line.getColor().equals(myColor)) {
                    myLines.add(line);
                }
                else {

                    availableLines.add(line);
                }
            }
        }
    }

    public void addOpponentLine(Line line) {
        opponentLines.add(line);
        availableLines.remove(line);
    }

    public Line getBestMove() {
        Line bestMove = null;
        if(myLines.size() == 0) { //jocul abia a inceput si nu este desenata nicio linie de aceeasi culoare cu a AI-ului
            bestMove = availableLines.get(0);
            myLines.add(bestMove);
            availableLines.remove(bestMove);
        }
        else {
            //Verific daca exista o linie disponibila, astfel incat, prin colorarea ei sa se castige jocul.
            for(Line edge : availableLines) {
                List<Line> candidateLines = new ArrayList<>(myLines);
                candidateLines.add(edge);
                org.graph4j.Graph graph1 = GraphBuilder.empty().estimatedNumVertices(graph.get().getNumberOfDots()).buildGraph();
                for(Dot vertex : graph.get().getDots()) {
                    graph1.addVertex(vertex);
                }
                for(Line line : candidateLines) {
                    if(line != null) {
                        graph1.addEdge(line.getA(), line.getB());
                    }
                }
                long count = Triangles.counter(graph1);
                System.out.println("count1=" + count);
                if (count > 0) {
                    return edge;
                }
            }

            //Daca nu am gasit nicio muchie astfel incat sa pot castiga jocul, atunci incerc sa vad daca nu cumva pot bloca un eventual castig al oponentului meu.
            for(Line edge : availableLines) {
                List<Line> candidateLines = new ArrayList<>(opponentLines);
                candidateLines.add(edge);
                org.graph4j.Graph graph1 = GraphBuilder.empty().estimatedNumVertices(graph.get().getNumberOfDots()).buildGraph();
                for(Dot vertex : graph.get().getDots()) {
                    graph1.addVertex(vertex);
                }
                for(Line line : candidateLines) {
                    graph1.addEdge(line.getA(), line.getB());
                }
                long count = Triangles.counter(graph1);
//                System.out.println("count1=" + count);
                if (count > 0) {
                    bestMove = edge;
                    break;
                }
            }
            myLines.add(bestMove);
            availableLines.remove(bestMove);
            if(bestMove == null) {
                //Sigur am cel putin o muchie colorata cu aceeasi culoare, deci o caut si incerc sa vad daca as putea forma triunghi cu ea.
                for(int i = 0; i < availableLines.size() - 1; i++) {
                    for(int j = i + 1; j < availableLines.size(); j++) {
                        Line edge1 = availableLines.get(i);
                        Line edge2 = availableLines.get(j);
                        List<Line> candidateLines = new ArrayList<>(myLines);
                        candidateLines.add(edge1);
                        candidateLines.add(edge2);
                        org.graph4j.Graph graph1 = GraphBuilder.empty().estimatedNumVertices(graph.get().getNumberOfDots()).buildGraph();
                        for(Dot vertex : graph.get().getDots()) {
                            graph1.addVertex(vertex);
                        }
                        for(Line line : candidateLines) {
                            if(line != null) {
                                graph1.addEdge(line.getA(), line.getB());
                            }
                        }
                        long count = Triangles.counter(graph1);
                        System.out.println("count1=" + count);
                        if (count > 0) {
                            bestMove = edge1;
                        }
                    }
                }
                myLines.add(bestMove);
                availableLines.remove(bestMove);
            }
        }
        return bestMove;
    }
}
