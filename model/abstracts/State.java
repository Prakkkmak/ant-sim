package abstracts;

import ant.Ant;

public abstract class State {
  public State next;
  public abstract State evolve();
  public abstract void action(Ant ant);
  public Role getRole() {
    return null;
  }
  public void setRole(Role role) {
    return;
  }

}
