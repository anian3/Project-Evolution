package evolution;

import java.util.Random;

public class RandomMutation extends AnimalReproducer {


    public RandomMutation(AbstractWorldMap map){
        this.map=map;
    }


    public Gene mutateGene(Gene gene) {
        return gene.randomGene();
    }
}
