package View;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MaintenanceGUI extends JFrame{
    public MaintenanceGUI() {
        super("Vending Machine Factory Simulator");
        getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
        
        init(); 

        // Set size of window
        setSize(450, 500);

        // explicitly set the visibility to true
        setVisible(true);
        setResizable(false);

        // Default: Hide the frame, don't end the program
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void init() {
    	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane);
        
        
        JPanel panelRestock = new JPanel();
        tabbedPane.addTab("New tab", null, panelRestock, null);
        panelRestock.setLayout(new GridLayout(1, 0, 0, 0));
    }
}
