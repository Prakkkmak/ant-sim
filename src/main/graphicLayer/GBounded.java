package main.graphicLayer;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GBounded extends GElement implements GContainer {

    protected Point position;
    protected Dimension dim;
    protected List<GElement> subElements = new ArrayList<GElement>();
    private List<MouseListener> mouseListeners = new LinkedList<MouseListener>();

    public GBounded() {
        this.position = new Point(0, 0);
        this.dim = new Dimension(20, 20);
    }

    public void addMouseListener(MouseListener k) {
        mouseListeners.add(k);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point p) {
        position = p;
        repaint();
    }

    public Point getGlobalPosition() {
        Point p = this.getPosition();
        if (container == null) {
            return p;
        }
        p.translate(container.getGlobalPosition().x, container.getGlobalPosition().y);
        return p;
    }

    public int getX() {
        return (getPosition().x);
    }

    public void setX(int x) {
        Point p = getPosition();
        setPosition(new Point(x, p.y));
    }

    public int getY() {
        return (getPosition().y);
    }

    public void setY(int y) {
        Point p = getPosition();
        setPosition(new Point(p.x, y));
    }

    public void moveRight(int gap) {
        setX(getX() + gap);
    }

    public void moveLeft(int gap) {
        setX(getX() - gap);
    }

    public void moveUp(int gap) {
        setY(getY() - gap);
    }

    public void moveDown(int gap) {
        setY(getY() + gap);
    }

    public void drawContents(Graphics2D g) {
        if (subElements.isEmpty()) {
            return;
        }
        Shape currentClip = g.getClipBounds();
        g.setClip(getX(), getY(), getWidth(), getHeight());
        g.translate(getX(), getY());
        Iterator<GElement> itor = subElements.iterator();
        GElement[] subElementsArray = new GElement[subElements.size()];
        subElements.toArray(subElementsArray);
        for (int i = 0; i < subElementsArray.length; i++) {
            if (subElementsArray[i] != null) {
                subElementsArray[i].draw(g);
            }
        }
		/*while (itor.hasNext()) {
			GElement m = itor.next();
			m.draw(g);
		}*/
        g.translate(-getX(), -getY());
        g.setClip(currentClip);
    }


    public void draw(Graphics2D g) {
        Rectangle bounds = this.getBounds();
        Color c = g.getColor();
        g.setColor(color);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        drawContents(g);
        g.setColor(c);
    }

    public Rectangle getBounds() {
        return new Rectangle(this.getPosition(), this.getDimension());
    }

    public Dimension getDimension() {
        return dim;
    }

    public void setDimension(Dimension dim) {
        this.dim = dim;
    }

    public Rectangle getGlobalBounds() {
        return (Rectangle) this.getBounds().clone();
    }

    public void addSubElement(GElement m) {
        if (m.getContainer() != null) {
            return;
        }
        m.setContainer(this);
        subElements.add(m);
    }

    public void removeSubElement(GElement m) {
        if (m.getContainer() == null) {
            return;
        }
        subElements.remove(m);
        m.setContainer(null);
    }

    public int getWidth() {
        return (int) dim.getWidth();
    }

    public void setWidth(Integer width) {
        this.setDimension(new Dimension(width, this.getHeight()));
    }

    public int getHeight() {
        return (int) dim.getHeight();
    }

    public void setHeight(Integer height) {
        this.setDimension(new Dimension(this.getWidth(), height));
    }

    public void dispatchMouseClicked(MouseEvent e) {
        if (!getBounds().contains(e.getPoint())) {
            return;
        }
        e.translatePoint(-getX(), -getY());
        Iterator<GElement> itor = subElements.iterator();
        while (itor.hasNext()) {
            GElement m = itor.next();
            m.dispatchMouseClicked(e);
        }
        if (!e.isConsumed()) {
            whenMouseClicked(e);
        }
        e.translatePoint(getX(), getY());
    }

    public void dispatchMousePressed(MouseEvent e) {
        if (!getBounds().contains(e.getPoint())) {
            return;
        }
        e.translatePoint(-getX(), -getY());
        Iterator<GElement> itor = subElements.iterator();
        while (itor.hasNext()) {
            GElement m = itor.next();
            m.dispatchMousePressed(e);
        }
        if (!e.isConsumed()) {
            whenMousePressed(e);
        }
        e.translatePoint(getX(), getY());
    }

    public void dispatchMouseReleased(MouseEvent e) {
        if (!getBounds().contains(e.getPoint())) {
            return;
        }
        e.translatePoint(-getX(), -getY());
        Iterator<GElement> itor = subElements.iterator();
        while (itor.hasNext()) {
            GElement m = itor.next();
            m.dispatchMouseReleased(e);
        }
        if (!e.isConsumed()) {
            whenMouseReleased(e);
        }
        e.translatePoint(getX(), getY());
    }

    public void dispatchMouseEntered(MouseEvent e) {
        if (!getBounds().contains(e.getPoint())) {
            return;
        }
        e.translatePoint(-getX(), -getY());
        Iterator<GElement> itor = subElements.iterator();
        while (itor.hasNext()) {
            GElement m = itor.next();
            m.dispatchMouseEntered(e);
        }
        if (!e.isConsumed()) {
            whenMouseEntered(e);
        }
        e.translatePoint(getX(), getY());
    }

    public void dispatchMouseExited(MouseEvent e) {
        if (!getBounds().contains(e.getPoint())) {
            return;
        }
        e.translatePoint(-getX(), -getY());
        Iterator<GElement> itor = subElements.iterator();
        while (itor.hasNext()) {
            GElement m = itor.next();
            m.dispatchMouseExited(e);
        }
        if (!e.isConsumed()) {
            whenMouseExited(e);
        }
        e.translatePoint(getX(), getY());
    }

    public void whenMouseClicked(MouseEvent e) {
        for (Iterator<MouseListener> iter = mouseListeners.iterator(); iter.hasNext(); ) {
            MouseListener mouseListener = iter.next();
            mouseListener.mouseClicked(e);
        }
    }

    public void whenMousePressed(MouseEvent e) {
        for (Iterator<MouseListener> iter = mouseListeners.iterator(); iter.hasNext(); ) {
            MouseListener mouseListener = iter.next();
            mouseListener.mousePressed(e);
        }
    }

    public void whenMouseReleased(MouseEvent e) {
        for (Iterator<MouseListener> iter = mouseListeners.iterator(); iter.hasNext(); ) {
            MouseListener mouseListener = iter.next();
            mouseListener.mouseReleased(e);
        }
    }

    public void whenMouseEntered(MouseEvent e) {
        for (Iterator<MouseListener> iter = mouseListeners.iterator(); iter.hasNext(); ) {
            MouseListener mouseListener = iter.next();
            mouseListener.mouseEntered(e);
        }
    }

    public void whenMouseExited(MouseEvent e) {
        for (Iterator<MouseListener> iter = mouseListeners.iterator(); iter.hasNext(); ) {
            MouseListener mouseListener = iter.next();
            mouseListener.mouseExited(e);
        }
    }


}
