package main.graphicLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GSpace extends JPanel implements GContainer, KeyListener, MouseListener {

    private static final long serialVersionUID = 1L;
    String name = "";
    private List<GElement> elements = new LinkedList<GElement>();
    private List<KeyListener> keyListeners = new LinkedList<KeyListener>();

    public GSpace(String name, Dimension dim) {
        this.name = name;
        super.addKeyListener(this);
        super.addMouseListener(this);
        this.setPreferredSize(dim);
    }

    public List<GElement> contents() {
        return elements;
    }

    public void open() {
        JFrame frame = new JFrame(name);
        WindowAdapter wa = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(wa);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        requestFocus();
    }

    public void addElement(GElement d) {
        elements.add(d);
        d.setContainer(this);
    }

    public void removeElement(GBounded d) {
        elements.remove(d);
        d.setContainer(null);
    }


    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Iterator<GElement> iter = elements.iterator(); iter.hasNext(); ) {
            iter.next().draw(g2d);
        }
    }

    public void addKeyListener(KeyListener k) {
        keyListeners.add(k);
    }

    public void clear() {
        for (Iterator<GElement> iter = elements.iterator(); iter.hasNext(); ) {
            iter.next().setContainer(null);
        }
        elements.clear();
    }

    public void keyTyped(KeyEvent e) {
        for (Iterator<KeyListener> iter = keyListeners.iterator(); iter.hasNext(); ) {
            KeyListener keyListener = iter.next();
            keyListener.keyTyped(e);
        }
    }

    public void keyPressed(KeyEvent e) {
        for (Iterator<KeyListener> iter = keyListeners.iterator(); iter.hasNext(); ) {
            KeyListener keyListener = iter.next();
            keyListener.keyPressed(e);
        }
    }

    public void keyReleased(KeyEvent e) {
        for (Iterator<KeyListener> iter = keyListeners.iterator(); iter.hasNext(); ) {
            KeyListener keyListener = iter.next();
            keyListener.keyReleased(e);
        }
    }

    public void mouseClicked(MouseEvent e) {
        for (Iterator<GElement> iter = elements.iterator(); iter.hasNext(); ) {
            GElement element = iter.next();
            element.dispatchMouseClicked(e);
        }
    }

    public void mousePressed(MouseEvent e) {
        for (Iterator<GElement> iter = elements.iterator(); iter.hasNext(); ) {
            GElement element = iter.next();
            element.dispatchMousePressed(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (Iterator<GElement> iter = elements.iterator(); iter.hasNext(); ) {
            GElement element = iter.next();
            element.dispatchMouseReleased(e);
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public Point getGlobalPosition() {
        return null;
    }

}