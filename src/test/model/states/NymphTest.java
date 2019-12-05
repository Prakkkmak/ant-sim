package test.model.states;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import main.model.abstracts.State;
import main.model.enums.EType;
import main.model.states.Larva;
import main.model.states.Mature;
import main.model.states.Nymph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NymphTest {

    private Nymph nymph;

    @BeforeEach
    void setUp(){
        this.nymph = new Nymph();
    }

    @Test
    void evolve() {
        State state = this.nymph.evolve();
        assertTrue(state instanceof Mature);
    }

    @Test
    void getType() {
        assertEquals(EType.NYMPH, this.nymph.getType());
    }
}