package minesweeper.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Overlay {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Transparent Window");
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setSize(800, 600);
        frame.setAlwaysOnTop(true);
        // Without this, the window is draggable from any non transparent
        // point, including points  inside textboxes.
        frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);

        frame.getContentPane().setLayout(new java.awt.BorderLayout());
        frame.getContentPane().add(new JTextField("text field north"), java.awt.BorderLayout.NORTH);
        frame.getContentPane().add(new JTextField("text field south"), java.awt.BorderLayout.SOUTH);
//        frame.pack();
        frame.setVisible(true);
        
//        Window w=new Window(null)
//        {
//          @Override
//          public void paint(Graphics g)
//          {
//            final Font font = getFont().deriveFont(48f);
//            g.setFont(font);
//            g.setColor(Color.RED);
//            final String message = "Hello";
//            FontMetrics metrics = g.getFontMetrics();
//            g.drawString(message,
//              (getWidth()-metrics.stringWidth(message))/2,
//              (getHeight()-metrics.getHeight())/2);
//          }
//          @Override
//          public void update(Graphics g)
//          {
//            paint(g);
//          }
//        };
//        w.setAlwaysOnTop(true);
//        w.setBounds(w.getGraphicsConfiguration().getBounds());
//        w.setBackground(new Color(0, true));
//        w.setVisible(true);
    }
}
