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
import Model.VendingCollection;
import View.MaintenanceGUI;

public class MaintenanceController implements ActionListener, ListSelectionListener, ChangeListener { 
    private MaintenanceGUI gui;
    private VendingCollection vendings;
    private Money insert;

    public MaintenanceController(MaintenanceGUI gui, VendingCollection vendings) {
        this.gui = gui;
        this.vendings = vendings;
        this.insert = new Money();

        gui.setActionListener(this);
        gui.setListSelectionListener(this);
        gui.setChangeListener(this);

        gui.setListItems(vendings.getCurrentVending().getInventory());
        gui.setTxtMoney(vendings.getCurrentVending().getMoney().getTotalAmount());
    }

    public void updateInventoryView() {
        gui.setListItems(vendings.getCurrentVending().getInventory());
    }
    
    public int convertToInt(String s) {
        int converted = -1;

        try {
            converted = Integer.parseInt(s);
        }
        catch (NumberFormatException e) {

        }

        return converted;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] denominations = {"100", "50", "20", "10", "5", "1"};
        int qty, price;

        if (e.getActionCommand().equals("Restock Item")) {
            qty = convertToInt(gui.getSpinnerAddQty());
            
            if (qty > 0) {
                vendings.getCurrentVending().restockItem(gui.getSelectedIndexListItems(), qty);
                popMessage("Successfully Added Quantity To:\n" + (vendings.getCurrentVending().getSelectedItem(gui.getSelectedIndexListItems())), "Succesful", 1);
                updateInventoryView();
            }
            else 
                popMessage("Enter An Integer Greater than 0", "Invalid Input", 0);

            gui.setAttributesFunction(false);
            gui.clearListSelected();
        }
        else if (e.getActionCommand().equals("Update")) {
            price = convertToInt(gui.getTfNewPrice());

            if (price > 0) {
                vendings.getCurrentVending().updatePrice(gui.getSelectedIndexListItems(), price);
                popMessage("Successfully Updated The Price Of:\n" + (vendings.getCurrentVending().getInventory().getSlot().get(gui.getSelectedIndexListItems())), "Succesful", 1);
                updateInventoryView();
            }
            else
                popMessage("Enter An Integer Greater than 0", "Invalid Input", 0);
            
            gui.setAttributesFunction(false);
            gui.clearListSelected();
        }
        else if (e.getActionCommand().equals("Collect")) {
            Money collected = vendings.getCurrentVending().getMoney();
            if (gui.getCheckSure()) {
                if (collected.getTotalAmount() > 0) {
                    popMessage("Money (" + collected.getTotalAmount() + ") was collected", "Collect oney Successful", 1);
                    vendings.getCurrentVending().getMoney().resetMoney();
                    gui.setTxtMoney(collected.getTotalAmount());
                }
                else
                    popMessage("Nothing To Collect.", "Unsuccessful", 0);
            }
            else
                popMessage("Check The Verification Box First.", "Collect Money Unsuccessful", 0);
        }
        else if (e.getActionCommand().equals("Add Item")) {
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
                    if (cost > 0 && newQty >= 0 && calories > 0) {
                        vendings.getCurrentVending().getInventory().addItem(name, calories, cost, newQty);
                        gui.setListItems(vendings.getCurrentVending().getInventory());
                        popMessage(name + " with " + calories + " calories is successfully added!", "Add Item Successful", 1);
                    }
                    else
                        popMessage("Invalid input. Enter integers greater than 0.", "Add Item Unsuccesful", 0);
                }
                    
            }
            else 
                popMessage("Complete All The Fields", "Add Item Unsuccesful", 0);
        }
        else if (e.getActionCommand().equals("ADD")) {
            if (insert.getTotalAmount() == 0)
                popMessage("You need to add any denominations first!", "Inserting Unsuccessful", 0);
            else {
                vendings.getCurrentVending().getMoney().updateDenominations(insert);
                popMessage("Money (" + (insert.getTotalAmount())  + " Pesos) Successfully Added!", "Inserting Successful", 1);
                insert.resetMoney();
                gui.setLabelAmount(insert.getTotalAmount());
                gui.setTxtMoney(vendings.getCurrentVending().getMoney().getTotalAmount());
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

    public void popMessage(String message, String title, int type) {
        if (type == 1)
            JOptionPane.showMessageDialog(null, message, title, type, new ImageIcon("src/images/iconVM.png"));
        else 
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);

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
    
}
