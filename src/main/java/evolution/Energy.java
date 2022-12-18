package evolution;

import java.util.LinkedList;

public class Energy {

    private int energy;
    private final int fedEnergy;

//    private LinkedList<IDeathObserver> observers;

    public Energy(int energy, int fedEnergy) {
        this.energy = energy;
        this.fedEnergy = fedEnergy;
    }

    public void addEnergy(int grassNutrition){
        this.energy += grassNutrition;
    }

    public void substractEnergy(int energyConsumed){
        this.energy -= energyConsumed;
    }

    public boolean isFed(){
        return energy >= fedEnergy;
    }

//    public void addObserver(IPositionChangeObserver observer){
//        observers.add(observer);
//    }
//    public void animalDied();
}
