package evolution;

import java.util.HashMap;

public class GlobeMap extends AbstractWorldMap{

//    public DeadAnimals deadAnimals = new DeadAnimals();

    public GlobeMap(int width, int height, int grassNutrition, int energyUsed, boolean isEquator,int startGrass, int everydayGrass, int geneCount, int fedEnergy, boolean isCrazy){
        mapEnd = new Vector2d(width, height);
        this.grassNutrition = grassNutrition;
        this.energyUsed = energyUsed;
        this.geneCount = geneCount;
        this.fedEnergy = fedEnergy;
        this.isCrazy = isCrazy;
        grasses = new HashMap<>();
        if (isEquator){
            this.grassGrower=new GrassyEquator(this, startGrass, everydayGrass);
        }
        else {
            this.grassGrower = new ToxicCorpses(this, startGrass, everydayGrass);
        }
        this.deadAnimals = new DeadAnimals(this);
    }

//    raczej z góry zakłada, że position, które przyjmuje jest poza mapą
    public void edgeOperation(Animal animal) {
        Vector2d position = animal.getPosition();
        animal.moveTo(position.vectorInMap(mapEnd));
    }
}
