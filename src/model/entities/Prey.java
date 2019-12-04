package model.entities;

import model.abstracts.EntityTile;
import model.abstracts.Role;
import model.enums.EType;
import model.interfaces.IEntityTile;
import model.interfaces.IVisitor;
import model.world.Pheromone;
import model.world.Tile;

public class Prey extends EntityTile {

  private double chanceToMoveRate;
  private int weight;

  private int moveX;
  private int moveY;

  public Prey(Tile tile) {
    super(tile);
    this.chanceToMoveRate = Math.random();
    if(this.chanceToMoveRate < 0.2) this.chanceToMoveRate = 0;
    this.weight = (int) (Math.random() * 10000);
    moveX = 0;
    moveY = 0;
  }

  private void wandering(){
    double r = Math.random();
    if(1 - r < this.chanceToMoveRate * 4) return;

    int x = this.getX();
    int y = this.getY();
    if(moveX == 0 && moveY == 0){
      r = Math.random();
      if(r < 0.25){
        moveX++;
      } else if( r < 0.5){
        moveX--;
      } else if(r < 0.75){
        moveY++;
      }
      else {
        moveY--;
      }
    }
    x += moveX;
    y += moveY;
    for (Object o: this.getTile().getEntities().toArray()) {
      IEntityTile e = (IEntityTile) o;
      if(e.getType().equals(EType.WORKER)){
        //Pheromone antPheromone = ((Ant) e).getSpecies().getHomePheromone();
        //this.getTile().addPheromone(antPheromone, 2000);
        this.getTile().moveTo(e, x, y);
      }
    }
    this.getTile().moveTo(this, x, y);
  }

  public int getWeight() {
    return weight;
  }

  private void wayToDie(){
    int totalWeight = 0;
    for (Object o: this.getTile().getEntities().toArray()) {
      IEntityTile e = (IEntityTile) o;
      if(e.getType().equals(EType.WORKER)){
        Ant a = (Ant) e;
        totalWeight += a.getWeight();
      }
    }
    if (this.getWeight() < totalWeight){
      this.getTile().addFood(this.weight);
      this.getTile().removeEntity(this);
    }
  }

  @Override
  public void update() {
    this.wandering();
    this.wayToDie();
  }

  @Override
  public void accept(IVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public EType getType() {
    return EType.PREY;
  }
}
