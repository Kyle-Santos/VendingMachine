package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;

public class InsertMoneyGUI extends JFrame {
    private JButton btn1;
    private JButton btn5;
    private JButton btn10;
    private JButton btn20;
    private JButton btn50;
    private JButton btn100;
    private JLabel labelAmount;
    private JButton btnInsertMoney;

    public InsertMoneyGUI() {
        super("Vending Machine Factory Simulator");
        getContentPane().setLayout(new BorderLayout());
        
        init(); 

        // Set size of window
        setSize(350, 300);

        // explicitly set the visibility to true
        setVisible(true);
        setResizable(false);

        // Default: Hide the frame, don't end the program
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        JLabel title = new JLabel("Insert Money");
        title.setForeground(Color.white);
        title.setFont(new Font("Times New Roman", Font.BOLD, 18));
        panelNorth.add(title);

        getContentPane().add(panelNorth, BorderLayout.NORTH);


        // CENTER
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(Color.GRAY);
        panelCenter.setLayout(new GridLayout(3, 1));
        //panelCenter.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        // panelCenter (0,0)
        JPanel panelCenter1 = new JPanel(new GridLayout(1, 3));
        panelCenter1.setBackground(new Color(147, 149, 151));

        btn100 = new JButton("100");
        panelCenter1.add(btn100);

        btn50 = new JButton("50");
        panelCenter1.add(btn50);

        btn20 = new JButton("20");
        panelCenter1.add(btn20);

        panelCenter.add(panelCenter1);

        // panelCenter (1,0)
        JPanel panelCenter2 = new JPanel(new GridLayout(1, 3));
        panelCenter2.setBackground(new Color(147, 149, 151));

        btn10 = new JButton("10");
        panelCenter2.add(btn10);

        btn5 = new JButton("5");
        panelCenter2.add(btn5);

        btn1 = new JButton("1");
        panelCenter2.add(btn1);

        panelCenter.add(panelCenter2);

        // panelCenter (3, 0)
        JPanel panelCenter3 = new JPanel(new GridLayout(2, 1));
        panelCenter3.setBackground(new Color(147, 149, 151));

        JPanel panelCenter3A = new JPanel();
        panelCenter3A.setBackground(new Color(175, 176, 180));
        panelCenter3A.setLayout(null);
        labelAmount = new JLabel("Amount: 0");
        labelAmount.setForeground(new Color(175, 137, 105));
        labelAmount.setFont(new Font("Kohinoor Devanagari", Font.BOLD, 20));
        labelAmount.setHorizontalAlignment(SwingConstants.CENTER);
        labelAmount.setBounds(102, 12, 141, 16);
        panelCenter3A.add(labelAmount);
        panelCenter3.add(panelCenter3A);

        btnInsertMoney = new JButton("ADD");
        panelCenter3.add(btnInsertMoney);

        panelCenter.add(panelCenter3);

        getContentPane().add(panelCenter, BorderLayout.CENTER);

    }

    public void setActionListener(ActionListener listener) {
        btn100.addActionListener(listener);
        btn50.addActionListener(listener);
        btn20.addActionListener(listener);
        btn10.addActionListener(listener);
        btn5.addActionListener(listener);
        btn1.addActionListener(listener);
        btnInsertMoney.addActionListener(listener);
    }

    public void setDocumentListener(DocumentListener listner) {
        //tfAmount.getDocument().addDocumentListener(listener);
    }

    public void setLabelAmount(int amount) {
        this.labelAmount.setText("Amount: " + amount);
    }
}