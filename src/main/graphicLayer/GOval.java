package main.graphicLayer;

import java.awt.*;

public class GOval extends GBounded {

    public void draw(Graphics2D g) {
        Color c = g.getColor();
        Rectangle bounds = this.getBounds();
        g.setColor(color);
        g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setColor(c);
    }

}
