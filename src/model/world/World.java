package model.world;

import java.util.Map;
import model.data.Species;
import model.developments.Egg;
import model.entities.Ant;
import model.entities.Anthill;
import model.entities.Prey;
import model.enums.EType;
import model.factories.AntFactory;
import model.factories.SpeciesFactory;
import model.interfaces.IEntityTile;
import model.interfaces.ITickable;
import model.roles.Soldier;
import model.roles.Worker;
import model.visitors.AntCounterVisitor;
import model.visitors.FoodCounterVisitor;

/**
 * The model.world is where the sim append. It's a list of tiles.
 *
 * @author lostanth
 */
public class World implements ITickable {

  private Time date;
  /**
   * Tiles composes the model.world.
   */
  private Tile[][] tiles;
  private AntFactory antFactory;

  /**
   * Creation of the model.world by its size.
   *
   * @param sizeX Number of X tiles.
   * @param sizeY Number of Y tiles.
   */
  public World(int sizeX, int sizeY) {
    Species species = SpeciesFactory.CreateDefaultSpecies();
    this.antFactory = new AntFactory(species);
    this.date = new Time();
    this.tiles = new Tile[sizeX][sizeY];
    for (int i = 0; i < this.tiles.length; i++) {
      for (int j = 0; j < this.tiles[i].length; j++) {
        this.tiles[i][j] = new Tile(this, i, j);
      }
    }
  }

  public World(int size) {
    this(size, size);
  }

  /**
   * Number of tiles in x axis.
   *
   * @return The number of tiles.
   */
  public int getSizeX() {
    return this.tiles.length;
  }

  /**
   * Number of tiles in y axis.
   *
   * @return The number of tiles.
   */
  public int getSizeY() {
    return this.tiles[0].length;
  }

  /**
   * Get the content of the tile.
   *
   * @param x X.
   * @param y Y.
   * @return 0 - nothing, 1 - an model.ant, 2 - a fourmiliere.
   */
  public int getTileContent(int x, int y) {
    return this.tiles[x][y].getContent();
  }

  public int getPheromoneConcentration(int x, int y) {
    return this.tiles[x][y].getPheromoneConcentration();
  }

  public int getPheromoneConcentration(int x, int y, int i) {
    return this.tiles[x][y].getPheromoneRate(i);
  }
  public int getFoodConcentration(int x, int y){
    return this.tiles[x][y].getFood();
  }

  /**
   * Create a soldier at specific coords.
   *
   * @param x Position X.
   * @param y Position Y.
   */
  public void createEgg(int x, int y) {
    Ant ant = antFactory.createAnt(new Egg());
    this.addEntity(ant, x, y);
  }

  /**
   * Create a soldier at specific coords.
   *
   * @param x Position X.
   * @param y Position Y.
   */
  public void createSoldier(int x, int y) {
    Ant ant = antFactory.createAnt(new Soldier());
    this.addEntity(ant, x, y);
  }

  /**
   * Create a worker at specific coords.
   *
   * @param x Position X.
   * @param y Position Y.
   */
  public void createWorker(int x, int y) {
    createWorker(x, y, false);
  }

  /**
   * Create a worker at specific coords with a mark.
   *
   * @param x      Position X.
   * @param y      Position Y.
   * @param marked True if it's marked
   */
  public void createWorker(int x, int y, boolean marked) {
    Ant ant = antFactory.createAnt(this.tiles[x][y], new Worker());
    ant.setMarked(marked);
    this.addEntity(ant, x, y);
  }

  /**
   * Create a queen at specific coords.
   *
   * @param x Position X.
   * @param y Position Y.
   */
  public void createAnthill(int x, int y) {
    Anthill anthill = this.antFactory.createAnthill(this.tiles[x][y]);
    this.addEntity(anthill, x, y);
    this.tiles[x][y].addFood(100000);
    this.antFactory.setAnthill(anthill);
  }

