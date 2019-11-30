package model.entities;


import model.abstracts.EntityTile;
import model.abstracts.State;
import model.abstracts.AntRole;
import model.data.Species;
import model.developments.Egg;
import model.interfaces.IEntityTile;
import model.world.Tile;
import model.world.Time;

/**
 * An model.ant is an element who eat, evolve and have a behaviour.
 * @author lostanth
 *
 */
public class Ant extends EntityTile {
  private State state;
  private Species species;

  public Ant(Tile tile, Species species) {
    super(tile);
    this.species = species;
    this.state = new Egg();
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

  @Override
  public void update(){
    state.action(this);
  }
  
}
