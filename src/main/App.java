package main;

import main.simulation.Simulation;

public class App {

    public static void main(String[] args) {
        Simulation sim = new Simulation(150, 500, 1, 100);
        sim.init();
        sim.run();
    }

}
