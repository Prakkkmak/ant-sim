package main.graphicLayer.demos;

import main.graphicLayer.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Example3 implements MouseListener {

    public Example3() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void main(String[] args) {
        new Example3();
    }

    public void createAndShowGUI() {
        GSpace w = new GSpace("Un essai de Morphs", new Dimension(800, 600));
        GRect container = new GRect();
        container.withoutBorder();
        container.setColor(Color.white);
        container.setPosition(new Point(30, 30));
        container.setDimension(new Dimension(400, 300));
        w.addElement(container);

        GOval cercle = new GOval();
        cercle.setPosition(new Point(30, 100));
        cercle.setDimension(new Dimension(60, 60));
        cercle.setColor(Color.yellow);
        container.addSubElement(cercle);

        GRect subContainer = new GRect();
        subContainer.setColor(Color.red);
        subContainer.setPosition(new Point(50, 50));
        subContainer.setDimension(new Dimension(200, 40));
        subContainer.setBorderWidth(2);
        container.addSubElement(subContainer);

        subContainer.addMouseListener(this);

        GString s = new GString();
        s.setPosition(new Point(10, 0));
        s.setString("HELLO, Click here...");
        s.setFont(new Font("Arial", Font.BOLD, 18));
        s.setColor(Color.white);
        subContainer.addSubElement(s);

        GLine line = new GLine(new Point(10, 30), new Point(150, 30));
        line.setColor(Color.white);
        line.setWidth(3);
        subContainer.addSubElement(line);

        GPolyLine pline = new GPolyLine();
        pline.setWidth(8);
        pline.addPoint(new Point(5, 140));
        pline.addPoint(new Point(50, 180));
        pline.addPoint(new Point(70, 130));
        pline.addPoint(new Point(95, 180));
        pline.addPoint(new Point(150, 130));
        pline.addPoint(new Point(170, 180));
        container.addSubElement(pline);

        w.open();
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("Click " + e);
        e.consume();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("Entered " + e);
        e.consume();
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("Exited " + e);
        e.consume();
    }

}
