package view;

import graphicLayer.GSpace;
import java.awt.Dimension;
import model.world.World;

public class View implements IView {

  private Container container;
  private GSpace gSpace;
  private DataPanel dataPanel;

  public View(World world) {
    this.gSpace = new GSpace("", new Dimension(700, 500));
    this.container = new Container(world, this.gSpace);
    this.dataPanel = new DataPanel(world, 500, 0);
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
