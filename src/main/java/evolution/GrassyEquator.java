package evolution;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

public class GrassyEquator extends GrassGrower {


    public GrassyEquator(IWorldMap map) {
        super(map);
    }

    @Override
    public void findForest() {
        double rowNumber = map.getMapEnd().getY();
        int equatorRowNumber = (int) ceil(rowNumber/5.0);
        List <Vector2d> forest = new ArrayList<>();
        for (int i=0; i<map.getMapEnd().getX();i++){
            for (int j = (int) (floor(rowNumber/2)-ceil(equatorRowNumber/2)); j<ceil(rowNumber/2)+ceil(equatorRowNumber/2); j++){
                forest.add(new Vector2d(i,j));
            }
        }
    }
}
