package evolution;

import java.util.Random;

public class SmallMutation extends AnimalReproducer {


    public SmallMutation(AbstractWorldMap map,int minMutation,int maxMutation){
        this.minMutation=minMutation;
        this.maxMutation=maxMutation;
        this.map=map;
    }

    public Gene mutateGene(Gene gene){
        Random rand = new Random();
        boolean Down = rand.nextBoolean();
        if (Down){
            return gene.previous();
        }
        return gene.next();
    }
}
