package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.Timer;

import Model.SpecialVending;

/**
 * The PrepareSushiGUI class represents the GUI for preparing sushi using a SpecialVending machine.
 * It extends JFrame and displays the steps involved in preparing sushi with a progress bar.
 */
public class PrepareSushiGUI extends JFrame{
    private ArrayList<String> steps;

    /**
     * Creates a new PrepareSushiGUI instance.
     * Initializes the GUI for preparing sushi with the provided SpecialVending machine.
     *
     * @param vending The SpecialVending machine used to prepare sushi.
     */
    public PrepareSushiGUI(SpecialVending vending) {
        super("Preparing Your Sushi");

        this.steps = vending.loadSteps();
        int height = 250 + this.steps.size() * 10;
        
        init(); 

        // Set size of window
        setSize(300, height);

        // explicitly set the visibility to true
        setVisible(true);
        setResizable(false);

        // Default: Hide the frame, don't end the program
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // layout the elements
    private void init() {
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(new Color(79, 79, 79));
        panelCenter.setBounds(0, 0, 300, 72);
        //panelCenter.setBackground(Color.GRAY);
        panelCenter.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panelCenter.setLayout(null);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setBackground(new Color(139, 140, 141));
        progressBar.setForeground(new Color(233, 233, 233));
        progressBar.setStringPainted(true);
        progressBar.setBounds(35, 35, 234, 20);
        panelCenter.add(progressBar);
        
        JTextArea textStep = new JTextArea();
        textStep.setForeground(new Color(232, 234, 234));
        textStep.setBackground(new Color(79, 79, 79));
        textStep.setBounds(71, 67, 205, 300);
        panelCenter.add(textStep);
        
        Timer timer = new Timer(500, new ActionListener() {
            private int progress = 0;
            private int ctr = 0;
            private String txtStep = "";
            private int increment = 100 / steps.size();

            @Override
            public void actionPerformed(ActionEvent e) {
                progress += increment;

                if (progress <= 100) {
                    txtStep += steps.get(ctr) + "\n";
                    textStep.setText(txtStep);
                    progressBar.setValue(progress);
                    ctr++;

                } else {
                    ((Timer) e.getSource()).stop();
                    dispose();
                }
            }
        });

        timer.start();
        getContentPane().add(panelCenter);

    }

}