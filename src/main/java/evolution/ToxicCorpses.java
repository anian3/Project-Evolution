package evolution;

public class ToxicCorpses extends GrassGrower implements IDeathObserver{

    public ToxicCorpses(IWorldMap map) {
        super(map);
    }

    public void animalDied(Animal animal){}

    @Override
    public void findForest() {

    }


//    będziemy tu trzymać, ile zwierzat umarło na każdym polu

}
