package view;

import java.awt.Color;
import graphicLayer.GOval;
import graphicLayer.GRect;

public class Square extends GRect{
  private GOval ant;
  public void addAnt() {
    if(ant == null) {
      this.ant = new GOval();
    }
    this.ant.setColor(Color.red);
    this.addSubElement(ant);
  }
  public void removeAnt() {
    if(this.ant == null)return;
    this.removeSubElement(this.ant);
  }
}
