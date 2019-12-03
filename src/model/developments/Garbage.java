package model.developments;

import model.abstracts.State;
import model.entities.Ant;
import model.enums.EType;

public class Garbage extends State {

  public Garbage() {

  }

  @Override
  public State evolve() {
    return null;
  }

  @Override
  public void action(Ant ant) {

  }
  @Override
  public EType getType(){
    return EType.GARBAGE;
  }

}
