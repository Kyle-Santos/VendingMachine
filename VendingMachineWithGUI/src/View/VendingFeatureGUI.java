package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import Model.Inventory;
import Model.Item;

/**
 * The VendingFeatureGUI class represents the GUI for regular features in the vending machine.
 * It extends JFrame and provides features for buying items and inserting money.
 */
public class VendingFeatureGUI extends JFrame {
    private JTabbedPane tabbedPane;

    private JButton btn1;
    private JButton btn5;
    private JButton btn10;
    private JButton btn20;
    private JButton btn50;
    private JButton btn100;
    private JLabel labelAmount;
    private JButton btnInsertMoney;

    private JScrollPane scrollPane;
    private JList<String> listItems;
    private JButton btnBuy;
    private JButton btnCancel;
    private JTextField tfQuantity;

    /**
     * Creates a new VendingFeatureGUI instance.
     * Initializes the GUI for regular features of the vending machine.
     */
    public VendingFeatureGUI() {
        super("Vending Machine Factory Simulator");
        
        init(); 

        // Set size of window
        setSize(450, 320);

        // explicitly set the visibility to true
        setVisible(true);
        setResizable(false);

        // Default: Hide the frame, don't end the program
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    // layout the elements
    private void init() {
        getContentPane().setLayout(new CardLayout(0, 0));
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        // PANEL BUY
        JPanel panelBuy = new JPanel();
        tabbedPane.addTab("Buy", null, panelBuy, null);
        tabbedPane.setDisplayedMnemonicIndexAt(0, 0);
        panelBuy.setLayout(null);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 429, 152);
        panelBuy.add(scrollPane);

        listItems = new JList<String>();

        scrollPane.setViewportView(listItems);
        
        btnBuy = new JButton();
        btnBuy.setText("Buy");
        btnBuy.setEnabled(false);
        btnBuy.setBounds(130, 211, 75, 29);
        panelBuy.add(btnBuy);
        
        btnCancel = new JButton();
        btnCancel.setText("Cancel");
        btnCancel.setBounds(217, 211, 86, 29);
        panelBuy.add(btnCancel);
        
        JLabel labelQuantity = new JLabel();
        labelQuantity.setText("How many are you buying? ");
        labelQuantity.setBounds(66, 175, 172, 16);
        panelBuy.add(labelQuantity);
        
        tfQuantity = new JTextField();
        tfQuantity.setText("0");
        tfQuantity.setEditable(false);
        tfQuantity.setBounds(250, 170, 112, 26);
        panelBuy.add(tfQuantity);
        

        // INSERT PANEL
        JPanel panelInsert = new JPanel();
        tabbedPane.addTab("Insert Money", panelInsert);
        panelInsert.setLayout(null);
        
        JPanel panelInsertBody = new JPanel();
        panelInsertBody.setBounds(0, 0, 429, 246);
        panelInsertBody.setLayout(new GridLayout(3, 1, 0, 0));
        //panelInsertBody.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        
        JPanel panelInsert1 = new JPanel(new GridLayout(1, 3));
        panelInsert1.setBackground(new Color(147, 149, 151));

        btn100 = new JButton("100");
        panelInsert1.add(btn100);

        btn50 = new JButton("50");
        panelInsert1.add(btn50);

        btn20 = new JButton("20");
        panelInsert1.add(btn20);

        panelInsertBody.add(panelInsert1);

        // panelInsertBody (1,0)
        JPanel panelInsert2 = new JPanel(new GridLayout(1, 3));
        panelInsert2.setBackground(new Color(147, 149, 151));

        btn10 = new JButton("10");
        panelInsert2.add(btn10);

        btn5 = new JButton("5");
        panelInsert2.add(btn5);

        btn1 = new JButton("1");
        panelInsert2.add(btn1);

        panelInsertBody.add(panelInsert2);

        // panelInsertBody (3, 0)
        JPanel panelInsert3 = new JPanel(new GridLayout(2, 1));
        panelInsert3.setBackground(new Color(147, 149, 151));

        JPanel panelInsert3A = new JPanel();
        panelInsert3A.setBackground(new Color(160, 160, 166));
        panelInsert3A.setLayout(null);
        labelAmount = new JLabel("Amount: 0");
        labelAmount.setForeground(new Color(189, 217, 181));
        labelAmount.setFont(new Font("Kohinoor Devanagari", Font.BOLD, 20));
        labelAmount.setHorizontalAlignment(SwingConstants.CENTER);
        labelAmount.setBounds(144, 12, 141, 16);
        panelInsert3A.add(labelAmount);
        panelInsert3.add(panelInsert3A);

        btnInsertMoney = new JButton("ADD");
        panelInsert3.add(btnInsertMoney);

        panelInsertBody.add(panelInsert3);
        
        panelInsert.add(panelInsertBody);


        getContentPane().add(tabbedPane);
    }

    /**
     * Sets an ActionListener to handle button clicks.
     *
     * @param listener The ActionListener to be set.
     */
    public void setActionListener(ActionListener listener) {
        btn100.addActionListener(listener);
        btn50.addActionListener(listener);
        btn20.addActionListener(listener);
        btn10.addActionListener(listener);
        btn5.addActionListener(listener);
        btn1.addActionListener(listener);
        btnInsertMoney.addActionListener(listener);

        btnBuy.addActionListener(listener);
        btnCancel.addActionListener(listener);
    }

    /**
     * Sets a ListSelectionListener to handle list selection events for the list of items and toppings.
     *
     * @param listener The ListSelectionListener to be set.
     */
    public void setListSelectionListener(ListSelectionListener listener) {
        listItems.addListSelectionListener(listener);
    } 

    /**
     * Sets a ChangeListener to handle tab change events for the tabbed pane.
     *
     * @param listener The ChangeListener to be set.
     */
    public void setChangeListener(ChangeListener listener) {
        tabbedPane.addChangeListener(listener);
    }

    public int getSelectedIndexListItems() {
        return this.listItems.getSelectedIndex();
    }

    public JTabbedPane getTabbedPane() {
        return this.tabbedPane;
    }

    public String getTfQuantity() {
        return this.tfQuantity.getText();
    }

    public int getSelectedItem() {
        return this.listItems.getSelectedIndex();
    }

    public void clearListSelected() {
        this.listItems.clearSelection();
    }

    public void setAttributesFunction(boolean enable) {
        this.btnBuy.setEnabled(enable);
        this.tfQuantity.setEditable(enable);
    }

    public void setListItems(Inventory slots) {
        // list model
        DefaultListModel<String> listModel = new DefaultListModel<String>();

        // add items from inventory to list model
        for (Item i : slots.getSlot()) {
            listModel.addElement(i.toString());
        }

        // set model to list
        listItems.setModel(listModel);
        listItems.clearSelection();
    }

    public void setLabelAmount(int amount) {
        this.labelAmount.setText("Amount: " + amount);
    }
}