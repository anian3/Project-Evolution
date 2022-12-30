package evolution;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.Collections.shuffle;

public abstract class AnimalReproducer implements IAnimalReproduce{
    AbstractWorldMap map;
    int geneCount;
    boolean isCrazy;
    int reproducingEnergy;
    int fedEnergy;
    public AnimalReproducer(AbstractWorldMap map,int geneCount,boolean isCrazy,int reproducingEnergy, int fedEnergy){
        this.map=map;
        this.geneCount=geneCount;
        this.isCrazy=isCrazy;
        this.reproducingEnergy=reproducingEnergy;
        this.fedEnergy=fedEnergy;
    }
    public int[] whichGenesMutate(){
        Random rand = new Random();
        int mutationCount = rand.nextInt(geneCount+1);
        List <Integer> indexes = new LinkedList<>();
        for (int i=0; i<geneCount;i++){
            indexes.add(i);
        }
        shuffle(indexes);
        int [] chosenIndexes = new int [mutationCount];
        for (int i=0; i<mutationCount;i++){
            chosenIndexes[i]=indexes.get(i);
        }
        return chosenIndexes;
    }

    public Genome makeGenome(Animal animal1, Animal animal2) {
        Genome newGenome;
        if (isCrazy) {
            newGenome = new CrazyAnimal(geneCount);
        }
        else {
            newGenome = new PredestinedAnimal(geneCount);
        }
        newGenome.writeGenes(mutate(makeGenes(animal1, animal2)));
        return newGenome;
    }

    public Gene[] makeGenes (Animal animal1, Animal animal2){
        Random rand = new Random();
        boolean leftSide = rand.nextBoolean();
        int numberOfGenes = geneCount * (animal1.getEnergyValue() / (animal1.getEnergyValue() + animal2.getEnergyValue()));
        Gene[] combinedGenes;
        if (leftSide){
             combinedGenes = Stream.concat(Arrays.stream(animal1.getGenome().getFromLeft(numberOfGenes)), Arrays.stream(animal2.getGenome().getFromRight(geneCount-numberOfGenes)))
                    .toArray(Gene[]::new);
        }
        else{
            combinedGenes = Stream.concat(Arrays.stream(animal2.getGenome().getFromLeft(geneCount-numberOfGenes)),Arrays.stream(animal1.getGenome().getFromRight(numberOfGenes)))
                    .toArray(Gene[]::new);
        }
        return combinedGenes;
    }

    public Gene[] mutate(Gene[] genes){
        int [] indexes = whichGenesMutate();
        for (int index: indexes){
            genes[index] = mutateGene(genes[index]);
        }
        return genes;
    }
    public void reproduce(Animal animal1, Animal animal2){
        animal1.energy.substractEnergy(reproducingEnergy);
        animal2.energy.substractEnergy(reproducingEnergy);
        Animal childAnimal = new Animal(animal1.getPosition(),makeGenome(animal1, animal2),2*reproducingEnergy,fedEnergy,map);
    }
}
