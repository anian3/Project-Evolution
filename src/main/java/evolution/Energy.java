package evolution;


import java.util.LinkedList;

public class Energy {

    protected int energyValue;
    private final int fedEnergy;

    private Animal animal;

    public Energy(int energy, int fedEnergy, Animal animal) {
        this.energyValue = energy;
        this.fedEnergy = fedEnergy;
        this.animal = animal;
    }
    public String toString(){
        return Integer.toString(energyValue);
    }

    public void addEnergy(int grassNutrition){
        this.energyValue += grassNutrition;
    }

    public void substractEnergy(int energyConsumed){

        this.energyValue -= energyConsumed;
        if (isDead()){
            animal.animalDied();
        }
    }

    public boolean isFed(){
        return energyValue >= fedEnergy;
    }

    public boolean isDead(){
        return energyValue<=0;
    }

}
