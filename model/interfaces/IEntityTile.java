package interfaces;

import world.Tile;

public interface IEntityTile extends ITickable {
  Tile getTile();
  void setTile(Tile tile);
  int getX();
  int getY();
}
