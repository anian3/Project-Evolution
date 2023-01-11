package evolution.gui;

import evolution.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class App extends Application implements IActionObserver{
    private IWorldMap map;
    private SimulationEngine engine;
    private final GridPane gridPane = new GridPane();
    private boolean isGlobe;
    private int width;
    private int height;
    private int startGrass;
    private int grassNutrition;
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
    private Stage stage = new Stage();
    private Scene scene = new Scene(gridPane);

    public void init() {

    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        createButtons(primaryStage);
    }

    public void showScene(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void createButtons(Stage primaryStage) {

        hBoxInterface = new HBox();

        Button startButton = new Button("START");
        startButton.setOnAction(action -> {
            engine = new SimulationEngine(width, height, isEquator, isGlobe, startGrass, grassNutrition, everydayGrass,
                    startAnimalsCount, startEnergy, fedEnergy, energyUsed, minMutation, maxMutation, isSmall, geneCount, isCrazy);
            engine.addObserver(this);
            this.map = engine.map;
            Thread engineThread = new Thread(engine);
            engineThread.start();
        });

        Button crazyButton = new Button("Nieco szalenstwa zwierzakow.");
        crazyButton.setOnAction(action -> {
            isCrazy = true;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(startButton);
            Scene scene = new Scene(hBoxInterface, 700, 400);
            showScene(primaryStage, scene);

        });
        Button predestinedButton = new Button("Pelna predestynacja zwierzakow.");
        predestinedButton.setOnAction(action -> {
            isCrazy = false;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(startButton);
            Scene scene = new Scene(hBoxInterface, 700, 400);
            showScene(primaryStage, scene);
        });

        Button randomButton = new Button("Pelna losowosc mutacji.");
        randomButton.setOnAction(action -> {
            isSmall = false;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(crazyButton, predestinedButton);
            Scene scene = new Scene(hBoxInterface, 700, 400);
            showScene(primaryStage, scene);
        });
        Button smallButton = new Button("Lekka korekta.");
        smallButton.setOnAction(action -> {
            isSmall = true;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(crazyButton, predestinedButton);
            Scene scene = new Scene(hBoxInterface, 700, 400);
            showScene(primaryStage, scene);
        });
        TextField geneInput = new TextField();
        Button geneButton = new Button("Wpisz minimalna i maksymalna liczbe mutacji oraz dlugosc genomu.");
        geneButton.setOnAction(action -> {
            String[] geneArgs = geneInput.getText().split(" ");
            minMutation = Integer.parseInt(geneArgs[0]);
            maxMutation = Integer.parseInt(geneArgs[1]);
            geneCount = Integer.parseInt(geneArgs[2]);
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(randomButton, smallButton);
            Scene scene = new Scene(hBoxInterface, 700, 400);
            showScene(primaryStage, scene);
        });

        TextField energyInput = new TextField();
        Button energyButton = new Button("Wpisz minimalna energie, kiedy zwierzak jest najedzony i energie zuzywana przy rozmnazaniu.");
        energyButton.setOnAction(action -> {
            String[] energyArgs = energyInput.getText().split(" ");
            fedEnergy = Integer.parseInt(energyArgs[0]);
            energyUsed = Integer.parseInt(energyArgs[1]);
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(geneInput, geneButton);
            Scene scene = new Scene(hBoxInterface, 700, 400);
            showScene(primaryStage, scene);
        });

        TextField animalInput = new TextField();
        Button animalButton = new Button("Wpisz startowa liczbe zwierzakow i ich startowa energie.");
        animalButton.setOnAction(action -> {
            String[] animalArgs = animalInput.getText().split(" ");
            startAnimalsCount = Integer.parseInt(animalArgs[0]);
            startEnergy = Integer.parseInt(animalArgs[1]);
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(energyInput, energyButton);
            Scene scene = new Scene(hBoxInterface, 700, 400);
            showScene(primaryStage, scene);
        });

        Button equatorButton = new Button("Zalesione rowniki.");
        equatorButton.setOnAction(action -> {
            isEquator = true;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(animalInput, animalButton);
            Scene scene = new Scene(hBoxInterface, 700, 400);
            showScene(primaryStage, scene);
        });
        Button toxicButton = new Button("Toksyczne trupy");
        toxicButton.setOnAction(action -> {
            isEquator = false;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(animalInput, animalButton);
            Scene scene = new Scene(hBoxInterface, 700, 400);
            showScene(primaryStage, scene);
        });

        TextField grassInput = new TextField();
        Button grassCountButton = new Button("Wpisz startowa liczbe roslin, energie zapewniana przez zjedzenie jednej rosliny i liczbe roslin wzrastajaca kazdego dnia.");
        grassCountButton.setOnAction(action -> {
            String[] grassArgs = grassInput.getText().split(" ");
            startGrass = Integer.parseInt(grassArgs[0]);
            grassNutrition = Integer.parseInt(grassArgs[1]);
            everydayGrass = Integer.parseInt(grassArgs[0]);
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(equatorButton, toxicButton);
            Scene scene = new Scene(hBoxInterface, 700, 400);
            showScene(primaryStage, scene);
        });

        Button hellButton = new Button("Piekielny portal.");
        hellButton.setOnAction(action -> {
            isGlobe = false;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(grassInput, grassCountButton);
            Scene scene = new Scene(hBoxInterface, 700, 400);
            showScene(primaryStage, scene);
        });

        Button globeButton = new Button("Kula ziemska.");
        globeButton.setOnAction(action -> {
            isGlobe = true;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(grassInput, grassCountButton);
            Scene scene = new Scene(hBoxInterface, 700, 400);
            showScene(primaryStage, scene);
        });

        TextField mapInput = new TextField();
        Button mapSizeButton = new Button("Wpisz wysokosc i szerokosc mapy oddzielone spacja.");
        mapSizeButton.setOnAction(action -> {
            String[] args = mapInput.getText().split(" ");
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
            width -= 1;
            height -= 1;
            hBoxInterface = new HBox();
            hBoxInterface.getChildren().addAll(hellButton, globeButton);
            Scene scene = new Scene(hBoxInterface, 700, 400);
            showScene(primaryStage, scene);
        });
        hBoxInterface.getChildren().addAll(mapInput, mapSizeButton);
        Scene scene = new Scene(hBoxInterface, 700, 400);
        showScene(primaryStage, scene);
    }

    public void actionHappened(){
        Platform.runLater(this::showMap);
    }

    public void showMap(){
        gridPane.setGridLinesVisible(false);
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getChildren().clear();
        gridPane.setGridLinesVisible(true);
        for (int i = 0; i <= map.getMapEnd().getX(); i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(40));
        }
        for (int j = 0; j <= map.getMapEnd().getY(); j++) {
            gridPane.getRowConstraints().add(new RowConstraints(40));
        }
        for (int i = 0; i <= map.getMapEnd().getX(); i++) {
            for (int j = 0; j <= map.getMapEnd().getY(); j++) {
                Vector2d position = new Vector2d(i, j);
                VBox vbox = new VBox();
                if (map.isAnimalAt(position)) {
                    try {
                        vbox = new GuiElementBox(map.randomAnimalAt(position)).vertical;
                    } catch (FileNotFoundException e) {
                        System.out.println("nie znaleziono obrazka");
                    }
                    gridPane.add(vbox, i, j);
                }
                else if (map.isGrassAt(position)){
                    try {
                        vbox = new GuiElementBox(new Grass(position)).vertical;
                    } catch (FileNotFoundException e) {
                        System.out.println("nie znaleziono obrazka");
                    }
                    gridPane.add(vbox, i, j);
                }
            }
        }
        gridPane.setGridLinesVisible(true);
        scene.setRoot(gridPane);
        showScene(stage, scene);
    }

}


