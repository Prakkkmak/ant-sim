package main.model.roles;

import main.model.abstracts.AntRole;
import main.model.entities.Ant;
import main.model.enums.EType;

public class Soldier extends AntRole {

    @Override
    public void action(Ant ant) {
        ant.getTile().moveTo(ant, ant.getX() + 1, ant.getY());
    }

    @Override
    public EType getType() {
        return EType.WORKER;
    }


}
