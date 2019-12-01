package model.developments;

import model.abstracts.State;
import model.entities.Ant;

public class Nymph extends State{

  @Override
  public State evolve() {
    return new Mature();
  }

}
