package model.roles;

import model.abstracts.AntRole;
import model.entities.Ant;
import model.enums.EType;

public class Soldier extends AntRole {

  @Override
  public void action(Ant ant) {
    ant.getTile().moveTo(ant, ant.getX() + 1, ant.getY());
  }
  @Override
  public EType getType(){
    return EType.WORKER;
  }


}
