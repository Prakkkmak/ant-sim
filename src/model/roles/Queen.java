package model.roles;

import model.abstracts.AntRole;
import model.developments.Egg;
import model.entities.Ant;
import model.factories.AntFactory;
import model.world.Tile;

public class Queen extends AntRole {
  private int lastEggingDay;

  public Queen(){
    this.lastEggingDay = 0;
  }
  @Override
  public void action(Ant ant) {
    int ageDay = ant.getAge().getDay();
    if(ageDay < lastEggingDay + 2) return;
    //TODO fonction ponte avec parametre parametrable dans species. (augmenter le nombre de ponte)
    AntFactory antFactory = new AntFactory(ant.getSpecies());
    Tile antTile = ant.getTile();
    Ant egg = antFactory.createAnt(antTile, new Egg());
    antTile.addEntity(egg);
    lastEggingDay = ageDay;

  }
  @Override
  public int getId(){
    return 9;
  }

}
