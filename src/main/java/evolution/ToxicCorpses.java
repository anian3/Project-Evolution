package evolution;

import java.util.Arrays;

public class ToxicCorpses extends GrassGrower implements IDeathObserver {

    private final Object[][] howManyDied;
    private final int positionCount;

    public ToxicCorpses(IWorldMap map, int startGrass, int everydayGrass) {

        this.map = map;
        this.startGrass = startGrass;
        this.everydayGrass = everydayGrass;
        positionCount = (map.getMapEnd().getX() + 1) * (map.getMapEnd().getY() + 1);
        howManyDied = new Object[positionCount][2];
        for (int i = 0; i <= map.getMapEnd().getX(); i++) {
            for (int j = 0; j <= map.getMapEnd().getY(); j++) {
                Vector2d position = new Vector2d(i, j);
                howManyDied[(map.getMapEnd().getY()+1) * i + j][0] = position;
                howManyDied[(map.getMapEnd().getY()+1) * i + j][1] = 0;
            }

        }
        firstGrasses();
    }

    public void animalDied(Animal animal) {
        Vector2d position = animal.getPosition();
        for (int i = 0; i < positionCount; i++) {
            if (howManyDied[i][0].equals(position)) {
                int number = (int) howManyDied[i][1] + 1;
                howManyDied[i][1] = number;
                break;
            }
        }
    }

    @Override
    public void findForest() {
        Arrays.sort(howManyDied,  (Object[] o1, Object[] o2) ->(int) o2[1] - (int) o1[1]);
        for (int i = 0; i < (int) Math.ceil(0.2 * positionCount); i++) {
            forest.add((Vector2d) howManyDied[i][0]);
        }
        for (int i = (int) Math.ceil(0.2 * positionCount); i < positionCount; i++){
            barren.add((Vector2d) howManyDied[i][0]);
        }


    }


//    będziemy tu trzymać, ile zwierzat umarło na każdym polu

}
