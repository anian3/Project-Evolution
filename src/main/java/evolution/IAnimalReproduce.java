package evolution;

public interface IAnimalReproduce {

    void reproduce(Animal animal1, Animal animal2);

    int[] whichGenesMutate();
    Genome makeGenome(Animal animal1, Animal animal2);
    Gene[] makeGenes (Animal animal1, Animal animal2);
    Gene mutateGene(Gene gene);

}
