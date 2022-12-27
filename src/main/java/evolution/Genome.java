package evolution;

import java.util.Random;

public class Genome {
    protected final Gene[] genes;
    protected final int geneCount;
    protected int iterator;
    public Genome(int geneCount){
        this.geneCount=geneCount;
        genes=new Gene [geneCount];
        Random rand = new Random();
        iterator=rand.nextInt(geneCount);
        randomGenes();
    }
    public void randomGenes(){
        for (int i=0; i<geneCount;i++){
            Random rand = new Random();
            int n = rand.nextInt(8);
            genes[i] = switch (n){
                case 1 -> Gene.FORWARD_RIGHT;
                case 2 -> Gene.RIGHT;
                case 3 -> Gene.BACKWARD_RIGHT;
                case 4 -> Gene.BACKWARD;
                case 5 -> Gene.BACKWARD_LEFT;
                case 6 -> Gene.LEFT;
                case 7 -> Gene.FORWARD_LEFT;
                default -> Gene.FORWARD;
            };
        }
    }
    public Gene getGene(){
        return genes[iterator];
    }
}

