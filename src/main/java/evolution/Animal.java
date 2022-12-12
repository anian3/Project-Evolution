package evolution;

import java.io.File;
import java.util.ArrayList;

public class Animal implements IMapElement{
    private MapDirection direction;
    private Vector2d position;

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public Animal(Vector2d initialPosition) {
        direction = MapDirection.NORTH;
        position = initialPosition;
    }

    public String toString(){
        return "Z" + position;
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    public void move(MoveDirection direction){
        switch (direction) {
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

    public void switchDirection(int number){
        for (int i=0; i< number; i++) {
            this.direction = this.direction.next();
        }
    }
}
