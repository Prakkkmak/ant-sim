package model.roles;

import model.abstracts.AntRole;
import model.entities.Ant;

public class Worker extends AntRole {

  @Override
  public void action(Ant ant) {
    int rnd = (int) (Math.random() * 4); // 0 1 2 3
    switch (rnd) {
      case 0:
        ant.getTile().moveTo(ant, ant.getX() + 1,  ant.getY());
        break;
      case 1:
        ant.getTile().moveTo(ant, ant.getX() - 1,  ant.getY());
        break;
      case 2:
        ant.getTile().moveTo(ant, ant.getX(),  ant.getY() + 1);
        break;
      case 3:
        ant.getTile().moveTo(ant, ant.getX(),  ant.getY() - 1);
        break;
    }
  }

}
