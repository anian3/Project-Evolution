package evolution;

import java.util.LinkedList;

public class DeadAnimals implements IDeathObserver{

    LinkedList<Animal> deadAnimals;

    public void animalDied(Animal animal){
        deadAnimals.add(animal);
    }

}
