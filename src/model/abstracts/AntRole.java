package model.abstracts;

import model.entities.Ant;
import model.interfaces.IAction;
import model.interfaces.IEntityTile;

public abstract class AntRole extends Role {
  public void action(Ant ant){
    return;
  }
  public int getId(){ return 1; }
}
