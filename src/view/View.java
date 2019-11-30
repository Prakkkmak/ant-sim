package view;

import java.awt.Dimension;

import graphicLayer.GSpace;
import model.world.World;

public class View implements IView {

  private Container container;
  private GSpace gSpace;
  
  public View(World world) {
    this.gSpace = new GSpace("", new Dimension(1000,1000));
    this.container = new Container(world);
    this.container.createGrid();
    this.gSpace.addElement(this.container);
    this.gSpace.open();
  }

  
  @Override
  public void update() {
    this.container.updateGrid();
    this.gSpace.repaint();
  }
}
