import java.util.Date;

import model.data.Species;
import view.View;
import model.world.World;

public class App {
  public static void main(String[] args) {
    Simulation sim = new Simulation(200, 1000, 1, 5);
    sim.init();
    sim.run();
  }

}
