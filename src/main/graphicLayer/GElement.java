package main.graphicLayer;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class GElement {

    protected GContainer container;
    protected Color color = defaultColor();

    public GElement() {
    }

    abstract public void draw(Graphics2D g);

    public Color defaultColor() {
        return Color.blue;
    }

    public GContainer getContainer() {
        return container;
    }

    public void setContainer(GContainer aContainer) {
        container = aContainer;
    }

    public void repaint() {
        if (container == null) {
            return;
        }
        container.repaint();
    }

    public void setColor(Color c) {
        color = c;
        repaint();
    }

    public void dispatchMouseClicked(MouseEvent e) {
    }

    public void dispatchMousePressed(MouseEvent e) {
    }

    public void dispatchMouseReleased(MouseEvent e) {
    }

    public void dispatchMouseEntered(MouseEvent e) {
    }

    public void dispatchMouseExited(MouseEvent e) {
    }

}
