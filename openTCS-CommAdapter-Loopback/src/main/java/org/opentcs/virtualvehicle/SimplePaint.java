package org.opentcs.virtualvehicle;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.List;
import javax.swing.*;

public class SimplePaint extends JPanel implements MouseListener, MouseMotionListener {

    private final static int BLACK = 0;

    private int currentColor = BLACK;  // The currently selected drawing color,
                                       //   coded as one of the above constants.
    private int prevX, prevY;     // The previous location of the mouse.

    private boolean dragging;      // This is set to true while the user is drawing.

    public Graphics graphicsForDrawing;  // A graphics context for the panel
                                          // that is used to draw the user's curve.
    public Client client;


    SimplePaint() {
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        //setUpDrawingGraphics();
    }

    SimplePaint(Client c) {
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        client = c;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);  // Fill with background color (white).

    } // end paintComponent()

    public void setUpDrawingGraphics() {
        graphicsForDrawing = getGraphics();
            graphicsForDrawing.setColor(Color.BLACK);
    } // end setUpDrawingGraphics()
    
    public void mousePressed(MouseEvent evt) {

        int x = evt.getX();   // x-coordinate where the user clicked.
        int y = evt.getY();   // y-coordinate where the user clicked.

        int width = getWidth();    // Width of the panel.
        int height = getHeight();  // Height of the panel.

        if (dragging == true)  // Ignore mouse presses that occur
            return;            //    when user is already drawing a curve.
                               //    (This can happen if the user presses
                               //    two mouse buttons at the same time.)

            prevX = x;
            prevY = y;
            dragging = true;
            setUpDrawingGraphics();
    } // end mousePressed()


    public void mouseReleased(MouseEvent evt) {
        if (dragging == false)
            return;  // Nothing to do because the user isn't drawing.
        dragging = false;
        graphicsForDrawing.dispose();
        graphicsForDrawing = null;
    }


    public void mouseDragged(MouseEvent evt) {

        if (dragging == false)
            return;  // Nothing to do because the user isn't drawing.

        int x = evt.getX();   // x-coordinate of mouse.
        int y = evt.getY();   // y-coordinate of mouse.

        graphicsForDrawing.drawLine(prevX, prevY, x, y);  // Draw the line.
        
        try{
                ChatMessage2 cm2 = new ChatMessage2();
                cm2.SetCoordinates(x, y, prevX, prevY);
                cm2.SetDrawing(true);
		client.myOutputStream.reset();
		client.myOutputStream.writeObject(cm2);
	}catch(IOException ioe){
		System.out.println(ioe.getMessage());
	}

        prevX = x;  // Get ready for the next line segment in the curve.
        prevY = y;

    } // end mouseDragged()

    public void mouseEntered(MouseEvent evt) { }   // Some empty routines.
    public void mouseExited(MouseEvent evt) { }    //    (Required by the MouseListener
    public void mouseClicked(MouseEvent evt) { }   //    and MouseMotionListener
    public void mouseMoved(MouseEvent evt) { }     //    interfaces).
} // end class SimplePaint
