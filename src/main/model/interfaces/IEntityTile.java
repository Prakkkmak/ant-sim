package main.model.interfaces;

import main.model.enums.EType;
import main.model.world.Tile;

public interface IEntityTile extends ITickable, IVisitable {

    Tile getTile();

    void setTile(Tile tile);

    int getX();

    int getY();

    EType getType();

    boolean isMarked();

    void setMarked(boolean marked);
}
