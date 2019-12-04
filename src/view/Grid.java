package view;

import graphicLayer.GBounded;
import graphicLayer.GSpace;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.io.IOException;
import model.world.World;
import simulation.Simulation;

public class Grid extends GBounded {

  private Square[][] grid;
  private Simulation simulation;
  private int pheromoneToShow;

  public Grid(Simulation simulation) {
    this.simulation = simulation;
    this.setColor(Color.white);
    this.setDimension(new Dimension(500, 500));
    this.createGrid();
  }

  public void createGrid() {
    int sizeX = this.simulation.getWorld().getSizeX();
    int sizeY = this.simulation.getWorld().getSizeY();
    this.grid = new Square[sizeX][sizeY];
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        this.grid[i][j] = new Square(i, j, this.getTileSize());
        this.addSubElement(this.grid[i][j]);
      }
    }
  }
  private Color pheromoneColor(int pheromoneRate){
    pheromoneRate /= 200;
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
  public void update() {
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        int pheromoneRate = this.simulation.getWorld().getPheromoneConcentration(i, j, this.getPheromoneToShow());
        int foodRate = this.simulation.getWorld().getFoodConcentration(i, j);
        Color c;
        if(foodRate > 0){
          c = this.foodColor(foodRate);
        }
        else {
          c = this.pheromoneColor(pheromoneRate);
        }
        this.grid[i][j].setColor(c);
        int content = this.simulation.getWorld().getTileContent(i, j);
        boolean marked = this.simulation.getWorld().getMarked(i, j);
        if (marked && content == 4) {
          //this.grid[i][j].addMarked();
        } else if (content == 0) {
          this.grid[i][j].removeIcon();
        } else if (content == 4) {
          this.grid[i][j].addAnt();
        } else if (content == 8){
          this.grid[i][j].addPrey();
        } else if(content == 9){
          this.grid[i][j].addGarbage();
        } else if (content == 10) {
          this.grid[i][j].addQueen();
        }
      }
    }
  }

  public int getPheromoneToShow() {
    return pheromoneToShow;
  }

  public void setPheromoneToShow(int pheromoneToShow) {
    this.pheromoneToShow = pheromoneToShow;
  }

  private int getTileSize() {
    return this.getHeight() / this.simulation.getWorld().getSizeX();
  }
}
