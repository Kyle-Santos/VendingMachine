package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.Money;
import Model.Vending;
import View.MaintenanceGUI;

/**
 * The MaintenanceController class handles user interactions and events from the MaintenanceGUI.
**/
public class MaintenanceController implements ActionListener, ListSelectionListener, ChangeListener { 
    private MaintenanceGUI gui;
    private Vending vending;
    private Money insert;

    /**
     * Creates a new MaintenanceController instance.
     *
     * @param gui      The MaintenanceGUI to be controlled.
     * @param vending  The Selected Vending model.
    **/
    public MaintenanceController(MaintenanceGUI gui, Vending vending) {
        this.gui = gui;
        this.vending = vending;
        this.insert = new Money();

        gui.setActionListener(this);
        gui.setListSelectionListener(this);
        gui.setChangeListener(this);

        gui.setListItems(vending.getSlots());
        gui.setTxtMoney(vending.getMoney().getTotalAmount());
        gui.setTextTransaction(vending.getTransactionList());
    }

    /**
     * Updates the inventory view in the GUI.
    **/
    public void updateInventoryView() {
        gui.setListItems(vending.getSlots());
    }
    
    /**
     * Converts a string to an integer.
     *
     * @param s The string to convert.
     * @return The converted integer value, or -1 if the conversion fails.
    **/
    public int convertToInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Restocks the selected item with the specified quantity.
    **/
    private void restockItem() {
        int qty = convertToInt(gui.getSpinnerAddQty());
            
        if (qty > 0) {
            vending.restockItem(gui.getSelectedIndexListItems(), qty);
            popMessage("Successfully Added Quantity To:\n" + (vending.getItemInfo(gui.getSelectedIndexListItems())), "Succesful", 1);
            updateInventoryView();
        }
        else 
            popMessage("Enter An Integer Greater than 0", "Invalid Input", 0);

        gui.setAttributesFunction(false);
        gui.clearListSelected();
    }

    /**
     * Updates the price of the selected item with the specified value.
     */
    private void updatePrice() {
        int price = convertToInt(gui.getTfNewPrice());

            if (price > 0) {
                vending.updatePrice(gui.getSelectedIndexListItems(), price);
                popMessage("Successfully Updated The Price Of:\n" + (vending.getItemInfo(gui.getSelectedIndexListItems())), "Succesful", 1);
                updateInventoryView();
            }
            else
                popMessage("Enter An Integer Greater than 0", "Invalid Input", 0);
            
            gui.setAttributesFunction(false);
            gui.clearListSelected();
    }

    /**
     * Collects the money from the vending machine.
    **/
    private void collectMoney() {
        Money collected = vending.getMoney();
        if (gui.getCheckSure()) {
            if (collected.getTotalAmount() > 0) {
                popMessage("Money (" + collected.getTotalAmount() + ") was collected", "Collect oney Successful", 1);
                vending.getMoney().resetMoney();
                gui.setTxtMoney(collected.getTotalAmount());
            }
            else
                popMessage("Nothing To Collect.", "Unsuccessful", 0);
        }
        else
            popMessage("Check The Verification Box First.", "Collect Money Unsuccessful", 0);
    }

    /**
     * Adds a new item to the inventory.
    **/
    private void addItem() {
        String name = gui.getTfName(); 
        String txtcalories = gui.getTfCalories();
        String txtnewQty = gui.getSpinnerNewQty();
        String txtcost = gui.getTfCost();
        double calories = 0;
        int newQty = 0, cost = 0;
        boolean valid = true;

        if (!(name.equals("") || txtcalories.equals("") || txtnewQty.equals("") || txtcost.equals(""))) {
            try {
                calories = Double.parseDouble(txtcalories);
                newQty = Integer.parseInt(txtnewQty);
                cost = Integer.parseInt(txtcost);
            }
            catch (NumberFormatException n) {
                valid = false;
                popMessage("Invalid Input. Enter valid inputs.", "Add Item Unsuccesful", 0);
            }

            if (valid) {
                if (cost > 0 && newQty > 0 && calories > 0) {
                    vending.addItem(name, calories, cost, newQty, gui.getItemType());
                    gui.setListItems(vending.getSlots());
                    popMessage(name + " with " + calories + " calories is successfully added!", "Add Item Successful", 1);
                }
                else
                    popMessage("Invalid input. Enter integers greater than 0.", "Add Item Unsuccesful", 0);
            }
                
        }
        else 
            popMessage("Complete All The Fields", "Add Item Unsuccesful", 0);
    }

    /**
     * Handles the actionPerformed event for buttons in the MaintenanceGUI.
     *
     * @param e The ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] denominations = {"100", "50", "20", "10", "5", "1"};

        if (e.getActionCommand().equals("Restock Item")) 
            restockItem();
        else if (e.getActionCommand().equals("Update")) 
            updatePrice();
        else if (e.getActionCommand().equals("Collect")) 
            collectMoney();
        else if (e.getActionCommand().equals("Add Item")) 
            addItem();
        else if (e.getActionCommand().equals("ADD")) {
            if (insert.getTotalAmount() == 0)
                popMessage("You need to add any denominations first!", "Inserting Unsuccessful", 0);
            else {
                vending.getMoney().updateDenominations(insert);
                popMessage("Money (" + (insert.getTotalAmount())  + " Pesos) Successfully Added!", "Inserting Successful", 1);
                insert.resetMoney();
                gui.setLabelAmount(insert.getTotalAmount());
                gui.setTxtMoney(vending.getMoney().getTotalAmount());
            }
        } 
        else {
            for (String d : denominations) {
                if (e.getActionCommand().equals(d)) {
                    insert.addDenomination(Integer.parseInt(d), 1);
                    gui.setLabelAmount(insert.getTotalAmount());
                    break;
                }
            }
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            if (gui.getSelectedIndexListItems() != -1)
                gui.setAttributesFunction(true);
            else
                gui.setAttributesFunction(false);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == gui.getTabbedPane()) {
            if (gui.getTabbedPane().getSelectedIndex() != 2) {
                insert.resetMoney();
                gui.setLabelAmount(insert.getTotalAmount());
            }

            if (gui.getTabbedPane().getSelectedIndex() != 0) {
                gui.setAttributesFunction(false);
                gui.clearListSelected();
            }
        }
    }

    /**
     * Displays a message dialog box with an optional icon.
     *
     * @param message The message to display.
     * @param title   The title of the dialog box.
     * @param type    The type of the dialog box (1 for info, 0 for error).
    **/
    private void popMessage(String message, String title, int type) {
        if (type == 1)
            JOptionPane.showMessageDialog(null, message, title, type, new ImageIcon("src/images/iconVM.png"));
        else 
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);

    }
    
}
