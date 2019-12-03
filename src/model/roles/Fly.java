package model.roles;

import model.abstracts.AntRole;
import model.entities.Ant;
import model.enums.EType;

public class Fly extends AntRole {

  @Override
  public void action(Ant ant) {
    // TODO Auto-generated method stub

  }
  @Override
  public EType getType(){
    return EType.SEXUAL;
  }

}
