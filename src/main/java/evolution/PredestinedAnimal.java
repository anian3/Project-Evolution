package evolution;

public class PredestinedAnimal extends Genome{


    public PredestinedAnimal(int geneCount) {
        super(geneCount);
    }

    public void findNextGene(){
        iterator =(iterator+1)%geneCount;
    }
}
