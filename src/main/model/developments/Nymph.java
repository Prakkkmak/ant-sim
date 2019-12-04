package main.model.developments;

import main.model.abstracts.State;
import main.model.enums.EType;

public class Nymph extends State {

    @Override
    public State evolve() {
        return new Mature();
    }

    @Override
    public EType getType() {
        return EType.NYMPH;
    }

}
