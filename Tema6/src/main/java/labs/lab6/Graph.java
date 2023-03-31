package labs.lab6;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.*;

public class Graph implements Serializable {
    private List<Dot> dots;

    private int numberOfDots;
    private int NumberOfLines;
    private List<Line> lines;

    public Graph() {
        this.dots = new ArrayList<>();
        this.lines = new ArrayList<>();
    }

    public List<Dot> getDots() {
        return dots;
    }

    public void setDots(List<Dot> dots) {
        this.dots = dots;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setNumberOfDots(int numberOfDots) {
        this.numberOfDots = numberOfDots;
    }

    public void setNumberOfLines(int numberOfLines) {
        NumberOfLines = numberOfLines;
    }

    public void addDot(Dot dot) {
        if(dots.size() == 0) {
            dots.add(dot);
        }
        else {
            boolean exist = false;
            for(Dot d : dots) {
                if(d.equals(dot)) {
                    exist = true;
                }
            }
            if(!exist) {
                dots.add(dot);
            }
        }
    }

    public void addLine(Line line) {
        if(!intersect(line)) {
            lines.add(line);
        }
    }

    public boolean intersect(Line line) {
        if(lines.size() == 0) {
            return false;
        }
        for(Line l : lines) {
            if(l.equals(line)) {
                return true;
            }
        }
        return false;
    }

    public Line getLineAt(double x, double y) {
        for(Line line : lines) {
            double distance = Math.abs((line.getB().getY() - line.getA().getY()) * x
                    - (line.getB().getX() - line.getA().getX()) * y + line.getB().getX() * line.getA().getY()
                    - line.getB().getY() * line.getA().getX()) / Math.sqrt(Math.pow(line.getB().getY() - line.getA().getY(), 2)
                    + Math.pow(line.getB().getX() - line.getA().getX(), 2));
            if (distance < 5.0) {
                return line;
            }
        }
        return null;
    }

    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setLineWidth(1);
        for(Line line : lines) {
            if(line.getColor().equals("red")) {
                gc.setStroke(Color.RED);
            }
            else if(line.getColor().equals("blue")) {
                gc.setStroke(Color.CYAN);
            }
            else {
                gc.setStroke(Color.GRAY);
            }
            gc.strokeLine(line.getA().getX(), line.getA().getY(), line.getB().getX(), line.getB().getY());
        }
        for (Dot dot : dots) {
            gc.setFill(Color.BLACK);
            gc.fillOval(dot.getX() - 4, dot.getY() - 4, 4 * 2, 4 * 2);
        }
    }

    public void panouRezultat(String color, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.strokeText(color + " win", 150, 20);
    }

    public void removeAll() {
        this.dots = new ArrayList<>();
        this.lines = new ArrayList<>();
        numberOfDots = 0;
        NumberOfLines = 0;
    }

    public void checkWin(boolean wasRed, Canvas canvas) {
        String searchedColor;
        if(wasRed) {
            searchedColor = "red";
        }
        else {
            searchedColor = "blue";
        }
        Set<Dot> visited = new HashSet<>();
        for(Dot dot : dots) {
            visited.add(dot);
            for(Dot neighbor : dot.getConnections(searchedColor)) {
                if(visited.contains(neighbor)) {
                    continue;
                }
                for(Dot secondNeighbor : dot.getConnections(searchedColor)) {
                    if(visited.contains(secondNeighbor) || secondNeighbor == dot || secondNeighbor == neighbor) {
                        continue;
                    }
                    if(neighbor.getConnections(searchedColor).contains(secondNeighbor)) {
                        panouRezultat(searchedColor, canvas);
                        return;
                    }
                }
            }
        }
    }
}



