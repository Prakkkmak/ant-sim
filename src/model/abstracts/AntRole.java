package model.abstracts;

import model.entities.Ant;
import model.enums.EType;
import model.world.Tile;

public abstract class AntRole extends Role {

  public void action(Ant ant) {
    return;
  }

  public int getId() {
    return 1;
  }

  public abstract EType getType();

  protected void moveTowardHome(Ant ant) {
    //TODO Ajouter une formule aléatoire si sur ligne reine.
    //TODO homeTile == null ???
    Tile homeTile = ant.getHome();
    Tile actualTile = ant.getTile();
    int difX = homeTile.getX() - actualTile.getX();
    int difY = homeTile.getY() - actualTile.getY();
    int total = Math.abs(difX) + Math.abs(difY);
    if (total == 0) {
      return;
    }
    double ratioX = Math.abs(difX) / (double) total;
    double ratioY = Math.abs(difY) / (double) total;
    double rng = Math.random();
    if (rng < ratioX) {
      // decrease dif x
      if (difX < 0) {
        ant.moveTo(ant.getTile().getWestTile());
      } else if (difX > 0) {
        ant.moveTo(ant.getTile().getEastTile());
      }
    } else {
      // decrease dif y
      if (difY < 0) {
        ant.moveTo(ant.getTile().getNorthTile());
      } else if (difY > 0) {
        ant.moveTo(ant.getTile().getSouthTile());
      }
    }
  }
}
