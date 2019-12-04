import simulation.Simulation;

public class App {

  public static void main(String[] args) {
    Simulation sim = new Simulation(100, 2000, 1, 50);
    sim.init();
    sim.run();
  }

}
