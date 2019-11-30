package world;

import java.util.ArrayList;
import java.util.List;
import enums.EDirection;
import interfaces.IEntityTile;
import interfaces.ITickable;

public class Tile implements ITickable {
  
  private List<IEntityTile> entities;
  
  private World world;
  
  private int x;
  private int y;
  
  public Tile(World world, int x, int y) {
    this.entities = new ArrayList<>();
    this.world = world;
    this.x = x;
    this.y = y;
  }
  
  public int getX() {
     return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getContent() {
    if(this.entities.size() > 0) return 1;
    else return 0;
  }
  
  public void addEntity(IEntityTile entity) {
    entities.add(entity);
    entity.setTile(this);
  }
  
  public void moveTo(IEntityTile entity, int x, int y) {
    world.addEntity(entity, x, y);
    this.entities.remove(entity);
  }
  
  @Override
  public void onMinutePass(int tick) {
    for(Object o : entities.toArray()) {
      IEntityTile entity = (IEntityTile) o;
      entity.onMinutePass(tick);
    }
  }
}
