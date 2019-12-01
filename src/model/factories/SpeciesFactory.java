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
    public static Species CreateDefaultSpecies(){
        Species species = new Species("Defola Fourmila", 1750, 200, 11 * 60);
        species.setGrowthDays(new Egg(), 1);
        species.setGrowthDays(new Larva(), 3);
        species.setGrowthDays(new Nymph(), 5);
        species.setGrowthDays(new Mature(), 100000);
        species.setRatio(new Worker(), 65);
        species.setRatio(new Soldier(), 20);
        species.setRatio(new Fly(), 15);
        return species;
    }
}
