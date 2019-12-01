package model.developments;

import model.abstracts.State;
import model.entities.Ant;

public class Garbage extends State{

  public Garbage(){
    System.out.println("Garbage created ");
  }

  @Override
  public State evolve() {
    return null;
  }

  @Override
  public void action(Ant ant) {

  }

}
