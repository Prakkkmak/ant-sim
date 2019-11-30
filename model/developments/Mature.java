package developments;

import abstracts.State;
import abstracts.Role;
import ant.Ant;
import roles.Worker;

public class Mature extends State{
  private Role role;
  
  public Mature() {
    //TODO rng
    this.role = new Worker();
  }
  @Override
  public State evolve() {
    return new Garbage();
  }
  @Override
  public Role getRole() {
    return this.role;
  }
  @Override
  public void setRole(Role role) {
    this.role = role;
  }
  @Override
  public void action(Ant ant) {
    role.action(ant);
  }
}
