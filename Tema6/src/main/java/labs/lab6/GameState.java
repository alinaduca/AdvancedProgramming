package labs.lab6;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    private List<Dot> dots;
    private List<Line> lines;

    public List<Dot> getDots() {
        return dots;
    }

    public List<Line> getLines() {
        return lines;
    }

    private boolean isRedTurn;

    public GameState(List<Dot> dots, List<Line> lines, boolean isRedTurn) {
        this.dots = dots;
        this.lines = lines;
        this.isRedTurn = isRedTurn;
    }

    public GameState() {
        this.dots = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.isRedTurn = true;
    }

    public void setDots(List<Dot> dots) {
        this.dots = dots;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public void setRedTurn(boolean redTurn) {
        isRedTurn = redTurn;
    }
}
