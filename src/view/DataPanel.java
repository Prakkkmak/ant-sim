package view;

import graphicLayer.GRect;
import graphicLayer.GSpace;
import graphicLayer.GString;
import model.world.World;

import java.awt.*;

public class DataPanel extends GRect {
    private World world;
    private GString date;
    private GString antCount;
    public DataPanel(World world, int x, int y){
        this.world = world;
        this.setPosition(new Point(x, y));
        this.setDimension(new Dimension(100, 300));
        this.setColor(Color.lightGray);
        this.setBorderColor(Color.black);
        this.setBorderWidth(2);
        this.date = new GString();
        this.date.setDimension(new Dimension(100, 10));
        this.date.setColor(Color.black);
        this.date.setString("Date : 0");
        this.addSubElement(this.date);
        this.antCount = new GString();
        this.antCount.setDimension(new Dimension(100, 10));
        this.antCount.setY(10);
        this.antCount.setColor(Color.black);
        this.antCount.setString("Pop : 0");
        this.addSubElement(this.antCount);
    }
    public void updateInfos(){
        this.date.setString(" Date : " + world.getDays() + " days");
        this.antCount.setString(" Pop : " + world.getAntCount() + " ants");
    }
}
