package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String TITLE = "My first java graphical interface";
    private static final int PROPORTION = 3;

    private final Controller controller = new Controller();
    private final JFrame frame = new JFrame();

    /**
     * Create a simple GUI for saving a string into a file.
     * @param title of window
     */
    public SimpleGUI(final String title) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.setLocationByPlatform(true);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        final JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);
        final JButton button = new JButton("Save");
        panel.add(button, BorderLayout.SOUTH);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    SimpleGUI.this.controller.writeToCurrentFile("Test");
                } catch (IOException ex) {
                    ex.printStackTrace(); // NOPMD: allowed as this is just an exercise
                }
            }
        });
        frame.setVisible(true);
    }

    /**
     * Create a SimpleGUI object.
     * @param args
     */
    public static void main(final String[] args) {
        new SimpleGUI(TITLE);
    }
}
