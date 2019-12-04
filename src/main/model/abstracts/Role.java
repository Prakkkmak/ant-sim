package main.model.abstracts;

import main.model.interfaces.IEntityTile;

public abstract class Role {

    public void action(IEntityTile entity) {
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == this.getClass();
    }
}
