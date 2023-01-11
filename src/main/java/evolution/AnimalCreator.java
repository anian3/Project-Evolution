package evolution;

import java.util.Random;

public class AnimalCreator {

    private final AbstractWorldMap map;
    private final int startEnergy;

    public AnimalCreator(AbstractWorldMap map, int startEnergy) {
        this.map = map;
        this.startEnergy = startEnergy;
    }

    public Genome createGenome() {
        if (map.isCrazy) {
            return new CrazyAnimal(map.geneCount);
        }
        return new PredestinedAnimal(map.geneCount);
    }

    public void createAnimal() {
        Genome genome = createGenome();
        Vector2d initialPosition = generatePosition();
        Animal animal = new Animal(initialPosition, genome, startEnergy, map);
    }

    public Vector2d generatePosition() {
        Random rand = new Random();
        int x = rand.nextInt(map.mapEnd.getX() + 1);
        rand = new Random();
        int y = rand.nextInt(map.mapEnd.getY() + 1);
        return new Vector2d(x, y);
    }
}
