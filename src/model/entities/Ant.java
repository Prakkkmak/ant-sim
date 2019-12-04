package model.entities;


import model.abstracts.AntRole;
import model.abstracts.EntityTile;
import model.abstracts.State;
import model.data.Species;
import model.developments.Egg;
import model.developments.Garbage;
import model.enums.EType;
import model.interfaces.IVisitor;
import model.world.Tile;

/**
 * An model.ant is an element who eat, evolve and have a behaviour.
 *
 * @author lostanth
 */
public class Ant extends EntityTile {

  /**
   * Quantity of food eaten.
   */
  private int food;

  /**
   * State of development.
   */
  private State state;

  /**
   * Data of ant.
   */
  private Species species;

  /**
   * Time ant can stay outside.
   */
  private int stamina;
  private int carriedFood;
  private int weight;
  /**
   * Position of the creation of the ant. Where the ant come back.
   */
  private Tile home;
  /**
   * Queen of the ant. The queen have all infos about the ant.
   */
  private Ant queen;

  /**
   * Constructor of ant by tile and species.
   *
   * @param tile    The tile where ant is created.
   * @param species The species of the ant.
   */
  public Ant(Tile tile, Species species) {
    super(tile);
    this.species = species;
    this.state = new Egg();
    this.food = this.species.getFoodConsumption();
    this.stamina = this.species.getStamina();
    this.home = tile;
    this.weight = species.getWeight();
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

  public Species getSpecies() {
    return this.species;
  }

  public int getStamina() {
    return this.stamina;
  }

  public void setStamina(int newStamina) {
    this.stamina = newStamina;
    if(stamina < 0) this.state = new Garbage();
  }

  public int getWeight() {
    return weight;
  }

  // TODO decrease remplace remove pour food de tile.
  public void decreaseStamina(int amount) {
    this.setStamina(this.getStamina() - amount);
  }

  /**
   * Decrease stamina by 1.
   */
  public void decreaseStamina() {

    this.decreaseStamina(1);
  }

  public int getFood() {
    return this.food;
  }

  public void decreaseFood(int amount) {
    this.food -= amount;
  }

  public void decreaseFood(){
    decreaseFood(1);
  }

  public Tile getHome() {
    return this.home;
  }

  public int getCarriedFood() {
    return carriedFood;
  }

  public void setCarriedFood(int carriedFood) {
    this.carriedFood = carriedFood;
  }

  public void addCarriedFood(int amount){
    this.setCarriedFood(this.getCarriedFood() + amount);
  }

  /**
   * Consume the food in the current tile. If the tile don't have enough food, the ant consume all
   * the food remaining. If the ant have less than 0 food they become garbage.
   *
   * @param amount The amount of food to consume.
   */
  public void consume(int amount) {
    int tileFood = this.getTile().getFood();
    if (tileFood >= amount) {
      this.food += amount;
      this.getTile().removeFood(amount);
    } else {
      this.food += tileFood;
      this.getTile().removeFood(tileFood);
    }
  }

  public void consume() {
    this.consume(this.getSpecies().getFoodConsumption());
  }

  public void moveTo(Tile nextTile) {
    this.getTile().removeEntity(this);
    nextTile.addEntity(this);
  }

  @Override
  public void update() {
    state.action(this);
    if(this.getFood() < 0){
      this.state = new Garbage();
    }
    if(this.getType().equals(EType.EGG) || this.getType().equals(EType.NYMPH)) return;
    decreaseFood();
    decreaseStamina();
  }

  @Override
  public void accept(IVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public EType getType() {
    return getState().getType();
  }


}
