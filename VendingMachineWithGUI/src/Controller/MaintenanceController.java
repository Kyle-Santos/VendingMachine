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
        int currInsert = insert.getTotalAmount();
        int qty, price;

        if (e.getActionCommand().equals("Restock Item")) {
            qty = convertToInt(gui.getSpinnerAddQty());
            
            if (qty > 0) {
                vendings.getCurrentVending().restockItem(gui.getSelectedIndexListItems(), qty);
                updateInventoryView();
                popMessage("Successfully Added Quantity to " + vendings.getCurrentVending().getInventory().getSlot().get(gui.getSelectedIndexListItems()).getName() + 
                ",\nIts Quantity is now " + vendings.getCurrentVending().getInventory().getSlot().get(gui.getSelectedIndexListItems()).getQuantity(), "Succesful", 1);
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
                updateInventoryView();
                popMessage("Successfully Updated The Price of " + vendings.getCurrentVending().getInventory().getSlot().get(gui.getSelectedIndexListItems()).getName() + 
                " to " + vendings.getCurrentVending().getInventory().getSlot().get(gui.getSelectedIndexListItems()).getCost() + " Pesos", "Succesful", 1);
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
        JOptionPane.showMessageDialog(null, message, title, type, new ImageIcon("src/images/iconVM.png"));
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
