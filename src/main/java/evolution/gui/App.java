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
    private boolean isHell;
    private int width;
    private int height;
    private int startGrass;
    private int grassNutrition ;
    private int everydayGrass;
    private boolean isEquator;
    private int startAnimalsCount;
    private int startEnergy;
    private int fedEnergy;
    private int energyUsed;
    private int minMutation;
    private int maxMutation;
    private int geneCount;
    private boolean isSmall;
    private boolean isCrazy;
    private HBox hBoxInterface;
    private VBox vBox = new VBox();

    public void init() {

    }

    @Override
    public void start(Stage primaryStage) {
        createButtons(primaryStage);

    }
    public void showScene(Stage primaryStage){
//        vBox.getChildren().addAll(gridPane, hBoxInterface);
//        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(hBoxInterface, 700, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void createButtons(Stage primaryStage){
        hBoxInterface = new HBox();

        Button crazyButton = new Button("Nieco szaleństwa zwierzaków.");
        crazyButton.setOnAction(action -> {
            isCrazy = true;
        });
        Button predestinedButton = new Button("Pełna predestynacja zwierzaków.");
        crazyButton.setOnAction(action -> {
            isCrazy = false;
        });

        Button randomButton = new Button("Pełna losowość mutacji.");
        randomButton.setOnAction(action -> {
            isSmall = false;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(crazyButton,predestinedButton);
            showScene(primaryStage);
        });
        Button smallButton = new Button("Lekka korekta.");
        smallButton.setOnAction(action -> {
            isSmall = true;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(crazyButton,predestinedButton);
            showScene(primaryStage);
        });
        TextField geneInput = new TextField();
        Button geneButton = new Button("Wpisz minimalną i maksymalną liczbę mutacji oraz długość genomu.");
        geneButton.setOnAction(action -> {
            String[] geneArgs = geneInput.getText().split(" ");
            minMutation = Integer.parseInt(geneArgs[0]);
            maxMutation = Integer.parseInt(geneArgs[1]);
            geneCount = Integer.parseInt(geneArgs[2]);
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(randomButton,smallButton);
            showScene(primaryStage);
        });

        TextField energyInput = new TextField();
        Button energyButton = new Button("Wpisz minimalną energię, kiedy zwierzak jest najedzony i energię zużywaną przy rozmnażaniu.");
        energyButton.setOnAction(action -> {
            String[] energyArgs = energyInput.getText().split(" ");
            fedEnergy = Integer.parseInt(energyArgs[0]);
            energyUsed = Integer.parseInt(energyArgs[1]);
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(geneInput,geneButton);
            showScene(primaryStage);
        });

        TextField animalInput = new TextField();
        Button animalButton = new Button("Wpisz startową liczbę zwierzaków i ich startową energię.");
        animalButton.setOnAction(action -> {
            String[] animalArgs = animalInput.getText().split(" ");
            startAnimalsCount = Integer.parseInt(animalArgs[0]);
            startEnergy = Integer.parseInt(animalArgs[1]);
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(energyInput,energyButton);
            showScene(primaryStage);
        });

        Button equatorButton = new Button ("Zalesione równiki.");
        equatorButton.setOnAction(action -> {
            isEquator = true;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(animalInput,animalButton);
            showScene(primaryStage);
        });
        Button toxicButton = new Button ("Toksyczne trupy");
        toxicButton.setOnAction(action -> {
            isEquator = false;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(animalInput,animalButton);
            showScene(primaryStage);
        });

        TextField grassInput = new TextField();
        Button grassCountButton = new Button("Wpisz startową liczbę roślin, energię zapewnianą przez zjedzenie jednej rośliny i liczbę roślin wzrastającą każdego dnia" +
                "(oddzielone spacją).");
        grassCountButton.setOnAction(action -> {
            String[] grassArgs = grassInput.getText().split(" ");
            startGrass = Integer.parseInt(grassArgs[0]);
            grassNutrition = Integer.parseInt(grassArgs[1]);
            everydayGrass = Integer.parseInt(grassArgs[0]);
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(equatorButton,toxicButton);
            showScene(primaryStage);
        });

        Button hellButton = new Button ("Piekielny portal.");
        hellButton.setOnAction(action -> {
            isHell = true;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(grassInput,grassCountButton);
            showScene(primaryStage);
        });

        Button globeButton = new Button ("Kula ziemska.");
        globeButton.setOnAction(action -> {
            isHell = false;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(grassInput,grassCountButton);
            showScene(primaryStage);
        });

        TextField mapInput = new TextField();
        Button mapSizeButton = new Button("Wpisz wysokość i szerokość mapy oddzielone spacją.");
        mapSizeButton.setOnAction(action -> {
            String[] args = mapInput.getText().split(" ");
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(hellButton,globeButton);
            showScene(primaryStage);
        });
        hBoxInterface.getChildren().addAll(mapInput,mapSizeButton);
        showScene(primaryStage);
    }


//    public void showMap(){
//        gridPane.getChildren().clear();
//
//        gridPane.getColumnConstraints().add(new ColumnConstraints(35));
//        gridPane.getRowConstraints().add(new RowConstraints(35));
//        for (int i = 0; i <= map.boundaries.height - map.boundaries.lowerendy; i++) {
//
//            Label label = new Label(Integer.toString(map.boundaries.lowerendy + i));
//            gridPane.add(label, 0, map.boundaries.height - map.boundaries.lowerendy - i + 1, 1, 1);
//        }
//        for (int i = 0; i <= map.boundaries.width - map.boundaries.lowerendx; i++) {
//            Label label = new Label(Integer.toString(map.boundaries.lowerendx + i));
//            gridPane.add(label, i + 1, 0, 1, 1);
//            GridPane.setHalignment(label, HPos.CENTER);
//            for (int j = 0; j <= map.boundaries.height - map.boundaries.lowerendy; j++) {
//                if (map.isOccupied(new Vector2d(map.boundaries.lowerendx + i, map.boundaries.lowerendy + j))) {
//                    VBox vbox = null;
//                    try {
//                        vbox = new GuiElementBox(new IMapElement(map.objectAt(new Vector2d(map.boundaries.lowerendx + i, map.boundaries.lowerendy + j)))).vertical;
//                    } catch (FileNotFoundException e) {
//                        System.out.println("nie znaleziono obrazka");
//                    }
//                    gridPane.add(vbox, i + 1, map.boundaries.height - map.boundaries.lowerendy - j + 1, 1, 1);
//
//                }
//            }
//        }
//        gridPane.setGridLinesVisible(true);
//    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}


