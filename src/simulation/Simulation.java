package simulation;

import java.util.Date;
import model.world.World;
import view.GlobalFrame;

public class Simulation {

  private int worldSize;
  private int startingWorkers;
  private int minutesPerTick;
  private int markedWorkersNumber;
  private int tick;
  private int refreshRate;
  private World world;

  public Simulation(int worldSize, int startingWorkers, int minutesPerTick, int refreshRate,
      int markedNumber) {
    this.worldSize = worldSize;
    this.startingWorkers = startingWorkers;
    this.minutesPerTick = minutesPerTick;
    this.markedWorkersNumber = markedNumber;
    this.refreshRate = refreshRate;
    this.tick = 1;
  }

  public Simulation(int worldSize, int startingWorkers, int minutesPerTick, int refreshRate) {
    this(worldSize, startingWorkers, minutesPerTick, refreshRate, 0);
    this.markedWorkersNumber = this.startingWorkers / 10;
  }

  public int getWorldSize() {
    return worldSize;
  }

  public void setWorldSize(int worldSize) {
    this.worldSize = worldSize;
  }

  public int getStartingWorkers() {
    return startingWorkers;
  }

  public void setStartingWorkers(int startingWorkers) {
    this.startingWorkers = startingWorkers;
  }

  public int getMinutesPerTick() {
    return minutesPerTick;
  }

  public void setMinutesPerTick(int minutesPerTick) {
    this.minutesPerTick = minutesPerTick;
  }

  public int getMarkedWorkersNumber() {
    return markedWorkersNumber;
  }

  public void setMarkedWorkersNumber(int markedWorkersNumber) {
    this.markedWorkersNumber = markedWorkersNumber;
  }

  public int getTick() {
    return tick;
  }

  public void setTick(int tick) {
    this.tick = tick;
  }

  public int getRefreshRate() {
    return refreshRate;
  }

  public void setRefreshRate(int refreshRate) {
    this.refreshRate = refreshRate;
  }

  public World getWorld() {
    return world;
  }

  public void init() {
    int worldSize = this.getWorldSize();
    this.world = new World(worldSize, worldSize);

    int startingTile = worldSize / 2;
    this.world.createAnthill(startingTile, startingTile);

    for (int i = 0; i < this.getMarkedWorkersNumber(); i++) {
      this.world.createWorker(startingTile, startingTile, true);
    }

    for (int i = 0; i < this.getStartingWorkers(); i++) {
      this.world.createWorker(startingTile, startingTile);
    }
  }

  public void run() {
    //View view = new View(this.world);
    GlobalFrame globalFrame = new GlobalFrame(this);
    //view.update();
    Date date = new Date();
    long time = date.getTime();
    while (true) {
      date = new Date();
      long dateMs = date.getTime();
      if (dateMs - time > this.getRefreshRate()) {
        for (int i = 0; i < this.getMinutesPerTick(); i++) {
          world.onMinutePass(this.tick++);
        }
        globalFrame.update();
        time = date.getTime();
      }
    }
  }
}
