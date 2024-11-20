package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final String TITLE = "My first java graphical interface";
    private static final int PROPORTION = 3;

    private final Controller controller = new Controller();
    private final JFrame frame = new JFrame();

    /**
     * Create a simple GUI for saving a string into a file.
     * @param title of window
     */
    public SimpleGUIWithFileChooser(final String title) {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.setLocationByPlatform(true);
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel fileBrowse = new JPanel(new BorderLayout());
        final JTextArea textField = new JTextArea(this.controller.getCurrentFilePath());
        final JButton browse = new JButton("Browse...");
        final JButton save = new JButton("Save");
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fileChooser = 
                    new JFileChooser(controller.getCurrentFile());
                switch (fileChooser.showSaveDialog(textField)) {
                    case JFileChooser.APPROVE_OPTION -> {
                        textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                        controller.setCurrentFile(new File(textField.getText()));
                    }
                    case JFileChooser.CANCEL_OPTION -> {
                    }
                    default -> {
                        new Dialog(frame, JOptionPane.showInputDialog("An error has occured")); 
                    }
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.writeToCurrentFile("Test");
                    textField.setText(controller.getCurrentFilePath());
                } catch (IOException ex) {
                    ex.printStackTrace(); // NOPMD: allowed as this is just an exercise
                }
            }
        });
        textField.setEditable(false);
        fileBrowse.add(browse, BorderLayout.EAST);
        fileBrowse.add(textField, BorderLayout.CENTER);
        panel.add(fileBrowse, BorderLayout.NORTH);
        panel.add(save, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Create a SimpleGUI object.
     * @param args
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser(TITLE);
    }

}
