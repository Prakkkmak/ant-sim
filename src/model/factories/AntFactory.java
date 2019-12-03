package model.factories;

import model.abstracts.AntRole;
import model.abstracts.State;
import model.data.Species;
import model.developments.Mature;
import model.entities.Ant;
import model.entities.Anthill;
import model.enums.EType;
import model.interfaces.IEntityTile;
import model.roles.Worker;
import model.world.Tile;

/**
 * And factory is here to create an ant at specific tile.
 */
public class AntFactory {

  private Species species;
  private Anthill anthill;

  public AntFactory(Species species) {
    this.species = species;
  }

  /**
   * Create an ant at specific tile and state and role.
   *
   * @param tile    The tile where the ant is created.
   * @param state   The state of the ant.
   * @param antRole The role of the ant. Useless if the state is not Mature.
   * @return The ant newly created.
   */
  public Ant createAnt(Tile tile, State state, AntRole antRole) {
    Ant ant = new Ant(tile, this.species);
    ant.setState(state);
    ant.setRole(antRole);
    if (this.anthill != null) {
      anthill.addAnt(ant);
    }
    return ant;
  }

  /**
   * Create an ant at specific tile and state. If state is mature, the ant is a worker.
   *
   * @param tile  The tile where the ant is created.
   * @param state The state of the ant.
   * @return The ant newly created.
   */
  public Ant createAnt(Tile tile, State state) {
    Ant ant = createAnt(tile, state, new Worker());
    return ant;
  }

  /**
   * Create an ant at specific tile and role. The ant is mature.
   *
   * @param tile    The tile where the ant is created.
   * @param antRole The role of the ant.
   * @return The ant newly created.
   */
  public Ant createAnt(Tile tile, AntRole antRole) {
    Ant ant = createAnt(tile, new Mature(), antRole);
    return ant;
  }

  /**
   * Create an ant at specific tile. The ant is a mature worker.
   *
   * @param tile The tile where the ant is created.
   * @return The ant newly created.
   */
  public Ant createAnt(Tile tile) {
    Ant ant = createAnt(tile, new Mature(), new Worker());
    return ant;
  }

  /**
   * Create an ant by state and role.
   *
   * @param state   The state of the ant.
   * @param antRole The role of the ant. Useless if the state is not Mature.
   * @return The ant newly created.
   */
  public Ant createAnt(State state, AntRole antRole) {
    Ant ant = createAnt(null, state, antRole);
    return ant;
  }

  /**
   * Create an ant at specific state. If state is mature, the ant is a worker.
   *
   * @param state The state of the ant.
   * @return The ant newly created.
   */
  public Ant createAnt(State state) {
    Ant ant = createAnt(null, state, new Worker());
    return ant;
  }

  /**
   * Create an ant at  role. The ant is mature.
   *
   * @param antRole The role of the ant.
   * @return The ant newly created.
   */
  public Ant createAnt(AntRole antRole) {
    Ant ant = createAnt(null, new Mature(), antRole);
    return ant;
  }

  /**
   * Create an ant. The ant is a mature worker.
   *
   * @return The ant newly created.
   */
  public Ant createAnt() {
    Ant ant = createAnt(null, new Mature(), new Worker());
    return ant;
  }

  private Ant getQueen(Tile tile) {
    for (IEntityTile entity : tile.getEntities()) {
      if (entity.getType() == EType.ANTHILL) {
        return (Ant) entity;
      }
    }
    return null;
  }

  public Anthill createAnthill(Tile tile) {
    Anthill anthill = new Anthill(tile, species);
    return anthill;
  }

  public Anthill getAnthill() {
    return anthill;
  }

  public void setAnthill(Anthill anthill) {
    this.anthill = anthill;
  }
}
