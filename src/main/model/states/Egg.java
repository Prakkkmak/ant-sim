package main.model.states;

import main.model.abstracts.State;
import main.model.enums.EType;

/**
 * An egg is the first state of the ant. It evolves to Larva.
 */
public class Egg extends State {

    /**
     * Evolve to larva.
     * @return A new Larva.
     */
    @Override
    public State evolve() {
        return new Larva();
    }

    @Override
    public EType getType() {
        return EType.EGG;
    }
}
