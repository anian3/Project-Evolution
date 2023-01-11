package evolution;

import org.junit.jupiter.api.Test;

import static evolution.Gene.*;
import static org.junit.jupiter.api.Assertions.*;

class AnimalReproducerTest {


    @Test
    void makeGenes() {
        //given
        AbstractWorldMap map = new GlobeMap(5,5,5,5,true,4, 4, 4, 5, false);
        Genome genome1 = new CrazyAnimal(5);
        genome1.randomGenes();
        Animal animal1 = new Animal(new Vector2d(2,2),genome1,8,map);
        Genome genome2 = new CrazyAnimal(5);
        genome2.randomGenes();
        Animal animal2 = new Animal(new Vector2d(2,2),genome2,8,map);
        AnimalReproducer animalReproducer = new SmallMutation(map,0,2);
        //when
        Gene[] result = animalReproducer.makeGenes(animal1,animal2);
        //then
        assertEquals(4,result.length);


    }


    @Test
    void reproduce() {
    }
}