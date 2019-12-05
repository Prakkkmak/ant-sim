package main.model.states;

import main.model.abstracts.State;
import main.model.entities.Ant;
import main.model.enums.EType;

/**
 * Last state of an ant.
 */
public class Garbage extends State {

    /**
     * A garbage evolve to null. If th garbage don't have a reason to exists , you can evolve to null
     * for different ui used.
     * @return Returns null.
     */
    @Override
    public State evolve() {
        return this;
    }

    @Override
    public EType getType() {
        return EType.GARBAGE;
    }

}
