package tests.model.developments;

import model.abstracts.State;
import model.developments.Egg;
import model.developments.Larva;
import model.developments.Mature;
import model.developments.Nymph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvolutionTest {
    private State state;
    @BeforeEach
    void setUp(){
        state = new Egg();
    }

    @Test
    void testEvolveToMature() {
        assertTrue(state instanceof Egg);
        this.state = state.evolve();
        assertTrue(state instanceof Larva);
        this.state = state.evolve();
        assertTrue(state instanceof Nymph);
        this.state = state.evolve();
        assertTrue(state instanceof Mature);
    }

    @Test
    void testEquals() {
        State state1 = new Egg();
        State state2 = new Mature();
        State state3 = new Egg();
        assertNotEquals(state1, state2);
        assertEquals(state1, state3);
    }
}