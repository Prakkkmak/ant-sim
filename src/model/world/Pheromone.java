package model.world;

public class Pheromone {

  private int volatility;
  private int saturation;
  private int id;

  public Pheromone() {
    this.volatility = 1;
    this.saturation = 24 * 60;
    this.id = lastId++;
  }

  public int getVolatility() {
    return this.volatility;
  }

  public int getSaturation() {
    return saturation;
  }

  public void setSaturation(int saturation) {
    this.saturation = saturation;
  }

  public int getId() {
    return id;
  }

  public static int lastId = 0;
}
