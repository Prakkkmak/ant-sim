package main.model.states;

import main.model.abstracts.State;
import main.model.entities.Ant;
import main.model.enums.EType;

public class Larva extends State {

    @Override
    public State evolve() {
        return new Nymph();
    }

    /**
     * The larva eat this weight when she have low food.
     *
     * @param ant The ant who is larva.
     */
    @Override
    public void action(Ant ant) {
        int currentFood = ant.getFood();
        if (currentFood < ant.getSpecies().getFoodConsumption()) {
            ant.consume(ant.getSpecies().getWeight() * 3);
        }
        super.action(ant);
    }

    @Override
    public EType getType() {
        return EType.LARVA;
    }

}
