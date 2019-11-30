package developments;

import abstracts.State;
import ant.Ant;

public class Nymph extends State{

  @Override
  public State evolve() {
    return new Mature();
  }

  @Override
  public void action(Ant ant) {
    // TODO Auto-generated method stub
    
  }

}
