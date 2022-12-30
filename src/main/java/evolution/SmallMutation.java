package evolution;

import java.util.Random;

public class SmallMutation extends AnimalReproducer {


    public SmallMutation(AbstractWorldMap map, int geneCount, boolean isCrazy, int reproducingEnergy, int fedEnergy) {
        super(map, geneCount, isCrazy, reproducingEnergy, fedEnergy);
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
