package model.roles;

import model.abstracts.AntRole;
import model.data.Species;
import model.entities.Ant;
import model.world.Pheromone;
import model.world.Tile;

import java.util.Random;

public class Worker extends AntRole {
  Tile fromTile;
  @Override
  public void action(Ant ant) {
    int rnd = (int) (Math.random() * 4); // 0 1 2 3
    Species species = ant.getSpecies();
    Pheromone speciesPheromone = species.getPheromone();
    Tile northTile = ant.getTile().getNorthTile();
    Tile eastTile = ant.getTile().getEastTile();
    Tile southTile = ant.getTile().getSouthTile();
    Tile westTile = ant.getTile().getWestTile();
    if(fromTile == null) fromTile = ant.getTile();
    int northPheromone = (northTile == null || this.fromTile.equals(northTile)) ? 0 : northTile.getPheromoneRate(speciesPheromone) + 100;
    int eastPheromone = (eastTile == null|| this.fromTile.equals(eastTile) ) ? 0 : eastTile.getPheromoneRate(speciesPheromone) + 100;
    int southPheromone = (southTile == null || this.fromTile.equals(southTile)) ? 0 : southTile.getPheromoneRate(speciesPheromone) + 100;
    int westPheromone =(westTile == null || this.fromTile.equals(westTile)) ? 0 : westTile.getPheromoneRate(speciesPheromone) + 100;
    int totalPheromone = northPheromone + eastPheromone + southPheromone + westPheromone;
    double northRate = northPheromone / (double)totalPheromone;
    double eastRate = eastPheromone / (double)totalPheromone;
    double southRate = southPheromone / (double)totalPheromone;
    double westRate = westPheromone /(double) totalPheromone;
    Random rand = new Random();
    float rng = rand.nextFloat();
    this.fromTile = ant.getTile();
    if(rng < northRate){
      ant.getTile().moveTo(ant, northTile);
    }
    else if(rng < eastRate + northRate){
      ant.getTile().moveTo(ant, eastTile);
    }
    else if(rng < southRate + eastRate + northRate ){
      ant.getTile().moveTo(ant, southTile);
    }
    else{
      ant.getTile().moveTo(ant, westTile);
    }
    ant.getTile().addPheromone(speciesPheromone, 600);
  }

}
