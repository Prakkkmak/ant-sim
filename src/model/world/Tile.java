package model.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.entities.Ant;
import model.interfaces.IEntityTile;
import model.interfaces.ITickable;
import model.interfaces.IVisitable;
import model.interfaces.IVisitor;

public class Tile implements ITickable, IVisitable {
  
  private List<IEntityTile> entities;
  private Map<Pheromone, Integer> pheromones;

  private World world;

  private int x;
  private int y;
  private int food;
  
  public Tile(World world, int x, int y) {
    this.entities = new ArrayList<>();
    this.pheromones = new HashMap<>();
    this.world = world;
    this.x = x;
    this.y = y;
    this.food = 0;
  }
  
  public int getX() {
     return this.x;
  }
  
  public int getY() {
    return this.y;
  }

  public int getContent() {
    int content = 0;
    for(IEntityTile entity : entities){
      if(entity.getId() > content) content = entity.getId();
    }
    return content;
  }

  public List<IEntityTile> getEntities(){
    return entities;
  }

  public void addEntity(IEntityTile entity) {
    entities.add(entity);
    entity.setTile(this);
  }
  
  public void moveTo(IEntityTile entity, int x, int y) {
    world.addEntity(entity, x, y);
    this.entities.remove(entity);
  }
  public void moveTo(IEntityTile entity, Tile tile) {
    this.moveTo(entity, tile.getX(), tile.getY());
  }
  public int getFood(){
    return this.food;
  }
  public void setFood(int food){
    this.food = food;
  }
  public void addFood(int food){
    this.setFood(this.getFood() + food);
  }
  public void removeFood(int food){
    this.setFood(this.getFood() - food);
  }

  public Tile getNorthTile(){
    if(getY() - 1 < 0) return null;
    return this.world.getTile(getX(), getY() - 1);
  }
  public Tile getEastTile(){
    if(getX() + 1 >= world.getSizeX()) return null;
    return this.world.getTile(getX() + 1, getY());
  }
  public Tile getSouthTile(){
    if(getY() + 1 >  world.getSizeY()) return null;
    return this.world.getTile(getX(), getY() + 1);
  }
  public Tile getWestTile(){
    if(getX() - 1 < 0) return null;
    return this.world.getTile(getX() - 1, getY());
  }

  public int getPheromoneRate(Pheromone p){
    Integer rate = this.pheromones.get(p);
    if(rate == null) rate = 0;
    return rate;
  }

  public void addPheromone(Pheromone p, int amount){
    this.pheromones.put(p, amount);
  }

  public int getPheromoneConcentration(){
    int total = 0;
    for(Integer pheromone : pheromones.values()){
      total += pheromone;
    }
    return total;
  }

  /**
   * The tile update all his entities. The tiles also remove 1 of each pheromone.
   * @param tick Current tick.
   */
  @Override
  public void onMinutePass(int tick) {
    for(Object o : entities.toArray()) {
      IEntityTile entity = (IEntityTile) o;
      entity.onMinutePass(tick);
    }
    for(Map.Entry<Pheromone, Integer> pheromone : pheromones.entrySet()){
      int volatility = pheromone.getKey().getVolatility();
      int value = pheromone.getValue();
      pheromone.setValue(value - volatility);
      if(pheromone.getValue() < 0) pheromone.setValue(0);
    }
  }

  @Override
  public void accept(IVisitor visitor) {
    visitor.visit(this);
  }
}
