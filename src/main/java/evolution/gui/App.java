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

    }

    @Override
    public void start(Stage primaryStage) {
        createButtons();
        engine = new SimulationEngine(map, this.positions);
        for(int i=0; i<engine.animals.size();i++){
            Animal animal=engine.animals.get(i);
            animal.addObserver(this);
        }
        Thread engineThread = new Thread(engine);
        engineThread.start();
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

    public void createButtons(){
        TextField mapInput = new TextField();
        Button mapSizeButton = new Button("Wpisz wysokość i szerokość mapy oddzielone spacją.");
        mapSizeButton.setOnAction(action -> {
            String[] args = mapInput.getText().split(" ");
            int width = Integer.parseInt(args[0]);
            int height = Integer.parseInt(args[1]);
        });
        Button hellButton = new Button ("Piekielny portal.");
        hellButton.setOnAction(action -> {
            boolean isHell = true;
        });
        Button globeButton = new Button ("Kula ziemska.");
        globeButton.setOnAction(action -> {
            boolean isHell = false;
        });
        TextField grassInput = new TextField();
        Button grassCountButton = new Button("Wpisz startową liczbę roślin, energię zapewnianą przez zjedzenie jednej rośliny i liczbę roślin wzrastającą każdego dnia" +
                "(oddzielone spacją).");
        grassCountButton.setOnAction(action -> {
            String[] grassArgs = mapInput.getText().split(" ");
            int startGrass = Integer.parseInt(grassArgs[0]);
            int grassNutrition = Integer.parseInt(grassArgs[1]);
            int everydayGrass = Integer.parseInt(grassArgs[0]);
        });
        Button equatorButton = new Button ("Zalesione równiki.");
        hellButton.setOnAction(action -> {
            boolean isEquator = true;
        });
        Button toxicButton = new Button ("Toksyczne trupy");
        globeButton.setOnAction(action -> {
            boolean isEquator = false;
        });
        TextField animalInput = new TextField();
        Button animalButton = new Button("Wpisz startową liczbę zwierzaków i ich startową energię.");
        animalButton.setOnAction(action -> {
            String[] animalArgs = animalInput.getText().split(" ");
            int startAnimalsCount = Integer.parseInt(animalArgs[0]);
            int startEnergy = Integer.parseInt(animalArgs[1]);
        });
        TextField energyInput = new TextField();
        Button energyButton = new Button("Wpisz minimalną energię, kiedy zwierzak jest najedzony i energię zużywaną przy rozmnażaniu.");
        energyButton.setOnAction(action -> {
            String[] energyArgs = energyInput.getText().split(" ");
            int fedEnergy = Integer.parseInt(energyArgs[0]);
            int energyUsed = Integer.parseInt(energyArgs[1]);
        });
        TextField geneInput = new TextField();
        Button geneButton = new Button("Wpisz minimalną i maksymalną liczbę mutacji oraz długość genomu.");
        geneButton.setOnAction(action -> {
            String[] geneArgs = geneInput.getText().split(" ");
            int minMutation = Integer.parseInt(geneArgs[0]);
            int maxMutation = Integer.parseInt(geneArgs[1]);
            int geneCount = Integer.parseInt(geneArgs[2]);
        });
        Button randomButton = new Button("Pełna losowość mutacji.");
        randomButton.setOnAction(action -> {
            boolean isSmall = false;
        });
        Button smallButton = new Button("Lekka korekta.");
        smallButton.setOnAction(action -> {
            boolean isSmall = true;
        });
        Button crazyButton = new Button("Nieco szaleństwa zwierzaków.");
        crazyButton.setOnAction(action -> {
            boolean isCrazy = true;
        });
        Button predestinedButton = new Button("Pełna predestynacja zwierzaków.");
        crazyButton.setOnAction(action -> {
            boolean isCrazy = false;
        });
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


