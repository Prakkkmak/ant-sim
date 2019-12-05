package main.model.states;

import main.model.abstracts.AntRole;
import main.model.abstracts.State;
import main.model.entities.Ant;
import main.model.enums.EType;
import main.model.roles.Worker;

/**
 * Most of ants are mature. It's the main state where an ant can be worker..
 */
public class Mature extends State {

    /**
     * The role of the ant. Only mature ants can have a role.
     */
    private AntRole antRole;

    public Mature() {
        //TODO rng
        this.antRole = new Worker();
    }

    /**
     * Evolve to garbage.
     * @return The new Garbage.
     */
    @Override
    public State evolve() {
        return new Garbage();
    }

    @Override
    public AntRole getAntRole() {
        return this.antRole;
    }

    @Override
    public void setAntRole(AntRole antRole) {
        if (antRole == null) {
            antRole = new Worker();
        }
        this.antRole = antRole;
    }

    @Override
    public void action(Ant ant) {
        if (ant.getTile().equals(ant.getHome())) {
            int currentFood = ant.getFood();
            if (currentFood < ant.getSpecies().getFoodConsumption() / 4) {
                ant.consume(ant.getSpecies().getFoodConsumption());
            }
        }
        if(antRole != null) this.antRole.action(ant);
    }

    @Override
    public EType getType() {
        return antRole.getType();
    }
}
