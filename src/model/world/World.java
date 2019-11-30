package model.world;

import model.data.Species;
import model.entities.Ant;
import model.factories.AntFactory;
import model.factories.SpeciesFactory;
import model.interfaces.IEntityTile;
import model.interfaces.ITickable;
import model.roles.Queen;
import model.roles.Soldier;
import model.roles.Worker;

/**
 * The model.world is where the sim append. It's a list of tiles.
 * @author lostanth
 *
 */
public class World implements ITickable{
  /**
   * Tiles composes the model.world.
   */
  private Tile[][] tiles;
  private AntFactory antFactory;
  /**
   * Creation of the model.world by its size.
   * @param sizeX Number of X tiles.
   * @param sizeY Number of Y tiles.
   */
  public World(int sizeX, int sizeY) {
    Species species = SpeciesFactory.CreateDefaultSpecies();
    this.antFactory = new AntFactory(species);
    this.tiles = new Tile[sizeX][sizeY];
    for(int i = 0; i < this.tiles.length; i++) {
      for(int j = 0; j < this.tiles[i].length; j++) {
        this.tiles[i][j] = new Tile(this, i,j);
      }
    }
  }
  /**
   * Number of tiles in x axis.
   * @return The number of tiles.
   */
  public int getSizeX() {
    return this.tiles.length;
  }
  /**
   * Number of tiles in y axis.
   * @return The number of tiles.
   */
  public int getSizeY() {
    return this.tiles[0].length;
  }
  /**
   * Get the content of the tile.
   * @param x X.
   * @param y Y.
   * @return 0 - nothing, 1 - an model.ant, 2 - a fourmiliere.
   */
  public int getTileContent(int x, int y) {
    return this.tiles[x][y].getContent();
  }

  /**
   * Create a soldier at specific coords.
   * @param x Position X.
   * @param y Position Y.
   */
  public void createSoldier(int x, int y) {
    Ant ant = antFactory.createAnt(new Soldier());
    this.addEntity(ant, x, y);
  }

  /**
   * Create a worker at specific coords.
   * @param x Position X.
   * @param y Position Y.
   */
  public void createWorker(int x, int y) {
    Ant ant = antFactory.createAnt(new Worker());
    this.addEntity(ant, x, y);
  }

  /**
   * Create a queen at specific coords.
   * @param x Position X.
   * @param y Position Y.
   */
  public void createQueen(int x, int y) {
    Ant ant = antFactory.createAnt(new Queen());
    this.addEntity(ant, x, y);
  }
  
  /**
   * Called when an hour is passed.
   */
  @Override
  public void onMinutePass(int tick) {
    for(int i = 0; i < this.tiles.length; i++) {
      for(int j = 0; j < this.tiles[i].length; j++) {
        this.tiles[i][j].onMinutePass(tick);
      }
    }
  }

  /**
   * Add entity at specific tile.
   * @param entity The entity to add.
   * @param x Pos X.
   * @param y Pos Y.
   */
  void addEntity(IEntityTile entity, int x, int y) {
    if(x >= this.tiles.length) {
      x = this.tiles.length - 1;
    }
    if(x < 0) {
      x = 0;
    }
    if(y >= this.tiles.length) {
      y = this.tiles[0].length - 1;
    }
    if(y < 0) {
      y = 0;
    }
    this.tiles[x][y].addEntity(entity);
  }
}
