package evolution;

import java.util.LinkedList;

public class DeadAnimals implements IDeathObserver{

    LinkedList<Animal> deadAnimals;
    IWorldMap map;

    public DeadAnimals(IWorldMap map){
        this.map = map;
        deadAnimals = new LinkedList<>();
    }

    public void animalDied(Animal animal){
        deadAnimals.add(animal);
        map.remove(animal);
    }

}
