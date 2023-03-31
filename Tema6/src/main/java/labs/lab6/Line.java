package labs.lab6;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import java.io.Serializable;

public class Line implements Serializable {
    private Dot A;
    private Dot B;
    private String color;

    public String getColor() {
        return color;
    }

    public Line(Dot A, Dot B) {
        this.A = A;
        this.B = B;
        this.color = "none";
    }

    public Dot getA() {
        return this.A;
    }

    public Dot getB() {
        return this.B;
    }

    public boolean colorLine(Canvas canvas, boolean isRedTurn) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if(this.color.equals("none")) {
            if(isRedTurn) {
                gc.setStroke(Color.RED);
                this.color = "red";
                isRedTurn = false;
                A.addConnection(B, this.color);
                B.addConnection(A, this.color);
            }
            else {
                gc.setStroke(Color.CYAN);
                this.color = "blue";
                isRedTurn = true;
                A.addConnection(B, this.color);
                B.addConnection(A, this.color);
            }
            gc.setLineWidth(1);
            gc.strokeLine(A.getX(), A.getY(), B.getX(), B.getY());
        }
        return isRedTurn;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Line))
            return false;
        Line line = (Line) obj;
        return line.getA().equals(this.A) && line.getB().equals(this.B) || line.getA().equals(this.B) && line.getB().equals(this.A);
    }
}