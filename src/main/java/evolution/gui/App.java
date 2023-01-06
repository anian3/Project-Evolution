package evolution.gui;
import evolution.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class App extends Application implements IPositionChangeObserver {
    private IWorldMap map;
    private SimulationEngine engine;
    GridPane gridPane = new GridPane();
    private final Vector2d [] positions={new Vector2d(2, 2), new Vector2d(3, 4),new Vector2d(5,5)};
    public void init() {
        try {
            map = new GrassField(10);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void start(Stage primaryStage) {

        TextField movesInput = new TextField();
        Button startButton = new Button("Wpisz ruchy: f,b,r lub l");
        startButton.setOnAction(action -> {
            String[] args = movesInput.getText().split(" ");
            MoveDirection [] directions = new OptionParser().parse(args);
            engine = new SimulationEngine(directions, map, this.positions);
            for(int i=0; i<engine.animals.size();i++){
                Animal animal=engine.animals.get(i);
                animal.addObserver(this);
            }
            Thread engineThread = new Thread(engine);
            engineThread.start();
        });

        HBox hBoxInterface = new HBox();
        hBoxInterface.getChildren().addAll(movesInput, startButton);

        showMap();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(gridPane, hBoxInterface);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, IWorldMap map) {
        Platform.runLater(() -> {
            System.out.println(this.map.toString());
            gridPane.getChildren().clear();
            showMap();
        });
    }
    public void showMap(){
        gridPane.getChildren().clear();

        gridPane.getColumnConstraints().add(new ColumnConstraints(35));
        gridPane.getRowConstraints().add(new RowConstraints(35));
        for (int i = 0; i <= map.boundaries.height - map.boundaries.lowerendy; i++) {

            Label label = new Label(Integer.toString(map.boundaries.lowerendy + i));
            gridPane.add(label, 0, map.boundaries.height - map.boundaries.lowerendy - i + 1, 1, 1);
        }
        for (int i = 0; i <= map.boundaries.width - map.boundaries.lowerendx; i++) {
            Label label = new Label(Integer.toString(map.boundaries.lowerendx + i));
            gridPane.add(label, i + 1, 0, 1, 1);
            GridPane.setHalignment(label, HPos.CENTER);
            for (int j = 0; j <= map.boundaries.height - map.boundaries.lowerendy; j++) {
                if (map.isOccupied(new Vector2d(map.boundaries.lowerendx + i, map.boundaries.lowerendy + j))) {
                    VBox vbox = null;
                    try {
                        vbox = new GuiElementBox(new IMapElement(map.objectAt(new Vector2d(map.boundaries.lowerendx + i, map.boundaries.lowerendy + j)))).vertical;
                    } catch (FileNotFoundException e) {
                        System.out.println("nie znaleziono obrazka");
                    }
                    gridPane.add(vbox, i + 1, map.boundaries.height - map.boundaries.lowerendy - j + 1, 1, 1);

                }
            }
        }
        gridPane.setGridLinesVisible(true);
    }
}


