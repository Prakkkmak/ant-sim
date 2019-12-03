package model.developments;

import model.abstracts.State;
import model.enums.EType;

public class Nymph extends State {

  @Override
  public State evolve() {
    return new Mature();
  }

  @Override
  public EType getType() {
    return EType.NYMPH;
  }

}
