package evolution;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap{

    protected Vector2d mapEnd;
    protected LinkedList<Animal> animals = new LinkedList<>();
    protected Map<Vector2d, IMapElement> grasses = new HashMap<>();
    protected int grassNutrition;
    protected int energyUsed;

    public boolean isBeyond(Vector2d position) {
        return !position.follows(new Vector2d(0, 0)) || !position.precedes(mapEnd);
    }


    public boolean place(Animal animal) {
        if (!isBeyond(animal.getPosition())){
            animals.add(animal);
            return true;
        }
        return false;
    }

    public boolean remove(IMapElement object) {
        if (animals.contains(object)){
            animals.remove(object);
            return true;
        }
        if (grasses.containsValue(object)){
            grasses.remove(object.getPosition(),object);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        return isAnimalAt(position) || isGrassAt(position);
    }


    public boolean isGrassAt(Vector2d position) {
        return grasses.get(position) != null;
    }


    public boolean isAnimalAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    public void eat(Vector2d position) {
        if (isGrassAt(position) && isAnimalAt(position)){
            strongestAnimalAt(position).energy.addEnergy(grassNutrition);
            remove(grasses.get(position));
        }
    }

    public Animal strongestAnimalAt(Vector2d position) {
        int maxEnergy = 0;
        Animal currentAnimal = null;
        for (Animal animal:animals){
            if (animal.getEnergyValue() > maxEnergy){
                maxEnergy = animal.getEnergyValue();
                currentAnimal = animal;
            }
        }
        return currentAnimal;
    }

    public Animal secondStrongestAt(Vector2d position) {
        Animal strongestAnimal = strongestAnimalAt(position);
        int maxEnergy = 0;
        Animal secondStrongest = null;
        for (Animal animal:animals){
            if (!animal.equals(strongestAnimal) && animal.getEnergyValue() > maxEnergy){
                maxEnergy = animal.getEnergyValue();
                secondStrongest = animal;
            }
        }
        return secondStrongest;
    }
}
