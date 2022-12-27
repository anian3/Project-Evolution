package evolution;

public abstract class GrassGrower implements IGrassGrower{
    protected IWorldMap map;

    public GrassGrower(IWorldMap map){
        this.map=map;
    }
    @Override
    public boolean growGrass(Vector2d[] forests) {
        return false;

    }
}
