package view;

import java.awt.Dimension;

import graphicLayer.GSpace;
import model.world.World;

public class View implements IView {

  private Container container;
  private GSpace gSpace;
  private DataPanel dataPanel;
  public View(World world) {
    this.gSpace = new GSpace("", new Dimension(1200,1000));
    this.container = new Container(world);
    this.dataPanel = new DataPanel(world ,1000,0);
    this.gSpace.addElement(this.container);
    this.gSpace.addElement(this.dataPanel);
    this.gSpace.open();
  }

  
  @Override
  public void update() {
    this.container.updateGrid();
    this.dataPanel.updateInfos();
    this.gSpace.repaint();
  }
}
