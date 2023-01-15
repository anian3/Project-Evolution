package evolution;

public interface IWorldMap{

//  czy to poza krawędzią?
    boolean isBeyond(Vector2d position);

//  obsługa krawędzi
    void edgeOperation(Animal animal);

    boolean place(Animal animal);

    boolean remove(IMapElement object);

    boolean isOccupied(Vector2d position);

    boolean isGrassAt(Vector2d position);

    boolean isAnimalAt(Vector2d position);

    void eat(Vector2d position);

    Animal randomAnimalAt(Vector2d position);

    Animal strongestAnimalAt(Vector2d position);

    Animal secondStrongestAt(Vector2d position);

    Vector2d getMapEnd();

    void growGrass(Vector2d position);

    String animalsCount();
    String grassCount();

}
