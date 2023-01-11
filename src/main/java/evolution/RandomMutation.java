package evolution;


public class RandomMutation extends AnimalReproducer {


    public RandomMutation(AbstractWorldMap map,int maxMutation,int minMutation){
        this.map=map;
        this.maxMutation=maxMutation;
        this.minMutation=minMutation;
    }


    public Gene mutateGene(Gene gene) {
        return gene.randomGene();
    }
}
