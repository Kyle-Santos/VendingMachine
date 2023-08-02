package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The MainMenuGUI class represents the main menu GUI of the Vending Machine Factory Simulator.
 * It extends JFrame and provides the main user interface for interacting with the application.
 */
public class MainMenuGUI extends JFrame{
    private JButton btnSpecial;
    private JButton btnRegular;
    private JButton btnFeatures;
    private JButton btnListVMS;
    private JTextField tfVendingName;
    private JTextField tfCreateTest;
    private JComboBox<String> allVMs;
    private JButton btnMaintenance;
    private JTextField tfCreate;
    private JTextField tfTest;
    private JButton btnExit;

    /**
     * Creates a new MainMenuGUI instance.
     * Initializes the main menu user interface.
     */
    public MainMenuGUI() {
        super("Vending Machine Factory Simulator");
        getContentPane().setLayout(new BorderLayout());
        
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

        JLabel create = new JLabel("2023 Vending Machine Corporation, Inc");
        create.setFont(new Font("Serif", Font.ITALIC, 11));
        create.setForeground(Color.white);
        panelSouth.add(create);

        getContentPane().add(panelSouth, BorderLayout.SOUTH);


        // NORTH
        JPanel panelNorth = new JPanel();
        panelNorth.setBackground(Color.BLACK);

        JLabel title = new JLabel("Main Menu");
        title.setForeground(Color.white);
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        panelNorth.add(title);

        getContentPane().add(panelNorth, BorderLayout.NORTH);


        // CENTER
        JPanel panelCenter = new JPanel();
        //panelCenter.setBackground(Color.GRAY);
        panelCenter.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        tfVendingName = new JTextField("Enter Name", 10);
        tfVendingName.setLocation(234, 114);
        tfVendingName.setSize(187, 25);
        tfVendingName.setHorizontalAlignment(0);
        tfVendingName.setMaximumSize(tfVendingName.getPreferredSize()); 
        panelCenter.add(tfVendingName);

        btnRegular = new JButton("Regular");
        btnRegular.setLocation(232, 140);
        btnRegular.setSize(100, 25);
        panelCenter.add(btnRegular);

        btnSpecial = new JButton("Special");
        btnSpecial.setLocation(322, 140);
        btnSpecial.setSize(100, 25);
        panelCenter.add(btnSpecial);

        btnFeatures = new JButton("Features");
        btnFeatures.setLocation(234, 237);
        btnFeatures.setSize(187, 25);
        panelCenter.add(btnFeatures);

        // list vms
        btnListVMS = new JButton("List Available VMs");
        
        btnMaintenance = new JButton("Maintenance");
        btnMaintenance.setBounds(234, 261, 187, 25);
        panelCenter.add(btnMaintenance);
        btnListVMS.setLocation(234, 305);
        btnListVMS.setSize(187, 42);
        panelCenter.add(btnListVMS);

        btnExit = new JButton("Exit");
        btnExit.setBounds(165, 364, 117, 29);
        panelCenter.add(btnExit);

        ImageIcon centerBG = new ImageIcon("src/images/centerMainBG.jpg");
        Image image = centerBG.getImage(); // transform it 
        Image newimg = image.getScaledInstance(450, 410,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        centerBG = new ImageIcon(newimg);

        getContentPane().add(panelCenter, BorderLayout.CENTER);
        panelCenter.setLayout(null);
        
        tfCreateTest = new JTextField();
        tfCreateTest.setForeground(Color.WHITE);
        tfCreateTest.setFont(new Font("Arial Black", Font.BOLD, 11));
        tfCreateTest.setBackground(new Color(137, 99, 82));
        tfCreateTest.setEditable(false);
        tfCreateTest.setText("    DO YOU WANNA TEST/CREATE A VENDING MACHINE?");
        tfCreateTest.setBounds(39, 27, 368, 42);
        tfCreateTest.setBorder(BorderFactory.createEtchedBorder());
        panelCenter.add(tfCreateTest);
        tfCreateTest.setColumns(10);

        tfCreate = new JTextField();
        tfCreate.setText(" CREATE VENDING MACHINE");
        tfCreate.setForeground(Color.WHITE);
        tfCreate.setFont(new Font("Arial Black", Font.BOLD, 11));
        tfCreate.setEditable(false);
        tfCreate.setColumns(10);
        tfCreate.setBorder(BorderFactory.createEtchedBorder());
        tfCreate.setBackground(new Color(137, 99, 82));
        tfCreate.setBounds(234, 96, 187, 16);
        panelCenter.add(tfCreate);
        
        tfTest = new JTextField();
        tfTest.setText("    TEST VENDING MACHINE");
        tfTest.setForeground(Color.WHITE);
        tfTest.setFont(new Font("Arial Black", Font.BOLD, 11));
        tfTest.setEditable(false);
        tfTest.setColumns(10);
        tfTest.setBorder(BorderFactory.createEtchedBorder());
        tfTest.setBackground(new Color(137, 99, 82));
        tfTest.setBounds(234, 192, 187, 16);
        panelCenter.add(tfTest);
                
        allVMs = new JComboBox<String>();
        allVMs.addItem("Select VM");
        allVMs.setSelectedIndex(0);
        //allVMs.setToolTipText("");
        allVMs.setBounds(234, 210, 187, 27);
        panelCenter.add(allVMs);

        JLabel mainmenuBG = new JLabel(centerBG);
        mainmenuBG.setLocation(0, 0);
        mainmenuBG.setSize(450, 410);
        panelCenter.add(mainmenuBG);
    }

    /**
     * Sets an ActionListener to handle button clicks.
     *
     * @param listener The ActionListener to be set.
     */
    public void setActionListener(ActionListener listener) {
        btnFeatures.addActionListener(listener);
        btnRegular.addActionListener(listener);
        btnSpecial.addActionListener(listener);
        btnListVMS.addActionListener(listener);
        btnMaintenance.addActionListener(listener);
        btnExit.addActionListener(listener);
    }

    /**
     * Gets the text entered in the vending name text field.
     *
     * @return The text entered in the vending name text field.
     */
    public String getTfVendingName() {
        return this.tfVendingName.getText();
    }

    /**
     * Gets the index of the selected vending machine in the combo box.
     *
     * @return The index of the selected vending machine in the combo box.
     */
    public int getSelectedVM() {
        return this.allVMs.getSelectedIndex();
    }

    /**
     * Adds a vending machine name to the combo box.
     *
     * @param name The name of the vending machine to be added to the combo box.
     */
    public void addVMList(String name) {
        allVMs.addItem(name);
    }
}