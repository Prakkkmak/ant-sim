package model.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.entities.Ant;
import model.enums.EType;

public class AnthillInfo {

  private Map<EType, List<Ant>> ants;
  private int workersOutside;

  public AnthillInfo() {
    this.ants = new HashMap<>();
    this.workersOutside = 0;
  }

  public void addAnt(Ant ant){
    EType type = ant.getType();
    ants.computeIfAbsent(type, k -> new ArrayList<>());
    ants.get(type).add(ant);
  }
  public void removeAnt(Ant ant){
    EType type = ant.getType();
    if(ants.get(type) == null) return;
    ants.get(type).remove(ant);
  }

  public int getWorkersOutside() {
    return this.workersOutside;
  }

  public void setWorkersOutside(int workersOutside) {
    this.workersOutside = workersOutside;
  }
}
