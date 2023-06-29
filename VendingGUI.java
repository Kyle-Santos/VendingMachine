import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

public class VendingGUI extends JFrame{
    public VendingGUI() {
        super("Vending Machine");
        setLayout(new BorderLayout());

        init(); 

        // Set size of window
        setSize(450, 500);

        // explicitly set the visibility to true
        setVisible(true);
        setResizable(false);

        // Default: Hide the frame, don't end the program
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // layout the elements
    private void init() {

        // SOUTH 
        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(Color.DARK_GRAY);

        // JLabel create = new JLabel("Create");
        // panelSouth.add(create);
        
        JButton btn = new JButton("Regular");
        panelSouth.add(btn);

        JButton btn2 = new JButton("Special");
        panelSouth.add(btn2);

        this.add(panelSouth, BorderLayout.SOUTH);


        // NORTH
        JPanel panelNorth = new JPanel();
        panelNorth.setBackground(Color.BLACK);

        JLabel title = new JLabel("Vending Machine Factory Simulator");
        title.setForeground(Color.white);
        //title.setFont(setFont());
        panelNorth.add(title);

        this.add(panelNorth, BorderLayout.NORTH);


        // CENTER
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(Color.GRAY);
        panelCenter.setLayout(new GridBagLayout());
        JLabel label1 = new JLabel("Machine Name ");
        label1.setForeground(Color.white);
        panelCenter.add(label1);
        JTextField item = new JTextField("", 10);

        panelCenter.add(item);
        this.add(panelCenter, BorderLayout.CENTER);

    }
}