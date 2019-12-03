public class App {

  public static void main(String[] args) {
    Simulation sim = new Simulation(100, 10, 120, 50);
    sim.init();
    sim.run();
  }

}
