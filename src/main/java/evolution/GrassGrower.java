package evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GrassGrower implements IGrassGrower{
    protected IWorldMap map;
    List<Vector2d> forest = new ArrayList<>();
    List<Vector2d> barren = new ArrayList<>();
    int everydayGrass;
    int startGrass;
    @Override
    public boolean growGrass() {
        Random rand = new Random();
        double random = rand.nextDouble();
        Vector2d position;
        rand = new Random();
        if (random < 0.8){
            int index = rand.nextInt(forest.size());
            position = forest.get(index);
        }
        else {
            int index = rand.nextInt(barren.size());
            position = barren.get(index);
        }
        if(map.isGrassAt(position)){
            return false;
        }
        map.growGrass(position);
        return true;
    }

    public void firstGrasses(){
        int i = 0;
        findForest();
        while (i < startGrass){
            if (growGrass()){
                i++;
            }
        }
    }

    public void createGrasses(){
        int i = 0;
        findForest();
        while (i < everydayGrass){
            if (growGrass()){
                i++;
            }
        }
    }
}