  public void createFood(int x, int y, int amount){
    this.tiles[x][y].addFood(amount);
  }
  public void randomPrey(){
    double r = Math.random();
    double chanceToDropFood = (1 - 1 / (double)(this.getSizeX() * this.getSizeY())) / 1000;
    if(r < chanceToDropFood){
      int posX = (int)(Math.random() * this.getSizeX());
      int posY = (int)(Math.random() * this.getSizeY());
      this.tiles[posX][posY].addEntity(new Prey(this.tiles[posX][posY]));
    }
  }
  public void randomFeeder(){
    double r = Math.random();
    double chanceToDropFood = (1 - 1 / (double)(this.getSizeX() * this.getSizeY())) / 500;
    if(r < chanceToDropFood){
      int posX = (int)(Math.random() * this.getSizeX());
      int posY = (int)(Math.random() * this.getSizeY());
      int amount = 0;
      amount = (int) Math.exp(Math.random() * 12) * 50; // 0 -> 100000
      createFood(posX, posY, amount);
    }
  }

  /**
   * Called when an hour is passed.
   */
  @Override
  public void onMinutePass(int tick) {
    date.onMinutePass(tick);
    for (int i = 0; i < this.tiles.length; i++) {
      for (int j = 0; j < this.tiles[i].length; j++) {
        this.tiles[i][j].onMinutePass(tick);
      }
    }
    //TODO create spawner class
    randomFeeder();
    randomPrey();
  }

  /**
   * Add entity at specific tile.
   *
   * @param entity The entity to add.
   * @param x      Pos X.
   * @param y      Pos Y.
   */
  void addEntity(IEntityTile entity, int x, int y) {
    if (x >= this.tiles.length) {
      x = this.tiles.length - 1;
    }
    if (x < 0) {
      x = 0;
    }
    if (y >= this.tiles.length) {
      y = this.tiles[0].length - 1;
    }
    if (y < 0) {
      y = 0;
    }
    this.tiles[x][y].addEntity(entity);
  }

  public Map<EType, Integer> getOutsides(){
    AntCounterVisitor visitor = new AntCounterVisitor();
    for (Tile[] tiles : this.tiles) {
      for (Tile tile : tiles) {
        tile.accept(visitor);
      }
    }
    return visitor.getOutside();
  }

  public Map<EType, Integer> getInsides(){
    AntCounterVisitor visitor = new AntCounterVisitor();
    for (Tile[] tiles : this.tiles) {
      for (Tile tile : tiles) {
        tile.accept(visitor);
      }
    }
    return visitor.getInside();
  }
  /**
   * Get ant count. Count all ant in all states.
   *
   * @return The ant count.
   */
  public int getAntCount() {
    AntCounterVisitor visitor = new AntCounterVisitor();
    for (Tile[] tiles : this.tiles) {
      for (Tile tile : tiles) {
        tile.accept(visitor);
      }
    }
    //return visitor.getAntCount();
    return 0;
  }

  public int getFoodInsides(){
    FoodCounterVisitor visitor = new FoodCounterVisitor();
    for (Tile[] tiles : this.tiles) {
      for (Tile tile : tiles) {
        tile.accept(visitor);
      }
    }
    return visitor.getInsideFood();
  }

  public int getFoodOutsides(){
    FoodCounterVisitor visitor = new FoodCounterVisitor();
    for (Tile[] tiles : this.tiles) {
      for (Tile tile : tiles) {
        tile.accept(visitor);
      }
    }
    return visitor.getOutsideFood();
  }

  /**
   * Get number of days of the world.
   *
   * @return The number of days.
   */
  public int getDays() {
    return this.date.getDay();
  }
  public boolean getMarked(int x, int y) {
    return this.tiles[x][y].isMarked();
  }
  /**
   * Get a tile at specific location. TODO Ne pas retourner de tile
   *
   * @param x Position X.
   * @param y Position Y.
   * @return The tile at this position.
   */
  public Tile getTile(int x, int y) {
    return this.tiles[x][y];
  }

  public Time getDate() {
    return date;
  }
}
