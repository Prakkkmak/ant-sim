package tests.model;

import model.abstracts.State;
import model.data.Species;
import model.developments.Egg;
import model.developments.Larva;
import model.developments.Mature;
import model.entities.Ant;
import model.factories.AntFactory;
import model.factories.SpeciesFactory;
import model.roles.Soldier;
import model.roles.Worker;
import model.world.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AntTest {
    private Ant ant;
    private Ant ant2;
    @BeforeEach
    void setUp() {
        AntFactory antFactory = new AntFactory(SpeciesFactory.CreateDefaultSpecies());
        this.ant = antFactory.createAnt(new Egg());
        this.ant2 = antFactory.createAnt(new Mature(), new Worker());
    }

    @Test
    void testGetSetState() {
        assertEquals(new Egg(), this.ant.getState());
        assertEquals(new Mature(), this.ant2.getState());
        this.ant.setState(new Larva());
        assertEquals(new Larva(), this.ant.getState());
    }

    @Test
    void testGetSetRole() {
      
        assertNull(this.ant.getRole());
        assertEquals(new Worker(), this.ant2.getRole());
        this.ant2.setRole(new Soldier());
        assertEquals(new Soldier(), this.ant2.getRole());
    }

    @Test
    void testGetSpeciesNotNull() {
        assertNotNull(this.ant.getSpecies());
    }

    @Test
    void testEvolveWithTime() {
        int tick = 0;
        Species species = this.ant.getSpecies();
        State state = this.ant.getState();
        int growTime =  species.getGrowth(state);
        for(int i = 0; i < growTime + 10; i++){
            this.ant.getAge().onMinutePass(i);
            this.ant.update();
        }
        assertEquals(new Larva(), this.ant.getState());
    }
}