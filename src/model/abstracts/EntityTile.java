package model.abstracts;

import model.interfaces.IEntityTile;
import model.world.Tile;
import model.world.Time;

public abstract class  EntityTile implements IEntityTile {
    private int lastAction;
    private Time age;
    private Tile tile;

    public EntityTile(Tile tile){
        this.lastAction = 0;
        this.age = new Time();
        this.tile = tile;
    }

    public Time getAge(){
        return age;
    }

    public int getX() {
        return this.tile.getX();
    }

    public int getY() {
        return this.tile.getY();
    }

    public Tile getTile() {
        return this.tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public abstract void update();

    @Override
    public void onMinutePass(int tick) {
        if(tick == lastAction) {
            return;
        }
        lastAction = tick;
        this.update();
    }

}