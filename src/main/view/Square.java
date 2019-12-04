package main.view;

import main.graphicLayer.GBounded;
import main.graphicLayer.GElement;
import main.graphicLayer.GOval;
import main.graphicLayer.GRect;

import java.awt.*;

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
        icon.setBorderWidth(1);
        icon.setDimension(this.getDimension());
        this.icon = icon;
        this.addSubElement(icon);
    }

    public void addPrey() {
        removeIcon();
        GRect icon = new GRect();
        icon.setColor(Color.CYAN);
        icon.setBorderColor(Color.RED);
        icon.setBorderWidth(2);
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
