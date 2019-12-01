package model.world;

public class Pheromone {
    private int volatility;
    public Pheromone(){
        this.volatility = 1;
    }
    public int getVolatility(){
        return this.volatility;
    }
}
