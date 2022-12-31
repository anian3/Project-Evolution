package evolution;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWorldMapTest {

    @Test
    void testIsBeyond() {
        //given:
        IWorldMap map = new GlobeMap(7, 9, 5, 2, false, 15);
        Vector2d position1 = new Vector2d(0, 0);
        Vector2d position2 = new Vector2d(8, 3);
        boolean res1;
        boolean res2;

        //when:
        res1 = map.isBeyond(position1);
        res2 = map.isBeyond(position2);

        //then:
        assertFalse(res1);
        assertTrue(res2);
    }

    @Test
    void testPlace(){
        //given:
        AbstractWorldMap map = new GlobeMap(7, 9, 5, 2, false, 15);
        Genome genome = new PredestinedAnimal(21);

        //when:
        Animal scyther = new Animal(new Vector2d(2, 2), genome, 4, 6, map);

        //then:
        assertTrue(map.animals.contains(scyther));
    }

    @Test
    void testRemove(){
        //given:
        AbstractWorldMap map = new GlobeMap(13, 9, 5, 2, false, 15);
        Genome genome = new PredestinedAnimal(21);
        Animal onix = new Animal(new Vector2d(10, 2), genome, 4, 6, map);

        //when:
        map.remove(onix);

        //then:
        assertFalse(map.animals.contains(onix));
    }

    @Test
    void testIsOccupied(){
        //given:
        AbstractWorldMap map = new GlobeMap(13, 9, 5, 2, false, 15);
        Genome genome = new PredestinedAnimal(21);
        Vector2d position1 = new Vector2d(10, 2);
        Vector2d position2 = new Vector2d(0, 5);
        Animal fearow = new Animal(position1, genome, 4, 6, map);
        boolean res1;
        boolean res2;

        //when:
        res1 = map.isOccupied(position1);
        res2 = map.isOccupied(position2);

        //then:
        assertTrue(res1);
        assertFalse(res2);
    }

    @Test
    void testIsAnimalAt(){
        //given:
        AbstractWorldMap map = new GlobeMap(13, 9, 5, 2, false, 15);
        Genome genome = new PredestinedAnimal(21);
        Vector2d position1 = new Vector2d(10, 2);
        Vector2d position2 = new Vector2d(0, 5);
        Animal jigglypuff = new Animal(position1, genome, 4, 6, map);
        boolean res1;
        boolean res2;

        //when:
        res1 = map.isAnimalAt(position1);
        res2 = map.isAnimalAt(position2);

        //then:
        assertTrue(res1);
        assertFalse(res2);
    }

    @Test
    void testStrongestAnimalAt(){
        //given:
        AbstractWorldMap map = new GlobeMap(13, 9, 5, 2, false, 15);
        Genome genome1 = new PredestinedAnimal(21);
        Genome genome2 = new PredestinedAnimal(21);
        Vector2d position = new Vector2d(10, 2);
        Animal charmander = new Animal(position, genome1, 4, 6, map);
        Animal growlithe = new Animal(position, genome2, 5, 6, map);

        //when:
        Animal result = map.strongestAnimalAt(position);

        //then
        assertEquals(result, growlithe);
    }

    @Test
    void testSecondStrongestAt(){
        //given:
        AbstractWorldMap map = new GlobeMap(13, 9, 5, 2, false, 15);
        Genome genome1 = new PredestinedAnimal(21);
        Genome genome2 = new PredestinedAnimal(21);
        Genome genome3 = new PredestinedAnimal(21);
        Vector2d position = new Vector2d(10, 2);
        Animal ivysaur = new Animal(position, genome1, 6, 6, map);
        Animal bulbasaur = new Animal(position, genome2, 4, 6, map);
        Animal venusaur = new Animal(position, genome3, 10, 6, map);

        //when:
        Animal result = map.secondStrongestAt(position);

        //then:
        assertEquals(result, ivysaur);
    }

    @Test
    void testGetMapEnd(){
        //given:
        AbstractWorldMap map = new GlobeMap(13, 9, 5, 2, false, 15);

        //when:
        Vector2d result = map.getMapEnd();

        //then:
        assertEquals(result, new Vector2d(13, 9));
    }
}