package labs.lab6;

import java.util.concurrent.atomic.AtomicReference;
import javafx.scene.image.WritableImage;
import javafx.scene.SnapshotParameters;
import javafx.embed.swing.SwingFXUtils;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javax.imageio.ImageIO;
import javafx.geometry.Pos;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.*;
import java.io.File;
import java.util.*;
import java.io.*;

public class PositionalGame extends Application {
    private static final String filename = "game.ser";
    private int numDots = 6;
    private double lineProbability = 1.0;
    Line selectedLine = null;
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
        AtomicReference<Graph> graph = new AtomicReference<>(new Graph());
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numDots = Integer.parseInt(numDotsTextField.getText());
                lineProbability = Double.parseDouble(numLinesTextField.getText());
                newGame(canvas, graph.get());
                isRedTurn = true;
            }
        });
        HBox configPanel = new HBox(numDotsLabel, numDotsTextField, numLinesLabel, numLinesTextField, newGameButton);
        configPanel.setAlignment(Pos.CENTER);
        root.setTop(configPanel);
        canvas.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                selectedLine = graph.get().getLineAt(event.getX(), event.getY());
                boolean isBlueTurn = isRedTurn;
                if (selectedLine != null) {
                    isBlueTurn = selectedLine.colorLine(canvas, isRedTurn);
                }
                if(isBlueTurn != isRedTurn) {
                    graph.get().checkWin(isRedTurn, canvas);
                    isRedTurn = isBlueTurn;
                }
            }
        });
        root.setCenter(canvas);
        // Control panel
        Button loadButton  = new Button("Load");
        Button saveButton  = new Button("Save");
        saveButton.setOnAction(e -> {
            WritableImage image = new WritableImage(
                    (int) root.getBoundsInParent().getWidth(),
                    (int) root.getBoundsInParent().getHeight()
            );
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(javafx.scene.paint.Color.TRANSPARENT);
            root.snapshot(params, image);
            try {
                File file = new File("game_board.png");
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                GameState gs = new GameState(graph.get().getDots(), graph.get().getLines(), isRedTurn);
                FileOutputStream fileOut = new FileOutputStream("game.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(gs);
                out.close();
                fileOut.close();
                System.out.println("Game saved to game.ser");
            } catch (IOException i) {
                i.printStackTrace();
            }
        });

        loadButton.setOnAction(event -> {
            try {
                FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                GameState gs = (GameState) in.readObject();
                in.close();
                fileIn.close();
                System.out.println("Game loaded from " + filename);
                graph.get().setDots(gs.getDots());
                graph.get().setLines(gs.getLines());
                graph.get().draw(canvas);
            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("Graph class not found");
                c.printStackTrace();
                return;
            }
        });
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> {
            graph.get().removeAll();
            newGame(canvas, graph.get());
        });
        Button exitButton  = new Button("Exit");
        exitButton.setOnAction(event -> {
            System.exit(0);
        });
        HBox controlPanel  = new HBox(loadButton, saveButton, resetButton, exitButton);
        controlPanel.setAlignment(Pos.CENTER);
        root.setBottom(controlPanel);
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
        newGame(canvas, graph.get());
    }

    private void newGame(Canvas canvas, Graph graph) {

        graph.removeAll();
        graph.setNumberOfDots(numDots);

        if(lineProbability > 1) lineProbability = 1;
        else if(lineProbability < 0) lineProbability = 0;

        int numLines = numDots * (numDots - 1) / 2;
        numLines = (int) (lineProbability * numLines);
        graph.setNumberOfLines(numLines);
        // Create dots
        Random random = new Random();
        for (int i = 0; i < numDots; i++) {
            double angle = 2 * Math.PI * i / numDots;
            double x = 200 + 150 + 150 * Math.cos(angle);
            double y = 150 + 150 * Math.sin(angle);
            graph.addDot(new Dot(x, y));
        }
        while(graph.getLines().size() < numLines) {
            int dotIndex1 = random.nextInt(numDots);
            int dotIndex2 = random.nextInt(numDots);
            if (dotIndex1 == dotIndex2) {
                continue;
            }
            Dot dot1 = graph.getDots().get(dotIndex1);
            Dot dot2 = graph.getDots().get(dotIndex2);
            Line line = new Line(dot1, dot2);
            if(!graph.intersect(line)) {
                graph.addLine(line);
            }
        }
        graph.draw(canvas);
    }
}