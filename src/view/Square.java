package view;

import graphicLayer.GBounded;
import graphicLayer.GElement;
import graphicLayer.GOval;
import graphicLayer.GRect;
import graphicLayer.GSpace;
import graphicLayer.GString;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

public class Square extends GBounded {

  private GElement icon;
  private int tileSize;

  public Square(int x, int y, int tileSize) {
    this.tileSize = tileSize;
    this.setPosition(new Point(x * tileSize, y * tileSize));
    this.setDimension(new Dimension(this.tileSize, this.tileSize));
    this.setColor(Color.white);
    //this.setBorderColor(Color.white);
    //this.setBorderWidth(1);
  }

  public void addAnt() {
    removeIcon();
    GOval ant = new GOval();
    ant.setColor(Color.magenta);
    ant.setDimension(this.getDimension());
    this.icon = ant;
    this.addSubElement(ant);
  }

  public void addQueen() {
    removeIcon();
    GRect queen = new GRect();
    queen.setColor(Color.black);
    queen.setDimension(this.getDimension());
    this.icon = queen;
    this.addSubElement(queen);
  }

  public void addMarked() {
    removeIcon();
    GOval marked = new GOval();
    marked.setColor(Color.blue);
    marked.setDimension(this.getDimension());
    this.icon = marked;
    this.addSubElement(marked);
  }

  public void addGarbage() {
    removeIcon();
    GRect icon = new GRect();
    icon.setColor(Color.green);
    icon.setBorderColor(Color.BLACK);
    icon.setBorderWidth(4);
    icon.setDimension(this.getDimension());
    this.icon = icon;
    this.addSubElement(icon);
  }

  public void removeIcon() {
    if (this.icon == null) {
      return;
    }
    this.removeSubElement(this.icon);
  }
}
