package evolution;

import java.io.File;

public class Grass implements IMapElement{
    private final Vector2d position;
    public Grass(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition(){
        return position;
    }

    public String toString(){
        return "Trawa";
    }

    public File getResources(){
        return new File("src/main/resources/grass.png");
    }
}
