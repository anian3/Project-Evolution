package evolution;

import java.util.Random;

import static java.lang.Math.*;
import static java.util.Arrays.copyOfRange;

public abstract class Genome implements IGenome {
    protected final int geneCount;
    protected int iterator;
    protected Gene[] genes;

    public Genome(int geneCount) {
        this.geneCount = geneCount;
        Random rand = new Random();
        iterator = rand.nextInt(geneCount);
        genes = new Gene[geneCount];
        randomGenes();
    }

    public void randomGenes() {
        for (int i = 0; i < geneCount; i++) {
            Random rand = new Random();
            int n = rand.nextInt(8);
            genes[i] = switch (n) {
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

    public Gene getGene() {
        Gene gene = genes[iterator];
        findNextGene();
        return gene;
    }

    public Gene[] getFromLeft(int numberOfGenes) {
        return copyOfRange(genes, 0, max(0, numberOfGenes));
    }

    public Gene[] getFromRight(int numberOfGenes) {
        return copyOfRange(genes, min(geneCount - numberOfGenes, geneCount - 1), geneCount);
    }

    public void writeGenes(Gene[] genes) {
        this.genes = genes;
    }
}

