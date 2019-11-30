package model.developments;

import model.abstracts.State;
import model.abstracts.AntRole;
import model.entities.Ant;
import model.roles.Worker;

public class Mature extends State{
  private AntRole antRole;
  
  public Mature() {
    //TODO rng
    this.antRole = new Worker();
  }
  @Override
  public State evolve() {
    return new Garbage();
  }
  @Override
  public AntRole getAntRole() {
    return this.antRole;
  }
  @Override
  public void setAntRole(AntRole antRole) {
    if(antRole == null) antRole = new Worker();
    this.antRole = antRole;
  }
  @Override
  public void action(Ant ant) {
    antRole.action(ant);
  }
}
