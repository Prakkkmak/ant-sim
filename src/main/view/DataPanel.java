package main.view;

import main.graphicLayer.GString;
import main.model.enums.EType;
import main.simulation.Simulation;

import javax.swing.*;
import java.util.Map;

public class DataPanel extends JPanel {
    private Simulation simulation;
    private GString date;
    private GString antCount;
    private JLabel textLabel;

    public DataPanel(Simulation simulation) {
        //this.main.simulation = main.simulation;
        //this.setRows(x);
        //this.setColumns(y);
        //this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        //this.setBounds(x,y, 100, 300);
        this.simulation = simulation;
        this.textLabel = new JLabel("<html>  test  </html>");
        this.add(this.textLabel);
    }

    public void update() {
        Map<EType, Integer> infoOutside = this.simulation.getWorld().getOutsides();
        Map<EType, Integer> infoInside = this.simulation.getWorld().getInsides();
        int infoFoodInside = this.simulation.getWorld().getFoodInsides();
        int infoFoodOutside = this.simulation.getWorld().getFoodOutsides();
        String text = "<html>-------- Ant Sim --------<br>";
        text += "<br>-- Total:<br><br>";
        int total = 0;
        for (Integer i : infoOutside.values()) {
            total += i;
        }
        for (Integer i : infoInside.values()) {
            total += i;
        }
        text += "Entities : " + total + "<br>";
        int workerTotal = 0;
        if (infoOutside.get(EType.WORKER) != null) workerTotal += infoOutside.get(EType.WORKER);
        if (infoInside.get(EType.WORKER) != null) workerTotal += infoInside.get(EType.WORKER);
        text += "Workers : " + workerTotal + "<br>";
        text += "<br>-- Outside: <br><br>";
        if (infoOutside.get(EType.WORKER) != null) text += "Workers :" + infoOutside.get(EType.WORKER) + "<br>";
        else text += "Workers : 0<br>";
        text += "Food :" + infoFoodOutside + "<br>";
        text += "<br>-- Inside: <br><br>";
        if (infoInside.get(EType.EGG) != null) text += "EGG :" + infoInside.get(EType.EGG) + "<br>";
        else text += "EGG : 0<br>";
        if (infoInside.get(EType.LARVA) != null) text += "LARVA :" + infoInside.get(EType.LARVA) + "<br>";
        else text += "LARVA : 0<br>";
        if (infoInside.get(EType.NYMPH) != null) text += "NYMPH :" + infoInside.get(EType.NYMPH) + "<br>";
        else text += "NYMPH : 0<br>";
        if (infoInside.get(EType.WORKER) != null) text += "WORKER :" + infoInside.get(EType.WORKER) + "<br>";
        else text += "WORKER : 0<br>";
        text += "Food :" + infoFoodInside + "<br>";
        this.textLabel.setText(text);
        //jLabel.equals("Workers count = " + world.getAntCount());
        //this.date.setString(" Date : " + world.getDays() + " days");
        //this.antCount.setString(" Pop : " + world.getAntCount() + " ants");
    }
}
