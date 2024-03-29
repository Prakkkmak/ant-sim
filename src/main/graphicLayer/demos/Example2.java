package main.graphicLayer.demos;

import main.graphicLayer.GBounded;
import main.graphicLayer.GSpace;

import java.awt.*;

public class Example2 {

    public Example2() {
        GSpace w = new GSpace("Un essai", new Dimension(800, 600));
        GBounded container = new GBounded();
        container.setColor(Color.white);
        container.setDimension(new Dimension(400, 300));
        w.addElement(container);

        GBounded subContainer = new GBounded();
        subContainer.setColor(Color.red);
        subContainer.setPosition(new Point(10, 10));
        subContainer.setDimension(new Dimension(100, 50));
        container.addSubElement(subContainer);

        GBounded subsubContainer = new GBounded();
        subsubContainer.setColor(Color.black);
        subsubContainer.setPosition(new Point(10, 5));
        subContainer.setDimension(new Dimension(80, 30));
        subContainer.addSubElement(subsubContainer);

        w.open();
    }


    public static void main(String[] args) {
        new Example2();
    }

}
