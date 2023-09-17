package labs.lab6;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.*;
import java.util.*;

public class PositionalGame extends Application {
    private int numDots = 6;
    private int numLines;
    private double lineProbability = 1.0;
    private List<Line> lines = new ArrayList<>();
    private List<Dot> dots = new ArrayList<>();
    private boolean isRedTurn = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Label numDotsLabel = new Label("Number of dots: ");
        TextField numDotsTextField = new TextField(Integer.toString(numDots));
        Label numLinesLabel = new Label("   Line probability: ");
        TextField numLinesTextField = new TextField(Double.toString(lineProbability));
        Button newGameButton = new Button("New Game");
        Canvas canvas = new Canvas(700, 300);
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numDots = Integer.parseInt(numDotsTextField.getText());
                lineProbability = Double.parseDouble(numLinesTextField.getText());
                newGame(canvas);
            }
        });
        HBox configPanel = new HBox(numDotsLabel, numDotsTextField, numLinesLabel, numLinesTextField, newGameButton);
        configPanel.setAlignment(Pos.CENTER);
        root.setTop(configPanel);

        canvas.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                Line selectedLine = getLineAt(event.getX(), event.getY());
                if (selectedLine != null) {
                    colorLine(selectedLine, canvas);
                }
            }
        });
        root.setCenter(canvas);

        //control panel
        Button loadButton  = new Button("Load");
        Button saveButton  = new Button("Save");
        Button resetButton = new Button("Reset");
        Button exitButton  = new Button("Exit");
        HBox controlPanel  = new HBox(loadButton, saveButton, resetButton, exitButton);
        controlPanel.setAlignment(Pos.CENTER);
        root.setBottom(controlPanel);
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
        newGame(canvas);
    }

    private void newGame(Canvas canvas) {
        lines.clear();
        dots.clear();

        //crearea punctelor
        Random random = new Random();
        for (int i = 0; i < numDots; i++) {
            double angle = 2 * Math.PI * i / numDots;
            double x = 200 + 150 + 150 * Math.cos(angle);
            double y = 150 + 150 * Math.sin(angle);
            dots.add(new Dot(x, y));
        }

        if(lineProbability > 1) lineProbability = 1;
        else if(lineProbability < 0) lineProbability = 0;

        numLines = numDots * (numDots - 1) / 2;
        numLines = (int) (lineProbability * numLines);

        //crearea liniilor
        while(lines.size() < numLines) {
            int dotIndex1 = random.nextInt(numDots);
            int dotIndex2 = random.nextInt(numDots);
            if (dotIndex1 == dotIndex2) {
                continue;
            }
            Dot dot1 = dots.get(dotIndex1);
            Dot dot2 = dots.get(dotIndex2);
            Line line = new Line(dot1.getX(), dot1.getY(), dot2.getX(),  dot2.getY());
            if(!intersect(line)) {
                lines.add(line);
            }
        }
        redraw(canvas);
    }

    //la fiecare apasare a butonului "New game" voi redesena board-ul, luand in considerare parametrii indicati de utilizator (numarul de puncte si probabilitatea liniilor)
    private void redraw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(1);
        for (Line line : lines) {
            gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        }
        for (Dot dot : dots) {
            gc.setFill(Color.BLACK);
            gc.fillOval(dot.getX() - 4, dot.getY() - 4, 4 * 2, 4 * 2);
        }
    }

    //verific daca linia data ca parametru se intersecteaza cu celelalte linii deja adaugate
    private boolean intersect(Line line) {
        for(Line l : lines) {
            if((l.getStartX() == line.getStartX() && l.getStartY() == line.getStartY() && l.getEndX() == line.getEndX() && l.getEndY() == line.getEndY()) ||
                l.getStartY() == line.getEndY() && l.getEndX() == line.getStartX() && l.getEndY() == line.getStartY() && l.getStartX() == line.getEndX()) {
                return true;
            }
        }
        return false;
    }

    //dandu-mi-se coordonatele locului in care utilizatorul a facut click, voi itera prin linii si voi verifica ce linie a fost selectata, pentru a o returna in vederea colorarii
    //precizia va fi de 5 pixeli
    private Line getLineAt(double x, double y) {
        for(Line line : lines) {
            double distance = Math.abs((line.getEndY() - line.getStartY()) * x
                    - (line.getEndX() - line.getStartX()) * y + line.getEndX() * line.getStartY()
                    - line.getEndY() * line.getStartX()) / Math.sqrt(Math.pow(line.getEndY() - line.getStartY(), 2)
                    + Math.pow(line.getEndX() - line.getStartX(), 2));
            if (distance < 5.0) {
                return line;
            }
        }
        return null;
    }

    //colorez linia pe care o primesc ca si parametru, prin suprascriere
    private void colorLine(Line line, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if(isRedTurn) {
            gc.setStroke(Color.RED);
            isRedTurn = false;
        }
        else {
            gc.setStroke(Color.CYAN);
            isRedTurn = true;
        }
        gc.setLineWidth(1);
        gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
    }
}