package model.entities;

import model.abstracts.EntityTile;
import model.data.AnthillInfo;
import model.data.Species;
import model.developments.Egg;
import model.enums.EType;
import model.factories.AntFactory;
import model.interfaces.IVisitor;
import model.world.Tile;

public class Anthill extends EntityTile {

  private int lastEggingDay;
  private Species species;
  private AnthillInfo infos;

  public Anthill(Tile tile, Species species) {
    super(tile);
    this.lastEggingDay = 0;
    this.species = species;
    this.infos = new AnthillInfo();
  }


  public void createEgg() {
    int ageDay = this.getAge().getDay();
    if (ageDay <= lastEggingDay) {
      return;
    }
    System.out.println("Début de la gestation");
    for (int i = 0; i < this.getSpecies().getNumberOfEggPerDay(); i++) {
      AntFactory antFactory = new AntFactory(this.getSpecies());
      Tile antTile = this.getTile();
      Ant egg = antFactory.createAnt(antTile, new Egg());
      antTile.addEntity(egg);
      this.addAnt(egg);
      System.out.println("Nouvel oeuf");
    }
    lastEggingDay = ageDay;
  }

  public int getLastEggingDay() {
    return lastEggingDay;
  }

  public void setLastEggingDay(int lastEggingDay) {
    this.lastEggingDay = lastEggingDay;
  }

  public Species getSpecies() {
    return species;
  }

  public void setSpecies(Species species) {
    this.species = species;
  }

  public AnthillInfo getInfos() {
    return infos;
  }

  public void addAnt(Ant ant){
    this.infos.addAnt(ant);
  }

  @Override
  public EType getType() {
    return EType.ANTHILL;
  }

  @Override
  public void update() {
    if(this.getTile().getWorld().getDate().getSeason() % 4 == 0){
      this.createEgg();
    }
  }

  @Override
  public void accept(IVisitor visitor) {

  }


}
