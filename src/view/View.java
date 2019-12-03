package view;

import graphicLayer.GSpace;

import java.awt.*;

import model.world.World;

public class View implements IView {

  private Grid grid;
  private GSpace gSpace;
  private DataPanel dataPanel;
  private Interaction interaction;

  public View(World world) {
    this.gSpace = new GSpace("", new Dimension(700, 700));
    //this.grid = new Grid(world);
    //this.dataPanel = new DataPanel(world, 500, 0);
    //this.interaction = new Interaction(new BorderLayout(), 0, 500);
    this.gSpace.addElement(this.grid);
    this.gSpace.setLayout(null);
    this.gSpace.add(this.dataPanel);
    this.gSpace.add(this.interaction);
    this.gSpace.open();
  }


  @Override
  public void update() {
    this.grid.update();
    this.dataPanel.update();
    this.gSpace.repaint();
  }
}
