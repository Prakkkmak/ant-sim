package model.roles;

import model.abstracts.AntRole;
import model.entities.Ant;

public class Soldier extends AntRole {

  @Override
  public void action(Ant ant) {
    ant.getTile().moveTo(ant, ant.getX() + 1,  ant.getY());
  }

}
