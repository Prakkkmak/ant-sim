package model.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.enums.EType;
import model.interfaces.IEntityTile;
import model.interfaces.ITickable;
import model.interfaces.IVisitable;
import model.interfaces.IVisitor;

/**
 * Composant of a world. A tile is composed of entites and pheromones.
 */
public class Tile implements ITickable, IVisitable {

  /**
   * List of entities in the tile.
   */
  private List<IEntityTile> entities;

  /**
   * The map of pheromone.
   */
  private Map<Pheromone, Integer> pheromones;
  /**
   * The world where the tile is.
   */
  private World world;
  /**
   * The pos x of the tile.
   */
  private int x;
  /**
   * The pos y of the tile.
   */
  private int y;
  /**
   * The number of food in the tile.
   */
  private int food;

  /**
   * Create a tile in a world with pos X and Y.
   *
   * @param world The world where the tile is.
   * @param x     Position x.
   * @param y     Position y.
   */
  public Tile(World world, int x, int y) {
    this.entities = new ArrayList<>();
    this.pheromones = new HashMap<>();
    this.world = world;
    this.x = x;
    this.y = y;
    this.food = 0;
  }

  /**
   * Get the position x of the tile in the world.
   *
   * @return
   */
  public int getX() {
    return this.x;
  }

  /**
   * The position Y of the tile in the world.
   *
   * @return
   */
  public int getY() {
    return this.y;
  }

  /**
   * TODO Change name. Get content of the world. Contents are stored in ESprite.
   *
   * @return The content identifier.
   */
  public int getContent() {
    int content = 0;
    for (IEntityTile entity : entities) {
      if (entity.getType().getValue() > content) {
        content = entity.getType().getValue();
      }
    }
    return content;
  }

  public boolean contains(EType type){
    for (IEntityTile entity : entities) {
      if (entity.getType().equals(type)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get the list of entities.
   *
   * @return
   */
  public List<IEntityTile> getEntities() {
    return entities;
  }

  /**
   * Add a entity.
   *
   * @param entity The entity to add.
   */
  public void addEntity(IEntityTile entity) {
    this.entities.add(entity);
    entity.setTile(this);
  }

  public void removeEntity(IEntityTile entity) {
    this.entities.remove(entity);
    entity.setTile(null);
  }

  /**
   * Move a entity to a specific tile coords. TODO probably not a concern.
   *
   * @param entity The entity to move.
   * @param x      The new position x.
   * @param y      The new position y.
   */
  public void moveTo(IEntityTile entity, int x, int y) {
    world.addEntity(entity, x, y);
    this.entities.remove(entity);
  }

  /**
   * Move a entity to a specific tile.
   *
   * @param entity The entity to move.
   * @param tile   The new tile t.
   */
  public void moveTo(IEntityTile entity, Tile tile) {
    this.moveTo(entity, tile.getX(), tile.getY());
  }

  /**
   * Get the food in the tile.
   *
   * @return The food in the tile.
   */
  public int getFood() {
    return this.food;
  }

  /**
   * Set the number of food in the tile.
   *
   * @param food The number of food.
   */
  public void setFood(int food) {
    this.food = food;
    //if(this.isAntHill()) System.out.println("food à la fourmiliere : " + this.food);
  }

  public boolean isAntHill(){
    return this.getContent() == 10;
  }
  /**
   * Add a specific number of food in the tile.
   *
   * @param food The number of food to add.
   */
  public void addFood(int food) {
    this.setFood(this.getFood() + food);
  }

  /**
   * Remove a specific number of food in the tile.
   *
   * @param food The number of food to remove.
   */
  public void removeFood(int food) {
    this.setFood(this.getFood() - food);
  }

  public World getWorld() {
    return world;
  }

  /**
   * Get the north tile of this tile.
   *
   * @return The north tile.
   */
  public Tile getNorthTile() {
    if (getY() - 1 < 0) {
      return null;
    }
    return this.world.getTile(getX(), getY() - 1);
  }

  /**
   * Get the east tile of this tile.
   *
   * @return The east tile.
   */
  public Tile getEastTile() {
    if (getX() + 1 >= world.getSizeX()) {
      return null;
    }
    return this.world.getTile(getX() + 1, getY());
  }

  /**
   * Get the south tile of this tile.
   *
   * @return the south tile.
   */
  public Tile getSouthTile() {
    if (getY() + 1 >= world.getSizeY()) {
      return null;
    }
    return this.world.getTile(getX(), getY() + 1);
  }

  /**
   * Get the west tile of this tile.
   *
   * @return the west tile.
   */
  public Tile getWestTile() {
    if (getX() - 1 < 0) {
      return null;
    }
    return this.world.getTile(getX() - 1, getY());
  }

  public boolean isMarked() {
    for (IEntityTile e : entities) {
      if (e.isMarked()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get the number of a specific pheromone.
   *
   * @param p The pheromone.
   * @return The number of pheromone.
   */
  public int getPheromoneRate(Pheromone p) {
    Integer rate = this.pheromones.get(p);
    if (rate == null) {
      rate = 0;
    }
    return rate;
  }

  /**
   * Add pheromone.
   *
   * @param p      The pheromone.
   * @param amount Amount of pheromone to add.
   */
  public void addPheromone(Pheromone p, int amount) {
    if(this.pheromones.containsKey(p)){
      amount += this.pheromones.get(p);
    }
    if(amount > 1200) amount = 1200;
    this.pheromones.put(p, amount);
  }

  /**
   * Get the sum of all pheromones.
   *
   * @return The sum of pheromones.
   */
  public int getPheromoneConcentration() {
    int total = 0;
    for (Integer pheromone : pheromones.values()) {
      total += pheromone;
    }
    return total;
  }

  /**
   * The tile update all his entities. The tiles also remove 1 of each pheromone.
   *
   * @param tick Current tick.
   */
  @Override
  public void onMinutePass(int tick) {
    for (Object o : entities.toArray()) {
      IEntityTile entity = (IEntityTile) o;
      entity.onMinutePass(tick);
    }
    for (Map.Entry<Pheromone, Integer> pheromone : pheromones.entrySet()) {
      //TODO improve readibility
      int volatility = pheromone.getKey().getVolatility();
      int value = pheromone.getValue();
      pheromone.setValue((int) (value * 0.999));
      if (pheromone.getValue() < 0) {
        pheromone.setValue(0);
      }
    }
  }

  @Override
  public void accept(IVisitor visitor) {
    visitor.visit(this);
  }
}
