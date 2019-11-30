package model.abstracts;

import model.entities.Ant;

public abstract class State {
  public State next;
  public abstract State evolve();
  public abstract void action(Ant ant);
  public AntRole getAntRole() {
    return null;
  }
  public void setAntRole(AntRole antRole) {
    return;
  }

  @Override
  public boolean equals(Object o){
    return o.getClass() == this.getClass();
  }

}
