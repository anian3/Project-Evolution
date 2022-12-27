package evolution;

public class HellMap extends AbstractWorldMap{

    public HellMap(int height, int width, int grassNutrition, int energyUsed){
        mapEnd = new Vector2d(width, height);
        this.grassNutrition = grassNutrition;
        this.energyUsed = energyUsed;
    }

//    zakłada, że jesteśmy poza mapą
    public void edgeOperation(Animal animal) {
        animal.energy.substractEnergy(energyUsed);
        if (animal.getEnergyValue() > 0){
            animal.moveTo(mapEnd.randomInMap());
        }
    }
}

