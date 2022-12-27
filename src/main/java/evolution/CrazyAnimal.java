package evolution;
import java.util.Random;

public class CrazyAnimal extends Genome{

    public CrazyAnimal(int geneCount) {
        super(geneCount);
    }

    public void findNextGene(){
        Random rand = new Random();
        int n = rand.nextInt(10);
        if (n<2){
            rand = new Random();
            iterator = rand.nextInt(geneCount);
        }
        else{
            iterator =(iterator+1)%geneCount;
        }
    }
}
