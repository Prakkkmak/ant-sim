package roles;

import abstracts.Role;
import ant.Ant;

public class Soldier extends Role {

  @Override
  public void action(Ant ant) {
    // TODO Auto-generated method stub
    ant.getTile().moveTo(ant, ant.getX() + 1,  ant.getY());
  }

}
