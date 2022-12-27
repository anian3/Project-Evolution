package evolution;


public class Energy {

    protected int energyValue;
    private final int fedEnergy;

//    private LinkedList<IDeathObserver> observers;

    public Energy(int energy, int fedEnergy) {
        this.energyValue = energy;
        this.fedEnergy = fedEnergy;
    }

    public void addEnergy(int grassNutrition){
        this.energyValue += grassNutrition;
    }

    public void substractEnergy(int energyConsumed){
        this.energyValue -= energyConsumed;
    }

    public boolean isFed(){
        return energyValue >= fedEnergy;
    }

//    public void addObserver(IPositionChangeObserver observer){
//        observers.add(observer);
//    }
//    public void animalDied();
}
