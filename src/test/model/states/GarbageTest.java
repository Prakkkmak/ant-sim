package test.model.states;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import main.model.abstracts.State;
import main.model.enums.EType;
import main.model.states.Egg;
import main.model.states.Garbage;
import main.model.states.Larva;
import main.model.states.Nymph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GarbageTest {

    private Garbage garbage;

    @BeforeEach
    void setUp(){
        this.garbage = new Garbage();
    }

    @Test
    void evolve() {
        State state = this.garbage.evolve();
        assertNull(state);
    }

    @Test
    void getType() {
        assertEquals(EType.GARBAGE, this.garbage.getType());
    }
}