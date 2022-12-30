package evolution;

import java.util.Random;

public class RandomMutation extends AnimalReproducer {


    public RandomMutation(AbstractWorldMap map, int geneCount, boolean isCrazy, int reproducingEnergy, int fedEnergy) {
        super(map, geneCount, isCrazy, reproducingEnergy, fedEnergy);
    }


    public Gene mutateGene(Gene gene) {
        return gene.randomGene();
    }
}
