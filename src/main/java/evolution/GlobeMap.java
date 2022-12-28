package evolution;

import java.util.HashMap;

public class GlobeMap extends AbstractWorldMap{

//    public DeadAnimals deadAnimals = new DeadAnimals();

    public GlobeMap(int height, int width, int grassNutrition, int energyUsed, IDeathObserver grassGrower){
        mapEnd = new Vector2d(width, height);
        this.grassNutrition = grassNutrition;
        this.energyUsed = energyUsed;
        this.grassGrower = grassGrower;
        grasses = new HashMap<>();
    }

//    raczej z góry zakłada, że position, które przyjmuje jest poza mapą
    public void edgeOperation(Animal animal) {
        Vector2d position = animal.getPosition();
        animal.moveTo(position.vectorInMap(mapEnd));
    }
}
