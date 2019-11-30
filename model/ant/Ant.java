package ant;


import abstracts.State;
import abstracts.Role;
import developments.Egg;
import developments.Mature;
import interfaces.IEntityTile;
import world.Tile;
import world.Time;

/**
 * An ant is an element who eat, evolve and have a behaviour.
 * @author lostanth
 *
 */
public class Ant implements IEntityTile {
  private int lastAction;
  private Time age;
  protected Tile tile;
 
  private State state;
  
  public Ant(Tile tile) {
    this.lastAction = 0;
    this.state = new Egg();
    this.age = new Time();
    this.tile = tile;
  }
  
  public int getX() {
    return this.tile.getX();
  }
  
  public int getY() {
    return this.tile.getY();
  }

  public Tile getTile() {
    return this.tile;
  }
  public void setTile(Tile tile) {
    this.tile = tile;
  }
  
  public State getState() {
    return this.state;
  }
  
  public void setState(State state) {
    this.state = state;
  }
  
  public Role getRole() {
    return state.getRole();
  }
  public void setRole(Role role) {
    state.setRole(role);
  }
  
  @Override
  public void onMinutePass(int tick) {
    if(tick == lastAction) {
      return;
    }
    // TODO Auto-generated method stub
    state.action(this);
    lastAction = tick;
  }
  
}
