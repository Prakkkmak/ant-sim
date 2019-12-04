package main.model.developments;

import main.model.abstracts.State;
import main.model.enums.EType;

public class Egg extends State {

    @Override
    public State evolve() {
        return new Larva();
    }

    @Override
    public EType getType() {
        return EType.EGG;
    }
}
