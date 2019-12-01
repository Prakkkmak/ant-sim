package model.interfaces;

import model.world.Tile;

public interface IEntityTile extends ITickable, IVisitable {
  Tile getTile();
  void setTile(Tile tile);
  int getX();
  int getY();
  int getId();
}
