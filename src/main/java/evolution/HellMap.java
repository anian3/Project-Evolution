package evolution;

import java.util.HashMap;

public class HellMap extends AbstractWorldMap{

//    public DeadAnimals deadAnimals = new DeadAnimals();
    public HellMap(int height, int width, int grassNutrition, int energyUsed, IDeathObserver grassGrower){
        mapEnd = new Vector2d(width, height);
        this.grassNutrition = grassNutrition;
        this.energyUsed = energyUsed;
        this.grassGrower = grassGrower;
        grasses = new HashMap<>();
    }

//    zakłada, że jesteśmy poza mapą
    public void edgeOperation(Animal animal) {
        animal.energy.substractEnergy(energyUsed);
        animal.moveTo(mapEnd.randomInMap());
    }
}

