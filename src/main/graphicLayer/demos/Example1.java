package graphicLayer.demos;

import graphicLayer.GBounded;
import graphicLayer.GElement;
import graphicLayer.GOval;
import graphicLayer.GSpace;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Example1 {

    public static void main(String[] args) {
        Random x = new Random();
        GSpace w = new GSpace("Un essai de Morphs", new Dimension(800, 600));
        for (int i = 0; i < 2000; i++) {
            GOval oval = new GOval();
            oval.setColor(new Color((int) (Math.random() * 0x1000000)));
            oval.setDimension(new Dimension(x.nextInt(30), x.nextInt(30)));
            w.addElement(oval);
        }
        w.open();
        while (true) {
            int worldWidth = w.getWidth();
            int worldHeight = w.getHeight();
            List<GElement> drawables = w.contents();
            for (Iterator<GElement> iter = drawables.iterator(); iter.hasNext(); ) {
                GElement next = iter.next();
                Point nextPos = new Point(x.nextInt(worldWidth), x.nextInt(worldHeight));
                ((GBounded) next).setPosition(nextPos);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
