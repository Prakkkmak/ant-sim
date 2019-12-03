package model.roles;

import java.util.ArrayList;
import java.util.List;
import model.abstracts.AntRole;
import model.data.Species;
import model.entities.Ant;
import model.enums.EType;
import model.world.Pheromone;
import model.world.Tile;

public class Worker extends AntRole {

  private Tile fromTile;
  private List<Tile> fromTiles;

  public Worker() {
    fromTiles = new ArrayList<>();
  }

  //TODO doc this.
  //TODO factoriser.
  @Override
  public void action(Ant ant) {
    Species antSpecies = ant.getSpecies();
    int antStamina = ant.getStamina();
    int speciesStamina = antSpecies.getStamina();
    Pheromone speciesPheromone = antSpecies.getHomePheromone();

    if (Math.random() < 0.001) { // AFK rate
      return;
    }

    if (ant.getTile().equals(ant.getHome())) {
      this.house(ant);
    } else if(ant.getTile().getFood() > 0 && ant.getCarriedFood() < ant.getSpecies().getMaxCarry()) {
      this.carry(ant);
    } else if(ant.getCarriedFood() > 0){
      this.moveTowardHome(ant);
      ant.getTile().addPheromone(speciesPheromone, antSpecies.getPheromoneDropRate() * 700);
    } else if (antStamina < speciesStamina / 2) {
      this.moveTowardHome(ant);
      ant.getTile().addPheromone(speciesPheromone, antSpecies.getPheromoneDropRate());
    } else {
      this.explore(ant);
      ant.getTile().addPheromone(speciesPheromone, antSpecies.getPheromoneDropRate());
    }

  }

  @Override
  public EType getType() {
    return EType.WORKER;
  }

  private void carry(Ant ant){
    Tile antTile = ant.getTile();
    Species antSpecies = ant.getSpecies();
    int amountOfFood = antTile.getFood();

    antTile.removeFood(amountOfFood);
    ant.addCarriedFood(amountOfFood);

    if(ant.getCarriedFood() > antSpecies.getMaxCarry()){
      int foodToRemove = ant.getCarriedFood() - antSpecies.getMaxCarry();
      ant.getTile().setFood(foodToRemove);
      ant.setCarriedFood(antSpecies.getMaxCarry());
    }
  }

  private void house(Ant ant) {
    if(ant.getCarriedFood() > 0){
      ant.getTile().addFood(ant.getCarriedFood());
      ant.setCarriedFood(0);
    }
    int speciesStamina = ant.getSpecies().getStamina();
    this.fromTiles = new ArrayList<>();
    ant.setStamina(speciesStamina);
    float chanceToGoOutside = 1 / (float) speciesStamina;
    if (Math.random() < chanceToGoOutside) {
      ant.consume(ant.getSpecies().getFoodConsumption() - ant.getFood());
      explore(ant);
    }
  }

  private void explore(Ant ant) {
    if (this.fromTiles.size() == 0) {
      this.fromTiles.add(ant.getHome());
    }
    Species species = ant.getSpecies();
    Pheromone speciesPheromone = species.getHomePheromone();
    Tile northTile = ant.getTile().getNorthTile();
    Tile eastTile = ant.getTile().getEastTile();
    Tile southTile = ant.getTile().getSouthTile();
    Tile westTile = ant.getTile().getWestTile();
    if (fromTile == null) {
      fromTile = ant.getTile();
    }
    int chanceToGoNorth = 0;
    int chanceToGoEast = 0;
    int chanceToGoSouth = 0;
    int chanceToGoWest = 0;
    /* Add random behaviour */
    int randomMovementRate = species.getRandomMovementRate();
    chanceToGoNorth += randomMovementRate;
    chanceToGoEast += randomMovementRate;
    chanceToGoSouth += randomMovementRate;
    chanceToGoWest += randomMovementRate;
    /* Add linear behaviour to chances. */
    int linearMovementRate = species.getLinearMovementRate();
    if (fromTile.equals(northTile)) {
      chanceToGoSouth += linearMovementRate;
    }
    if (fromTile.equals(eastTile)) {
      chanceToGoWest += linearMovementRate;
    }
    if (fromTile.equals(southTile)) {
      chanceToGoNorth += linearMovementRate;
    }
    if (fromTile.equals(westTile)) {
      chanceToGoEast += linearMovementRate;
    }
    /* Add pheromones if tile exist, chance = 0 otherwise */
    if (this.fromTiles.contains(northTile) || northTile == null) {
      chanceToGoNorth = 0;
    } else {
      chanceToGoNorth += northTile.getPheromoneRate(speciesPheromone);
    }
    if (this.fromTiles.contains(eastTile) || eastTile == null) {
      chanceToGoEast = 0;
    } else {
      chanceToGoEast += eastTile.getPheromoneRate(speciesPheromone);
    }
    if (this.fromTiles.contains(southTile) || southTile == null) {
      chanceToGoSouth = 0;
    } else {
      chanceToGoSouth += southTile.getPheromoneRate(speciesPheromone);
    }
    if (this.fromTiles.contains(westTile) || westTile == null) {
      chanceToGoWest = 0;
    } else {
      chanceToGoWest += westTile.getPheromoneRate(speciesPheromone);
    }
    /* Calculate chances */
    int totalChance = chanceToGoNorth + chanceToGoEast + chanceToGoSouth + chanceToGoWest;
    if(totalChance == 0){
      if(northTile != null) chanceToGoNorth = 10;
      if(eastTile != null) chanceToGoEast = 10;
      if(southTile != null) chanceToGoSouth = 10;
      if(westTile != null) chanceToGoWest = 10;
      totalChance = chanceToGoNorth + chanceToGoEast + chanceToGoSouth + chanceToGoWest;
    }
    double northRate = chanceToGoNorth / (double) totalChance;
    double eastRate = chanceToGoEast / (double) totalChance;
    double southRate = chanceToGoSouth / (double) totalChance;
    double westRate = chanceToGoWest / (double) totalChance;
    double rng = Math.random();
    this.fromTile = ant.getTile();
    if (rng < northRate) {
      ant.getTile().moveTo(ant, northTile);
      this.fromTiles.add(northTile);
    } else if (rng < eastRate + northRate) {
      ant.getTile().moveTo(ant, eastTile);
      this.fromTiles.add(eastTile);
    } else if (rng < southRate + eastRate + northRate) {
      ant.getTile().moveTo(ant, southTile);
      this.fromTiles.add(southTile);
    } else {
      ant.getTile().moveTo(ant, westTile);
      this.fromTiles.add(westTile);
    }
    if (this.fromTiles.size() > 15) {
      this.fromTiles.remove(1);
    }
  }

}
