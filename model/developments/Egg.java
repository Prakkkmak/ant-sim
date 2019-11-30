package developments;

import abstracts.State;
import ant.Ant;

public class Egg extends State{

  @Override
  public State evolve() {
    return new Larva();
  }

  @Override
  public void action(Ant ant) {
    // TODO Auto-generated method stub
    
  }

}
