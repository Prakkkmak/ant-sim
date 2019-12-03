package view;

import graphicLayer.GBounded;
import graphicLayer.GSpace;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.io.IOException;
import model.world.World;

public class Container extends GBounded {

  private Square[][] grid;
  private World world;

  public Container(World world, GSpace gSpace) {
    this.world = world;
    this.setColor(Color.white);
    this.setDimension(new Dimension(500, 500));
    this.createGrid();
  }

  public void createGrid() {
    int sizeX = this.world.getSizeX();
    int sizeY = this.world.getSizeY();
    this.grid = new Square[sizeX][sizeY];
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        this.grid[i][j] = new Square(i, j, this.getTileSize());
        this.addSubElement(this.grid[i][j]);
      }
    }
  }
  private Color pheromoneColor(int pheromoneRate){
    pheromoneRate /= 4;
    if (pheromoneRate < 0) {
      pheromoneRate = 0;
    }
    if (pheromoneRate > 255) {
      pheromoneRate = 255;
    }
    return new Color(255 , 255, 255 - pheromoneRate);
  }
  private Color foodColor(int foodRate){
    foodRate /= 3920;
    if (foodRate < 0) {
      foodRate = 0;
    }
    if (foodRate > 255) {
      foodRate = 255;
    }
    return new Color(255 - foodRate, 255 , 255 - foodRate);
  }
  public void updateGrid() {
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        int pheromoneRate = this.world.getPheromoneConcentration(i, j);
        int foodRate = this.world.getFoodConcentration(i, j);
        Color c;
        if(foodRate > 0){
          c = this.foodColor(foodRate);
        }
        else {
          c = this.pheromoneColor(pheromoneRate);
        }
        this.grid[i][j].setColor(c);
        int content = this.world.getTileContent(i, j);
        boolean marked = this.world.getMarked(i, j);
        if (marked && content == 4) {
          //this.grid[i][j].addMarked();
        } else if (content == 0) {
          this.grid[i][j].removeIcon();
        } else if (content == 4) {
          this.grid[i][j].addAnt();
        } else if(content == 9){
          this.grid[i][j].addGarbage();
        } else if (content == 10) {
          this.grid[i][j].addQueen();
        }
      }
    }
  }

  private int getTileSize() {
    return this.getHeight() / this.world.getSizeX();
  }
}
