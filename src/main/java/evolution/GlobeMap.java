package evolution;

public class GlobeMap extends AbstractWorldMap{

    public GlobeMap(int height, int width, int grassNutrition, int energyUsed){
        mapEnd = new Vector2d(width, height);
        this.grassNutrition = grassNutrition;
        this.energyUsed = energyUsed;
    }

//    raczej z góry zakłada, że position, które przyjmuje jest poza mapą
    public void edgeOperation(Animal animal) {
        Vector2d position = animal.getPosition();
        animal.moveTo(position.vectorInMap(mapEnd));
    }
}
