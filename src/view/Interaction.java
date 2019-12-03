package view;

import javax.swing.*;
import java.awt.*;
import java.awt.Container;

public class Interaction extends JPanel{
    public Interaction(BorderLayout layout, int x, int y){
        super(layout);
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
        this.setBounds(x,y, 300, 200);
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 120, 1);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBounds(0,0, 200, 100);
        JPanel createColonyButtonPannel = new JPanel();
        createColonyButtonPannel.setBounds(200,0, 10, 20);
        JButton createColonyButton = new JButton();
        createColonyButton.setText("Colonie");
        createColonyButtonPannel.add(createColonyButton);
        this.add(slider);
        this.add(createColonyButtonPannel);
    }
}
