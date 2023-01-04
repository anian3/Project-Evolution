package evolution;

import java.util.HashMap;

public class HellMap extends AbstractWorldMap{


    public HellMap(int width, int height, int grassNutrition, int energyUsed, boolean isEquator,int grassCount, int geneCount, int fedEnergy, boolean isCrazy){
        mapEnd = new Vector2d(width, height);
        this.grassNutrition = grassNutrition;
        this.energyUsed = energyUsed;
        this.grassCount = grassCount;
        this.geneCount = geneCount;
        this.fedEnergy = fedEnergy;
        this.isCrazy = isCrazy;
        grasses = new HashMap<>();
        if (isEquator){
            this.grassGrower=new GrassyEquator(this,grassCount);
        }
        else {
            this.grassGrower = new ToxicCorpses(this,grassCount);
        }
        deadAnimals =  new DeadAnimals(this);
    }

//    zakłada, że jesteśmy poza mapą
    public void edgeOperation(Animal animal) {
        animal.energy.substractEnergy(energyUsed);
        animal.moveTo(mapEnd.randomInMap());
    }
}

