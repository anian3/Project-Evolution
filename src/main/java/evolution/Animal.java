package evolution;

import java.util.LinkedList;

public class Animal implements IMapElement{
    MapDirection direction;
    private Vector2d position;
    private final IWorldMap map;
    private final Genome genome;
    private int daysLived;
    protected final Energy energy;
    private LinkedList<IDeathObserver> deathObservers;
    private LinkedList<IPositionChangeObserver> positionObservers;

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public Animal(Vector2d initialPosition,Genome genome, int energy, int fedEnergy, AbstractWorldMap map) {
        direction = MapDirection.NORTH;
        position = initialPosition;
        this.genome=genome;
        this.energy = new Energy(energy, fedEnergy);
        this.map = map;
        addDeathObserver(map.deadAnimals);
        addDeathObserver(map.grassGrower);
        map.place(this);
    }

    public String toString(){
        return "Z" + position;
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    public void move(Gene gene){
        switch (gene) {
            case FORWARD_RIGHT -> switchDirection(1);
            case RIGHT -> switchDirection(2);
            case BACKWARD_RIGHT -> switchDirection(3);
            case BACKWARD -> switchDirection(4);
            case BACKWARD_LEFT -> switchDirection(5);
            case LEFT -> switchDirection(6);
            case FORWARD_LEFT -> switchDirection(7);
        }
        this.position = this.position.add(this.direction.toUnitVector());
    }

    public void moveTo(Vector2d position){
        this.position = position;
    }

    public void switchDirection(int number){
        for (int i=0; i< number; i++) {
            this.direction = this.direction.next();
        }
    }

    public int getEnergyValue(){
        return energy.energyValue;
    }

    public void addPositionObserver(IPositionChangeObserver observer){
        positionObservers.add(observer);
    }

    public void removePositionObserver(IPositionChangeObserver observer){
        positionObservers.remove(observer);
    }
    public void addDeathObserver(IDeathObserver observer){
        deathObservers.add(observer);
    }

    public void removeDeathObserver(IDeathObserver observer){
        deathObservers.remove(observer);
    }
    public Genome getGenome(){
        return genome;
    }
}