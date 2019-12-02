package view;

import graphicLayer.GBounded;
import model.world.World;

import java.awt.*;

public class Container extends GBounded {
    private Square[][] grid;
    private World world;

    public Container(World world){
        this.world = world;
        this.setColor(Color.white);
        this.setDimension(new Dimension(1000,1000));
        this.createGrid();
    }

    public void createGrid() {
        int sizeX = this.world.getSizeX();
        int sizeY = this.world.getSizeY();
        this.grid = new Square[sizeX][sizeY];
        for(int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                this.grid[i][j] = new Square(i,j,this.getTileSize());
                this.addSubElement(this.grid[i][j]);
            }
        }
    }

    public void updateGrid() {
        for(int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                int pheromoneRate = this.world.getPheromoneConcentration(i,j);
                pheromoneRate = (int) pheromoneRate;
                if(pheromoneRate < 0) pheromoneRate = 0;
                if(pheromoneRate > 254) pheromoneRate = 254;
                Color c = new Color(254 - pheromoneRate, 254, 254 - pheromoneRate);
                this.grid[i][j].setColor(c);
                int content = this.world.getTileContent(i, j);
                boolean marked = this.world.getMarked(i,j);
                if(marked){
                    this.grid[i][j].addMarked();
                }
                else if(content == 0) {
                    this.grid[i][j].removeIcon();
                }
                else if(content == 1) {
                    this.grid[i][j].addAnt();
                }
                else if(content == 9){
                    this.grid[i][j].addQueen();
                }
            }
        }
    }

    private int getTileSize() {
        return (int) this.getHeight() / this.world.getSizeX();
    }
}
