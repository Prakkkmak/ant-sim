package tests.model.data;

import model.abstracts.AntRole;
import model.abstracts.State;
import model.data.Species;
import model.developments.Egg;
import model.developments.Larva;
import model.factories.SpeciesFactory;
import model.roles.Soldier;
import model.roles.Worker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeciesTest {
    Species species;
    @BeforeEach
    void setUp() {
        this.species = SpeciesFactory.CreateDefaultSpecies();
    }

    @Test
    void testDefaultNameIsDefolaFourmila() {
        assertEquals("Defola Fourmila", this.species.getName());
    }

    @Test
    void testDefaultWeightIs1750() {

        assertEquals(1750, this.species.getWeight());
    }

    @Test
    void testDefaultTeritoryIs200() {
        assertEquals(200, this.species.getTerritory());
    }

    @Test
    void testDefaultStaminaIs11Hours() {
        assertEquals( 11 * 60, this.species.getStamina());
    }

    @Test
    void testGetSetGrowth() {
        State state = new Egg();
        assertEquals(3, this.species.getGrowth(state));
        State state2 = new Larva();
        this.species.setGrowth(state2, 40);
        assertEquals(40, this.species.getGrowth(state2));
    }

    @Test
    void testGetSetRatio() {
        AntRole role = new Worker();
        assertEquals(65, this.species.getRatio(role));
        role = new Soldier();
        this.species.setRatio(role, 100);
        assertEquals(100, this.species.getRatio(role));
    }

    @Test
    void testGetTotalRatio() {
        assertEquals(100, this.species.getTotalRatio());
    }

    @Test
    void testGetRealRatio() {
        AntRole role = new Worker();
        assertEquals(0.65, this.species.getRealRatio(role));
    }
}