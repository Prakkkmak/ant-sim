package main.model.states;

import main.model.abstracts.State;
import main.model.entities.Ant;
import main.model.enums.EType;

public class Garbage extends State {

    public Garbage() {

    }

    @Override
    public State evolve() {
        return null;
    }

    @Override
    public void action(Ant ant) {

    }

    @Override
    public EType getType() {
        return EType.GARBAGE;
    }

}
