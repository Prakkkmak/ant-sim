package developments;

import abstracts.State;
import ant.Ant;

public class Larva extends State{

  @Override
  public State evolve() {
    return new Nymph();
  }

  @Override
  public void action(Ant ant) {
    // TODO Auto-generated method stub
    
  }

}
