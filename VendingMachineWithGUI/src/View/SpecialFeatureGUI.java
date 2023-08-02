package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import Model.Inventory;
import Model.Item;

/**
 * The SpecialFeatureGUI class represents the GUI for special features in the vending machine.
 * It extends JFrame and provides features for buying items, customizing sushi, and inserting money.
 */
public class SpecialFeatureGUI extends JFrame{
    private JTabbedPane tabbedPane;

    // Insert Money Pane
    private JButton btn1;
    private JButton btn5;
    private JButton btn10;
    private JButton btn20;
    private JButton btn50;
    private JButton btn100;
    private JLabel labelAmount;
    private JButton btnInsertMoney;

    // Buy Item Pane
    private JScrollPane scrollPane;
    private JList<String> listItems;
    private JButton btnBuy;
    private JButton btnCancel;
    private JTextField tfQuantity;

    // Customize Sushi Pane
    private JComboBox<String> listNori;
    private JComboBox<String> listRice;
    private JLabel labelNori;
    private JLabel labelRice;
    private JTextField tfQtyCustom;
    private JList<String> listTopping;
    private JTextArea txtReceipt;
    private JButton btnCreate;
    private JButton btnAddTopping;
    private ArrayList<Integer> toppingIndexes;

    /**
     * Creates a new SpecialFeatureGUI instance.
     * Initializes the GUI for special features of the vending machine.
     */
    public SpecialFeatureGUI() {
        super("Vending Machine Factory Simulator");
        
        init(); 

        // Set size of window
        setSize(450, 370);

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
        scrollPane.setBounds(0, 0, 429, 187);
        panelBuy.add(scrollPane);

        listItems = new JList<String>();

        scrollPane.setViewportView(listItems);
        
        btnBuy = new JButton();
        btnBuy.setText("Buy");
        btnBuy.setEnabled(false);
        btnBuy.setBounds(130, 242, 75, 29);
        panelBuy.add(btnBuy);
        
        btnCancel = new JButton();
        btnCancel.setText("Cancel");
        btnCancel.setBounds(217, 242, 86, 29);
        panelBuy.add(btnCancel);
        
        JLabel labelQuantity = new JLabel();
        labelQuantity.setText("How many are you buying? ");
        labelQuantity.setBounds(66, 207, 172, 16);
        panelBuy.add(labelQuantity);
        
        tfQuantity = new JTextField();
        tfQuantity.setText("0");
        tfQuantity.setEditable(false);
        tfQuantity.setBounds(250, 204, 112, 26);
        panelBuy.add(tfQuantity);
        


        // PANEL CUSTOMIZE
        toppingIndexes = new ArrayList<Integer>();
        JPanel panelCustomize = new JPanel();
        tabbedPane.addTab("Customize Sushi", null, panelCustomize, null);
        panelCustomize.setLayout(null);
        
        listNori = new JComboBox<String>();
        listNori.setBounds(72, 20, 172, 27);
        panelCustomize.add(listNori);
        
        listRice = new JComboBox<String>();
        listRice.setBounds(70, 56, 172, 27);
        panelCustomize.add(listRice);
        
        labelNori = new JLabel("Nori");
        labelNori.setBounds(24, 24, 61, 16);
        panelCustomize.add(labelNori);
        
        labelRice = new JLabel("Rice");
        labelRice.setBounds(24, 60, 61, 16);
        panelCustomize.add(labelRice);
        
        JScrollPane paneTopping = new JScrollPane();
        paneTopping.setBounds(24, 116, 220, 134);
        panelCustomize.add(paneTopping);
        
        listTopping = new JList<String>();
        paneTopping.setViewportView(listTopping);
        
        JLabel labelTopping = new JLabel("Toppings");
        labelTopping.setBounds(102, 95, 61, 16);
        panelCustomize.add(labelTopping);
        
        btnAddTopping = new JButton("Add");
        btnAddTopping.setBounds(153, 257, 99, 29);
        panelCustomize.add(btnAddTopping);
        
        tfQtyCustom = new JTextField();
        tfQtyCustom.setBounds(87, 257, 61, 26);
        panelCustomize.add(tfQtyCustom);
        tfQtyCustom.setColumns(10);
        
        JLabel labelQty = new JLabel("Quantity");
        labelQty.setBounds(24, 262, 61, 16);
        panelCustomize.add(labelQty);
        
        btnCreate = new JButton("Create");
        btnCreate.setEnabled(false);
        btnCreate.setBounds(285, 257, 117, 29);
        panelCustomize.add(btnCreate);
        
        txtReceipt = new JTextArea();
        txtReceipt.setText("\nAmount: 0\nCalories: 0.0");
        txtReceipt.setEditable(false);
        txtReceipt.setLineWrap(true);
        txtReceipt.setWrapStyleWord(true);
        txtReceipt.setBackground(new Color(238, 238, 238));
        txtReceipt.setBounds(273, 44, 137, 206);
        panelCustomize.add(txtReceipt);
        
        JLabel labelReceipt = new JLabel("Receipt:");
        labelReceipt.setHorizontalAlignment(SwingConstants.CENTER);
        labelReceipt.setBounds(267, 20, 61, 16);
        panelCustomize.add(labelReceipt);
        

        // INSERT PANEL
        JPanel panelInsert = new JPanel();
        tabbedPane.addTab("Insert Money", panelInsert);
        panelInsert.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panelInsertBody = new JPanel();
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
        labelAmount.setBounds(144, 17, 141, 16);
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
        btnAddTopping.addActionListener(listener);
        btnCreate.addActionListener(listener);

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
        listTopping.addListSelectionListener(listener);
    } 

