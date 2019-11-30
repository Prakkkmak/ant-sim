package view;

import java.awt.*;

import graphicLayer.GElement;
import graphicLayer.GOval;
import graphicLayer.GRect;

public class Square extends GRect{

  private GElement icon;
  private int tileSize;

  public Square(int x, int y, int tileSize){
    this.tileSize = tileSize;
    this.setPosition(new Point(x * tileSize, y * tileSize));
    this.setDimension(new Dimension(tileSize, tileSize));
    this.setColor(Color.white);
    this.setBorderColor(Color.lightGray);
    this.setBorderWidth(1);
  }

  public void addAnt() {
    removeAnt();
    /*if(icon == null) {
      this.icon = new GOval();
    }*/
    GOval ant =  new GOval();
    ant.setColor(Color.red);
    ant.setDimension(this.getDimension());
    this.icon = ant;
    this.addSubElement(ant);

  }
  public void removeAnt() {
    if(this.icon == null)return;
    this.removeSubElement(this.icon);
  }
}
