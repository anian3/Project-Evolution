package evolution;

import java.util.LinkedList;

public class SimulationEngine implements IEngine, Runnable {

    public final AbstractWorldMap map;
    private final AnimalReproducer animalReproducer;

    private final LinkedList<IActionObserver> observers = new LinkedList<>();

    public SimulationEngine(int width, int height, boolean isEquator, boolean isGlobe, int startGrass, int grassNutrition, int everydayGrass,
                            int startAnimalsCount, int startEnergy, int fedEnergy, int energyUsed,
                            int minMutation, int maxMutation, boolean isSmall, int geneCount, boolean isCrazy) {
        if (isGlobe) {
            map = new GlobeMap(width, height, grassNutrition, energyUsed, isEquator, startGrass, everydayGrass, geneCount, fedEnergy, isCrazy);
        } else {
            map = new HellMap(width, height, grassNutrition, energyUsed, isEquator, startGrass, everydayGrass, geneCount, fedEnergy, isCrazy);
        }
        AnimalCreator animalCreator = new AnimalCreator(map, startEnergy);
        for (int i = 0; i < startAnimalsCount; i++) {
            animalCreator.createAnimal();
        }
        if (isSmall) {
            animalReproducer = new SmallMutation(map, minMutation, maxMutation);
        } else {
            animalReproducer = new RandomMutation(map, minMutation, maxMutation);
        }
    }

    public void moveAnimals() {
        for (Animal animal : map.animals) {
            animal.move();
            actionHappened();
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException("Przerwano symulację.");
            }
        }
    }

    public void addObserver(IActionObserver observer) {
        observers.add(observer);
    }

    public void actionHappened() {
        for (IActionObserver observer : observers) {
            observer.actionHappened(map);
        }
    }

    public void grassConsumption() {
        for (Animal animal : map.animals) {
            map.eat(animal.getPosition());
            actionHappened();
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException("Przerwano symulację.");
            }
        }
    }

    public void animalsReproduce() {
        LinkedList<Animal> animalsCopy;
        animalsCopy = (LinkedList<Animal>) map.animals.clone();
        for (Animal animal : animalsCopy) {
            if (animal.equals(map.strongestAnimalAt(animal.getPosition()))) {
                Animal secondAnimal = map.secondStrongestAt(animal.getPosition());
                if (secondAnimal != null) {
                    animalReproducer.reproduce(animal, secondAnimal);
                    actionHappened();
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        throw new RuntimeException("Przerwano symulację.");
                    }
                }
            }
        }
    }

    public void run() {
        while (true) {
            moveAnimals();
            map.deadAnimals.removeDeadAnimals(map.animals);
            grassConsumption();
            animalsReproduce();
            map.grassGrower.createGrasses();
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException("Przerwano symulację.");
            }
        }
    }

}
