package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import Model.Inventory;
import Model.Item;

public class MaintenanceGUI extends JFrame {
	private JTabbedPane tabbedPane;

	private JButton btnRestock;
	private JButton btnUpdate;
	private JSpinner spinnerAddQty;
	private JTextField tfNewPrice;
	private JList<String> listItems;

	private JTextField txtMoney;
	private JButton btnCollect;
	private JCheckBox checkSure;

	private JButton btn1;
    private JButton btn5;
    private JButton btn10;
    private JButton btn20;
    private JButton btn50;
    private JButton btn100;
    private JLabel labelAmount;
    private JButton btnInsertMoney;

    private JTextField tfName;
    private JTextField tfCalories;
    private JTextField tfCost;
	private JSpinner spinnerNewQty;
	private JButton btnAddItem;

    public MaintenanceGUI() {
        super("Vending Machine Factory Simulator");
        
        init(); 

        // Set size of window
        setSize(450, 400);

        // explicitly set the visibility to true
        setVisible(true);
        setResizable(false);

        // Default: Hide the frame, don't end the program
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void init() {
    	tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	tabbedPane.setBounds(0, 0, 450, 372);
        
        
		// PANEL UPDATE
        JPanel panelUpdateItem = new JPanel();
        tabbedPane.addTab("Update Item", null, panelUpdateItem, null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 6, 417, 190);
        
        btnRestock = new JButton("Restock Item");
        btnRestock.setEnabled(false);
        btnRestock.setBounds(275, 214, 126, 29);
        
        JLabel labelNewPrice = new JLabel("Enter New Price Of Item:");
        labelNewPrice.setBounds(26, 276, 152, 16);
        
        JLabel labelQuantity = new JLabel("Enter Quantity To Be Added:");
        labelQuantity.setBounds(26, 219, 176, 16);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setEnabled(false);
        btnUpdate.setBounds(307, 271, 94, 29);
        
        spinnerAddQty = new JSpinner();
        spinnerAddQty.setEnabled(false);
        spinnerAddQty.setBounds(219, 214, 50, 26);
        
        listItems = new JList<String>();
        scrollPane.setRowHeaderView(listItems);
        panelUpdateItem.setLayout(null);
        panelUpdateItem.add(btnRestock);
        panelUpdateItem.add(labelQuantity);
        panelUpdateItem.add(spinnerAddQty);
        panelUpdateItem.add(labelNewPrice);
        panelUpdateItem.add(btnUpdate);
        panelUpdateItem.add(scrollPane);
        
        tfNewPrice = new JTextField();
        tfNewPrice.setEditable(false);
        tfNewPrice.setBounds(190, 271, 105, 26);
        panelUpdateItem.add(tfNewPrice);
        tfNewPrice.setColumns(10);
        


		// PANEL COLLECT
        JPanel panelCollect = new JPanel();
        tabbedPane.addTab("Collect Money", null, panelCollect, null);
        
        txtMoney = new JTextField();
        txtMoney.setEnabled(false);
        txtMoney.setEditable(false);
        txtMoney.setBounds(0, 25, 429, 92);
        txtMoney.setFont(new Font("Lucida Grande", Font.PLAIN, 42));
        txtMoney.setHorizontalAlignment(SwingConstants.CENTER);
        txtMoney.setColumns(10);
        
        btnCollect = new JButton("Collect");
        btnCollect.setBounds(170, 267, 88, 29);
        
        checkSure = new JCheckBox("Are You Sure You Want To Collect All Money?");
        checkSure.setBounds(56, 220, 314, 23);
        
        JEditorPane txtInfo = new JEditorPane();
        txtInfo.setBounds(100, 142, 230, 48);
        txtInfo.setBackground(new Color(238, 238, 238));
        txtInfo.setEditable(false);
        txtInfo.setText("This is all the money your vending \nmachine have. You can choose to \ncollector withdraw all this money.");
        panelCollect.setLayout(null);

        panelCollect.add(btnCollect);
        panelCollect.add(txtMoney);
        panelCollect.add(txtInfo);
        panelCollect.add(checkSure);
        


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
        labelAmount.setBounds(144, 20, 141, 16);
        panelInsert3A.add(labelAmount);
        panelInsert3.add(panelInsert3A);

        btnInsertMoney = new JButton("ADD");
        panelInsert3.add(btnInsertMoney);

        panelInsertBody.add(panelInsert3);
        
        panelInsert.add(panelInsertBody);



		JPanel panelAddItem = new JPanel();
        tabbedPane.addTab("Add Item", null, panelAddItem, null);
        panelAddItem.setLayout(null);
        
        JLabel labelName = new JLabel("Item name:");
        labelName.setBounds(22, 0, 157, 65);
        panelAddItem.add(labelName);
        
        tfName = new JTextField();
        tfName.setBounds(234, 8, 178, 48);
        panelAddItem.add(tfName);
        tfName.setColumns(10);
        
        JLabel labelCalories = new JLabel("How many calories does it have?");
        labelCalories.setBounds(22, 65, 214, 65);
        panelAddItem.add(labelCalories);
        
        tfCalories = new JTextField();
        tfCalories.setBounds(234, 73, 178, 48);
        panelAddItem.add(tfCalories);
        tfCalories.setColumns(10);
        
        JLabel labelCost = new JLabel("How much does it cost?");
        labelCost.setBounds(22, 130, 214, 65);
        panelAddItem.add(labelCost);
        
        tfCost = new JTextField();
        tfCost.setBounds(234, 138, 178, 48);
        panelAddItem.add(tfCost);
        tfCost.setColumns(10);
        
        JLabel labelNewQty = new JLabel("How many is the quantity?");
        labelNewQty.setBounds(22, 195, 214, 65);
        panelAddItem.add(labelNewQty);
        
        spinnerNewQty = new JSpinner();
        spinnerNewQty.setBounds(234, 203, 178, 48);
        panelAddItem.add(spinnerNewQty);
        
        btnAddItem = new JButton("Add Item");
        btnAddItem.setBounds(119, 263, 214, 48);
        panelAddItem.add(btnAddItem);

        getContentPane().add(tabbedPane);
    }

	public void setActionListener(ActionListener listener) {
		btnRestock.addActionListener(listener);
		btnUpdate.addActionListener(listener);

		btnCollect.addActionListener(listener);

        btn100.addActionListener(listener);
        btn50.addActionListener(listener);
        btn20.addActionListener(listener);
        btn10.addActionListener(listener);
        btn5.addActionListener(listener);
        btn1.addActionListener(listener);
        btnInsertMoney.addActionListener(listener);

        btnAddItem.addActionListener(listener);
    }

	public int getSelectedIndexListItems() {
        return this.listItems.getSelectedIndex();
    }

	public JTabbedPane getTabbedPane() {
        return this.tabbedPane;
    }

	public String getSpinnerAddQty() {
		return this.spinnerAddQty.getValue().toString();
	}

	public String getTfNewPrice() {
		return this.tfNewPrice.getText();
	}

	public boolean getCheckSure() {
		return this.checkSure.isSelected();
	}

    public void setListSelectionListener(ListSelectionListener listener) {
        listItems.addListSelectionListener(listener);
    } 

    public void setChangeListener(ChangeListener listener) {
        tabbedPane.addChangeListener(listener);
		checkSure.addChangeListener(listener);
    }

	public void clearListSelected() {
        this.listItems.clearSelection();
    }

    public void setAttributesFunction(boolean enable) {
        this.btnRestock.setEnabled(enable);
		this.btnUpdate.setEnabled(enable);
		this.spinnerAddQty.setEnabled(enable);
        this.tfNewPrice.setEditable(enable);
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

	public void setTxtMoney(int amount) {
		this.txtMoney.setText("Total Money: " + amount);
	}
}
