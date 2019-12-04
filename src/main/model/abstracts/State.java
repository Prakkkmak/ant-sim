package main.model.abstracts;

import main.model.entities.Ant;
import main.model.enums.EType;

public abstract class State {

    public abstract State evolve();

    public AntRole getAntRole() {
        return null;
    }

    public void setAntRole(AntRole antRole) {
        return;
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == this.getClass();
    }

    public void action(Ant ant) {
        this.tryToEvolve(ant);
    }

    public abstract EType getType();

    public void tryToEvolve(Ant ant) {
        int antAge = ant.getAge().getMinutes();
        int ageToGrow = ant.getSpecies().getGrowth(this);
        if (antAge > ageToGrow) {
            ant.setState(evolve());
        }
    }

}
