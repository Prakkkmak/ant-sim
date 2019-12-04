package model.factories;

import model.data.Species;
import model.developments.Egg;
import model.developments.Larva;
import model.developments.Mature;
import model.developments.Nymph;
import model.roles.Fly;
import model.roles.Soldier;
import model.roles.Worker;

public class SpeciesFactory {

  public static Species CreateDefaultSpecies() {
    //TODO aléger constructeur
    Species species = new Species("Defola Fourmila", 4500, 200, 11 * 60);
    species.setGrowthDays(new Egg(), 5);
    species.setGrowthDays(new Larva(), 10);
    species.setGrowthDays(new Nymph(), 15);
    species.setGrowthDays(new Mature(), 365 * 2);
    species.setRatio(new Worker(), 65);
    species.setRatio(new Soldier(), 20);
    species.setRatio(new Fly(), 15);
    species.setPheromoneDropRate(1000);
    species.setRandomMovementRate(500);
    species.setLinearMovementRate(100);
    species.setNumberOfTimesWeightCarried(10);
    species.setNumberOfEggPerDay(10);
    return species;
  }
}
