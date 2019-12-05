package main.model.states;

import main.model.abstracts.State;
import main.model.entities.Ant;
import main.model.enums.EType;
/**
 * Larva is the second state of an ant. The larva consume good amount of food.
 */
public class Larva extends State {

    /**
     * Evolve to nymph.
     * @return A new Nymph state.
     */
    @Override
    public State evolve() {
        return new Nymph();
    }

    /**
     * The larva eat this weight * 6 ( *3 + weight *3) when she have low food.
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
