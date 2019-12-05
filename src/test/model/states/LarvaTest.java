package test.model.states;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import main.model.abstracts.State;
import main.model.entities.Ant;
import main.model.enums.EType;
import main.model.states.Garbage;
import main.model.states.Larva;
import main.model.states.Mature;
import main.model.states.Nymph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LarvaTest {

    private Larva larva;

    @BeforeEach
    void setUp(){
        this.larva = new Larva();
    }

    @Test
    void evolve() {
        State state = this.larva.evolve();
        assertTrue(state instanceof Nymph);
    }

    @Test
    void getType() {
        assertEquals(EType.LARVA, this.larva.getType());
    }
}