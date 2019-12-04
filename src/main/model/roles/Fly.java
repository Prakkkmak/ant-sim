package main.model.roles;

import main.model.abstracts.AntRole;
import main.model.entities.Ant;
import main.model.enums.EType;

public class Fly extends AntRole {

    @Override
    public void action(Ant ant) {
        // TODO Auto-generated method stub

    }

    @Override
    public EType getType() {
        return EType.SEXUAL;
    }

}
