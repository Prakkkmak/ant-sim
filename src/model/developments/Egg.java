package model.developments;

import model.abstracts.State;
import model.enums.EType;

public class Egg extends State {

  @Override
  public State evolve() {
    return new Larva();
  }
  @Override
  public EType getType(){
    return EType.EGG;
  }
}
