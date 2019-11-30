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

    private int getTileSize() {
        return (int) this.getHeight() / this.world.getSizeX();
    }
}
