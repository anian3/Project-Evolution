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
    }

    public void removeDeadAnimals(LinkedList <Animal> animals){
        LinkedList <Animal> animalsCopy = new LinkedList<>();
        animalsCopy = (LinkedList<Animal>) animals.clone();
        for (Animal animal: animalsCopy){
            if (animal.energy.isDead()){
                animals.remove(animal);
            }
        }
    }

}
