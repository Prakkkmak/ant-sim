package model.developments;

import model.abstracts.State;
import model.entities.Ant;

public class Larva extends State{

  @Override
  public State evolve() {
    return new Nymph();
  }

  @Override
  public void action(Ant ant) {
    if(ant.getAge().getDay() > ant.getSpecies().getGrowth(this)){
      evolve();
    }
  }

}
