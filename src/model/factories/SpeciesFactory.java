package model.factories;

import model.data.Species;
import model.developments.Egg;
import model.developments.Larva;
import model.developments.Mature;
import model.developments.Nymph;

public class SpeciesFactory {
    public static Species CreateDefaultSpecies(){
        Species species = new Species("Defola Fourmila", 1750, 200, 11 * 60);
        species.setGrowth(new Egg(), 3);
        species.setGrowth(new Larva(), 13);
        species.setGrowth(new Nymph(), 30);
        species.setGrowth(new Mature(), 600);
        return species;
    }
}
