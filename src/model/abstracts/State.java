package model.abstracts;

import model.entities.Ant;

public abstract class State {
  public abstract State evolve();
  public AntRole getAntRole() {
    return null;
  }
  public void setAntRole(AntRole antRole) {
    return;
  }

  @Override
  public boolean equals(Object o){
    return o.getClass() == this.getClass();
  }

  public void action(Ant ant) {
    this.tryToEvolve(ant);
  }

  public void tryToEvolve(Ant ant){
    int antAge = ant.getAge().getMinutes();
    int ageToGrow = ant.getSpecies().getGrowth(this);
    System.out.println(antAge + "/" + ageToGrow);
    if(antAge > ageToGrow){
      ant.setState(evolve());
      System.out.println("Ant evolve to " + ant.getState().getClass().getSimpleName());
    }
  }

}
