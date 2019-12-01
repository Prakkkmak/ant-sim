package model.entities;


import model.abstracts.EntityTile;
import model.abstracts.Role;
import model.abstracts.State;
import model.abstracts.AntRole;
import model.data.Species;
import model.developments.Egg;
import model.developments.Garbage;
import model.enums.ESprite;
import model.interfaces.IEntityTile;
import model.interfaces.IVisitor;
import model.roles.Queen;
import model.world.Tile;
import model.world.Time;

/**
 * An model.ant is an element who eat, evolve and have a behaviour.
 * @author lostanth
 *
 */
public class Ant extends EntityTile {
  private int food;
  private State state;
  private Species species;

  public Ant(Tile tile, Species species) {
    super(tile);
    this.species = species;
    this.state = new Egg();
    this.food = this.species.getFoodConsumption();
  }
  
  public State getState() {
    return this.state;
  }
  
  public void setState(State state) {
    this.state = state;
  }
  
  public AntRole getRole() {
    return this.state.getAntRole();
  }

  public void setRole(AntRole antRole) {
    this.state.setAntRole(antRole);
  }

  public Species getSpecies(){
    return this.species;
  }

  public int getFood() { return this.food; }

  /**
   * Consume the food in the current tile. If the tile don't have enough food, the ant consume all the food remaining.
   * If the ant have less than 0 food they become garbage.
   * @param amount the amount of food to consume.
   */
  public void consume(int amount){
    int tileFood = this.getTile().getFood();
    if(tileFood >= amount){
      this.food += amount;
      this.getTile().removeFood(amount);
    }
    else{
      this.food += tileFood;
      this.getTile().removeFood(tileFood);
    }
    if(this.getFood() < 0){
      this.setState(new Garbage());
    }
  }

  public void consume(){
    this.consume(this.species.getFoodConsumption());
  }

  @Override
  public void update(){
    state.action(this);
  }

  @Override
  public void accept(IVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public int getId() {
    AntRole antRole = this.getRole();
    if(antRole == null) return ESprite.GARBAGE.getValue();
    return antRole.getId();
  }

}
