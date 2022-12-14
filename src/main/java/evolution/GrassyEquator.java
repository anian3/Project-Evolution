package evolution;


import static java.lang.Math.ceil;
import static java.lang.Math.floor;

public class GrassyEquator extends GrassGrower {


    public GrassyEquator(IWorldMap map, int startGrass, int everydayGrass) {
        this.map = map;
        this.startGrass = startGrass;
        this.everydayGrass = everydayGrass;
        firstGrasses();
    }

    @Override
    public void findForest() {
        double rowNumber = map.getMapEnd().getY();
        int equatorRowNumber = (int) ceil(rowNumber / 5.0);
        for (int i = 0; i < map.getMapEnd().getX(); i++) {
            for (int j = 0; j < rowNumber; j++) {
                if (j >= (int) (floor(rowNumber / 2) - ceil(equatorRowNumber / 2)) && j <= ceil(rowNumber / 2) + ceil(equatorRowNumber / 2)) {
                    forest.add(new Vector2d(i, j));
                } else {
                    barren.add(new Vector2d(i, j));
                }
            }
        }
    }
}
