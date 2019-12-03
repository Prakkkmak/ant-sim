package view;

import graphicLayer.GRect;
import graphicLayer.GString;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import model.enums.EType;
import model.world.World;

public class DataPanel extends GRect {

  private World world;
  private GString date;
  private GString antCount;
  private JTextArea jTextArea;

  public DataPanel(World world, int x, int y) {
    this.world = world;
    this.setPosition(new Point(x, y));
    this.setDimension(new Dimension(100, 300));
    this.setColor(Color.lightGray);
    this.setBorderColor(Color.black);
    this.setBorderWidth(2);
    JFrame frame = new JFrame("Stats");
    this.jTextArea = new JTextArea(10, 30);
    frame.add(this.jTextArea);
    frame.pack();
    frame.setVisible(true);
  }

  public void updateInfos() {
    Map<EType, Integer> infosOutside = this.world.getOutsides();
    Map<EType, Integer> infosInside = this.world.getInsides();
    String text = "";
    text += "-- Outside: \n";
    if(infosOutside.get(EType.WORKER) != null) text += "Workers :" + infosOutside.get(EType.WORKER) + "\n";
    text += "-- Inside: \n";
    if(infosInside.get(EType.EGG) != null) text += "EGG :" + infosInside.get(EType.EGG) + "\n";
    if(infosInside.get(EType.LARVA) != null) text += "LARVA :" + infosInside.get(EType.LARVA) + "\n";
    if(infosInside.get(EType.NYMPH) != null) text += "NYMPH :" + infosInside.get(EType.NYMPH) + "\n";
    if(infosInside.get(EType.WORKER) != null) text += "WORKER :" + infosInside.get(EType.WORKER) + "\n";
    jTextArea.setText(text);
    //jLabel.equals("Workers count = " + world.getAntCount());
    //this.date.setString(" Date : " + world.getDays() + " days");
    //this.antCount.setString(" Pop : " + world.getAntCount() + " ants");
  }
}
