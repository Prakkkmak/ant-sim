package world;

import ant.Ant;
import developments.Mature;
import interfaces.IEntityTile;
import interfaces.ITickable;
import roles.Soldier;
/**
 * The world is where the sim append. It's a list of tiles.
 * @author lostanth
 *
 */
public class World implements ITickable{
  /**
   * Tiles composes the world.
   */
  private Tile[][] tiles;
  /**
   * Creation of the world by its size.
   * @param sizeX Number of X tiles.
   * @param sizeY Number of Y tiles.
   */
  public World(int sizeX, int sizeY) {
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
   * @return 0 - nothing, 1 - an ant, 2 - a fourmiliere.
   */
  public int getTileContent(int x, int y) {
    return this.tiles[x][y].getContent();
  }
 
  
  public void addEntity(IEntityTile entity, int x, int y) {
    if(x >= this.tiles.length) {
      x = this.tiles.length - 1;
    }
    if(y >= this.tiles.length) {
      y = this.tiles[0].length - 1;
    }
    this.tiles[x][y].addEntity(entity);
  }
  
  public void createSoldier(int x, int y) {
    Ant ant = new Ant(this.tiles[0][0]);
    ant.setState(new Mature());
    ant.setRole(new Soldier());
    this.addEntity(ant, x, y);
    //this.addEntity(new Ant(), x, y);
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
}
