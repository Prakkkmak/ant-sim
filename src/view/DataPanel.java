package view;

import graphicLayer.GRect;
import graphicLayer.GString;
import model.enums.EType;
import model.world.World;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class DataPanel2 extends JTextArea {

  private World world;
  private GString date;
  private GString antCount;
  private JTextArea jTextArea;

  public DataPanel2(World world, int x, int y) {
    this.world = world;
    this.setRows(x);
    this.setColumns(y);
    this.setBounds(x,y, 100, 300);
  }

  public void updateInfos() {
    Map<EType, Integer> infoOutside = this.world.getOutsides();
    Map<EType, Integer> infoInside = this.world.getInsides();
    String text = "";
    text += "-- Total: \n";
    int total = 0;
    for (Integer i : infoOutside.values()){
      total += i;
    }
    for (Integer i : infoInside.values()){
      total += i;
    }
    text += "Entities : " + total + "\n";
    text += "-- Outside: \n";
    if(infoOutside.get(EType.WORKER) != null) text += "Workers :" + infoOutside.get(EType.WORKER) + "\n";
    else text += "Workers : 0\n";
    text += "-- Inside: \n";
    if(infoInside.get(EType.EGG) != null) text += "EGG :" + infoInside.get(EType.EGG) + "\n";
    else text += "EGG : 0\n";
    if(infoInside.get(EType.LARVA) != null) text += "LARVA :" + infoInside.get(EType.LARVA) + "\n";
    else text += "LARVA : 0\n";
    if(infoInside.get(EType.NYMPH) != null) text += "NYMPH :" + infoInside.get(EType.NYMPH) + "\n";
    else text += "NYMPH : 0\n";
    if(infoInside.get(EType.WORKER) != null) text += "WORKER :" + infoInside.get(EType.WORKER) + "\n";
    else text += "WORKER : 0\n";
    this.setText(text);
    //jLabel.equals("Workers count = " + world.getAntCount());
    //this.date.setString(" Date : " + world.getDays() + " days");
    //this.antCount.setString(" Pop : " + world.getAntCount() + " ants");
  }
}
