package main.view;

import main.graphicLayer.GSpace;
import main.simulation.Simulation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlobalFrame extends JFrame {
    private Grid grid;
    private DataPanel dataPanel;

    public GlobalFrame(Simulation simulation) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);

        //Ant grid
        GSpace gSpace = new GSpace("", new Dimension(700, 700));
        this.grid = new Grid(simulation);
        gSpace.addElement(grid);

        //data
        this.dataPanel = new DataPanel(simulation);

        //Creating buttons
        JPanel createColonyPanel = new JPanel(); // the panel is not visible in output
        JButton send = new JButton("Créer colonie");
        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulation.init();
            }
        });
        JLabel text = new JLabel("Refresh Rate : ");
        JSlider slider = new JSlider(0, 30, 1);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                simulation.setMinutesPerTick(slider.getValue());
            }
        });
        JLabel text2 = new JLabel("Pheromone : ");
        JSlider sliderPheromone = new JSlider(JSlider.HORIZONTAL, 0, 5, 1);
        sliderPheromone.setMajorTickSpacing(1);
        sliderPheromone.setPaintTicks(true);
        sliderPheromone.setPaintLabels(true);
        sliderPheromone.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                grid.setPheromoneToShow(sliderPheromone.getValue());
            }
        });
        createColonyPanel.add(send);
        createColonyPanel.add(reset);
        createColonyPanel.add(text);
        createColonyPanel.add(slider);
        createColonyPanel.add(text2);
        createColonyPanel.add(sliderPheromone);

        this.add(createColonyPanel, BorderLayout.SOUTH);
        this.add(this.dataPanel);
        this.add(gSpace, BorderLayout.WEST);
        this.setVisible(true);
    }

    public void update() {
        this.grid.update();
        this.dataPanel.update();
    }
}
