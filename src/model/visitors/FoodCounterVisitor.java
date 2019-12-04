package model.visitors;

import model.entities.Anthill;
import model.enums.EType;
import model.interfaces.IEntityTile;
import model.interfaces.IVisitable;
import model.interfaces.IVisitor;
import model.world.Tile;

public class FoodCounterVisitor implements IVisitor {

  private int insideFood;
  private int outsideFood;

  public FoodCounterVisitor() {
    this.insideFood = 0;
    this.outsideFood = 0;
  }

  public int getInsideFood() {
    return insideFood;
  }

  public void setInsideFood(int insideFood) {
    this.insideFood = insideFood;
  }

  public void addInsideFood(int amount){
    this.insideFood += amount;
  }

  public int getOutsideFood() {
    return outsideFood;
  }

  public void setOutsideFood(int outsideFood) {
    this.outsideFood = outsideFood;
  }

  public void addOutsideFood(int amount){
    this.outsideFood += amount;
  }

  @Override
  public void visit(IVisitable visitable) {
    return;
  }

  @Override
  public void visit(Tile tile) {
    if(tile.contains(EType.ANTHILL)){
      this.addInsideFood(tile.getFood());
    } else {
      this.addOutsideFood(tile.getFood());
    }
  }

  @Override
  public void visit(IEntityTile entityTile) {

  }
}