    /**
     * Sets a ChangeListener to handle tab change events for the tabbed pane.
     *
     * @param listener The ChangeListener to be set.
     */
    public void setChangeListener(ChangeListener listener) {
        tabbedPane.addChangeListener(listener);
    }

    /**
     * Sets an ItemListener to handle item change events for the combo boxes of Nori and Rice.
     *
     * @param listener The ItemListener to be set.
     */
    public void setItemChangeListener(ItemListener listener) {
        listNori.addItemListener(listener);
        listRice.addItemListener(listener);
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

    public String getNori() {
        if (listNori.getSelectedIndex() != - 1)
            return this.listNori.getSelectedItem().toString();
        return "";
    }

    public String getRice() {
        if (listRice.getSelectedIndex() != -1)
            return this.listRice.getSelectedItem().toString();
        return "";
    }

    public int getTopping() {
        return toppingIndexes.get(this.listTopping.getSelectedIndex());
    }

    public String getTfQtyCustom() {
        return this.tfQtyCustom.getText();
    }

    public void clearListSelected() {
        this.listItems.clearSelection();
        this.listTopping.clearSelection();
    }

    public void enableCreate(boolean enable) {
        this.btnCreate.setEnabled(enable);
    }

    public void setSelectedMainItem() {
        listNori.setSelectedIndex(-1);
        listRice.setSelectedIndex(-1);
    }

    public void setAttributesFunction(boolean enable) {
        this.btnBuy.setEnabled(enable);
        this.tfQuantity.setEditable(enable);
        this.tfQtyCustom.setEditable(enable);
        this.btnAddTopping.setEnabled(enable);
    }

    public void setListItems(Inventory slots) {
        int index = 0; // To keep track of list of topping indexes
        // list model
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        DefaultListModel<String> listModelTopping = new DefaultListModel<String>();
        listNori.removeAllItems();
        listRice.removeAllItems();
        // add items from inventory to list model
        for (Item i : slots.getSlot()) {
            if (i.getType().equals("Nori"))
                listNori.addItem(i.getName());
            else if (i.getType().equals("Rice"))
                listRice.addItem(i.getName());
            else {
                listModelTopping.addElement(i.toString());
                toppingIndexes.add(index);
            }

            index++;
            listModel.addElement(i.toString());
        }

        setSelectedMainItem();

        // set model to list
        listTopping.setModel(listModelTopping);
        listTopping.clearSelection();

        listItems.setModel(listModel);
        listItems.clearSelection();
    }

    public void setLabelAmount(int amount) {
        this.labelAmount.setText("Amount: " + amount);
    }

    public void setReceipt(String receipt) {
        this.txtReceipt.setText(receipt);
    }
}