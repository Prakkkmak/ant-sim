package model.interfaces;

import model.enums.EType;
import model.world.Tile;

public interface IEntityTile extends ITickable, IVisitable {

  Tile getTile();

  void setTile(Tile tile);

  int getX();

  int getY();

  EType getType();

  boolean isMarked();

  void setMarked(boolean marked);
}
