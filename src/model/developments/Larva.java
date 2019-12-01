package model.developments;

import model.abstracts.State;
import model.entities.Ant;

public class Larva extends State{

  @Override
  public State evolve() {
    return new Nymph();
  }

  /**
   * The larva eat this weight when she have low food.
   * @param ant The ant who is larva.
   */
  @Override
  public void action(Ant ant) {
    int currentFood = ant.getFood();
    if(currentFood < ant.getSpecies().getFoodConsumption()){
      //ant.consume(ant.getSpecies().getWeight());
    }
    super.action(ant);
  }

}
