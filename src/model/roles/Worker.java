package model.roles;

import java.util.ArrayList;
import java.util.List;
import model.abstracts.AntRole;
import model.data.Species;
import model.entities.Ant;
import model.world.Pheromone;
import model.world.Tile;
import java.util.Random;

public class Worker extends AntRole {
  Tile fromTile;
  List<Tile> fromTiles;
  public Worker(){
    fromTiles = new ArrayList<>();
  }
  //TODO doc this.
  //TODO factoriser.
  @Override
  public void action(Ant ant) {
    int antStamina = ant.getStamina();
    int speciesStamina = ant.getSpecies().getStamina();

    if( antStamina < speciesStamina/2) {
      if (ant.getTile().equals(ant.getHome())){
        ant.setStamina(ant.getSpecies().getStamina());
        return;
      }
      this.moveTowardHome(ant);
    } 
    else {
      this.explore(ant);
    }
    ant.getTile().addPheromone(ant.getSpecies().getPheromone(), 100); // TODO to species.
    ant.decreaseStamina();
    

  }
  
  private void explore(Ant ant) {
    Species species = ant.getSpecies();
    Pheromone speciesPheromone = species.getPheromone();
    Tile northTile = ant.getTile().getNorthTile();
    Tile eastTile = ant.getTile().getEastTile();
    Tile southTile = ant.getTile().getSouthTile();
    Tile westTile = ant.getTile().getWestTile();
    if (fromTile == null)
      fromTile = ant.getTile();
    //TODO mettre le +100 dans l'especes (chance de base d'aller dans un non phéro.)
    int northPheromone = (northTile == null || this.fromTile.equals(northTile)) ? 0
        : northTile.getPheromoneRate(speciesPheromone) + 1;
    int eastPheromone = (eastTile == null || this.fromTile.equals(eastTile)) ? 0
        : eastTile.getPheromoneRate(speciesPheromone) + 1;
    int southPheromone = (southTile == null || this.fromTile.equals(southTile)) ? 0
        : southTile.getPheromoneRate(speciesPheromone) + 1;
    int westPheromone = (westTile == null || this.fromTile.equals(westTile)) ? 0
        : westTile.getPheromoneRate(speciesPheromone) + 1;
    if(fromTile.equals(northTile) && southTile != null) {
      southPheromone += 10; //TODO Taux de non changement de direction
    }
    if(fromTile.equals(eastTile) && westTile != null) {
      westPheromone += 10; //TODO Taux de non changement de direction
    }
    if(fromTile.equals(southTile) && northTile != null) {
      northPheromone += 10; //TODO Taux de non changement de direction
    }
    if(fromTile.equals(westTile) && eastTile != null) {
      eastPheromone += 20; //TODO Taux de non changement de direction
    }
    if(this.fromTiles.contains(northTile)){
      northPheromone = 0;
    }
    if(this.fromTiles.contains(eastTile)){
      eastPheromone = 0;
    }
    if(this.fromTiles.contains(southTile)){
      southPheromone = 0;
    }
    if(this.fromTiles.contains(westTile)){
      westPheromone = 0;
    }
    int totalPheromone = northPheromone + eastPheromone + southPheromone + westPheromone;
    double northRate = northPheromone / (double) totalPheromone;
    double eastRate = eastPheromone / (double) totalPheromone;
    double southRate = southPheromone / (double) totalPheromone;
    double westRate = westPheromone / (double) totalPheromone;
    Random rand = new Random();
    float rng = rand.nextFloat();
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
    if(this.fromTiles.size() > 7) this.fromTiles.remove(0);
  }

}
