package model.abstracts;

import model.interfaces.IEntityTile;

public abstract class Role {

  public void action(IEntityTile entity) {
  }

  @Override
  public boolean equals(Object o) {
    return o.getClass() == this.getClass();
  }
}
