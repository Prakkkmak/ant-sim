package test.model.developments;

import main.model.abstracts.State;
import main.model.data.AnthillInfo;
import main.model.developments.Egg;
import main.model.developments.Larva;
import main.model.enums.EType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EggTest {
    private Egg egg;
    @BeforeEach
    void setUp(){
        this.egg = new Egg();
    }
    @Test
    void evolve() {
        State state = this.egg.evolve();
        assertTrue(state instanceof Larva);
    }

    @Test
    void getType() {
        assertEquals(EType.EGG, this.egg.getType());
    }
}