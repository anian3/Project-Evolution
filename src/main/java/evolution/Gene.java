package evolution;

import java.util.Random;

public enum Gene {

    FORWARD,
    FORWARD_RIGHT,
    RIGHT,
    BACKWARD_RIGHT,
    BACKWARD,
    BACKWARD_LEFT,
    LEFT,
    FORWARD_LEFT;

    public Gene next(){
        return switch (this) {
            case FORWARD -> FORWARD_RIGHT;
            case FORWARD_RIGHT -> RIGHT;
            case RIGHT -> BACKWARD_RIGHT;
            case BACKWARD_RIGHT -> BACKWARD;
            case BACKWARD -> BACKWARD_LEFT;
            case BACKWARD_LEFT -> LEFT;
            case LEFT -> FORWARD_LEFT;
            case FORWARD_LEFT -> FORWARD;
        };
    }

    public Gene previous(){
        return switch (this) {
            case FORWARD -> FORWARD_LEFT;
            case FORWARD_RIGHT -> FORWARD;
            case RIGHT -> FORWARD_RIGHT;
            case BACKWARD_RIGHT -> RIGHT;
            case BACKWARD -> BACKWARD_RIGHT;
            case BACKWARD_LEFT -> BACKWARD;
            case LEFT -> BACKWARD_LEFT;
            case FORWARD_LEFT -> LEFT;
        };
    }

    public Gene randomGene(){
        Random rand = new Random();
        int whichGene = rand.nextInt(8);
        return switch (whichGene){
            case 0 -> FORWARD;
            case 1 -> FORWARD_RIGHT;
            case 2 -> RIGHT;
            case 3 -> BACKWARD_RIGHT;
            case 4 -> BACKWARD;
            case 5 -> BACKWARD_LEFT;
            case 6 -> LEFT;
            default -> FORWARD_LEFT;
        };
    }
}
