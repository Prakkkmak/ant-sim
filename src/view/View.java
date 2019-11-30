package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import graphicLayer.GBounded;
import graphicLayer.GOval;
import graphicLayer.GRect;
import graphicLayer.GSpace;
import world.World;

public class View implements IView {
  
  private World world;
  
  private Square[][] grid;
  private GBounded container;
  private GSpace gSpace;
  
  public View(World world) {
    this.world = world;
    this.gSpace = new GSpace("", new Dimension(1000,1000));
    this.container = new GBounded();
    this.container.setColor(Color.white);
    this.container.setDimension(new Dimension(1000,1000));
    this.createGrid();
    this.gSpace.addElement(this.container);
    this.gSpace.open();
  }
  
  public void createGrid() {
    int sizeX = this.world.getSizeX();
    int sizeY = this.world.getSizeY();
    this.grid = new Square[sizeX][sizeY];
    for(int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        this.grid[i][j] = new Square();
        this.grid[i][j].setPosition(new Point(i * this.getTileSize(), j * this.getTileSize()));
        this.grid[i][j].setDimension(new Dimension(this.getTileSize(), this.getTileSize()));
        this.grid[i][j].setColor(Color.white);
        this.grid[i][j].setBorderColor(Color.lightGray);
        this.grid[i][j].setBorderWidth(1);
        this.container.addSubElement(this.grid[i][j]);
      }
    }
  }
  
  public void updateGrid() {
    for(int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        int content = this.world.getTileContent(i, j);
        if(content == 0) {
          this.grid[i][j].removeAnt();
        }
        else if(content == 1) {
          System.out.println("Ant en " + i + " " + j);
         this.grid[i][j].addAnt();
        }
      }
    }
  }
  
  public int getTileSize() {
    return (int) this.container.getHeight() / this.world.getSizeX();
  }
  
  @Override
  public void update() {
    updateGrid();
    this.gSpace.repaint();
  }
}
