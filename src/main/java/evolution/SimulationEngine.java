package evolution;

public class SimulationEngine implements IEngine, Runnable{

    public final AbstractWorldMap map;
    private AnimalReproducer animalReproducer;

    public SimulationEngine(int width, int height, boolean isEquator, boolean isGlobe, int grassCount, int grassNutrition,
                            int startAnimalsCount, int startEnergy, int fedEnergy, int energyUsed,
                            int minMutation, int maxMutation, boolean isSmall, int geneCount, boolean isCrazy){
        if (isGlobe){
            map = new GlobeMap(width, height, grassNutrition, energyUsed, isEquator, grassCount, geneCount, fedEnergy, isCrazy);
        }
        else {
            map = new HellMap(width, height, grassNutrition, energyUsed, isEquator, grassCount, geneCount, fedEnergy, isCrazy);
        }
        AnimalCreator animalCreator = new AnimalCreator(startAnimalsCount, map, startEnergy);
        if (isSmall){
            animalReproducer = new SmallMutation(map,minMutation,maxMutation);
        }
        else {
            animalReproducer = new RandomMutation(map,minMutation,maxMutation);
        }
    }

    public void moveAnimals(){
        for (Animal animal: map.animals){
            animal.move();
        }
    }

    public void grassConsumption(){
        for (Animal animal: map.animals){
            map.eat(animal.getPosition());
        }
    }

    public void animalsReproduce(){
        for (Animal animal: map.animals){
            if (animal.equals(map.strongestAnimalAt(animal.getPosition()))){
                Animal secondAnimal = map.secondStrongestAt(animal.getPosition());
                if (secondAnimal != null){
                    animalReproducer.reproduce(animal, secondAnimal);
                }
            }
        }
    }

    public void run(){
        moveAnimals();
        grassConsumption();
        animalsReproduce();
        map.grassGrower.createGrasses();
    }

}
