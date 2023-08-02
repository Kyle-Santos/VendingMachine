package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.Money;
import Model.Slot;
import Model.SpecialVending;
import Model.Vending;
import View.PrepareSushiGUI;
import View.VendingFeatureGUI;

/**
 * The VendingFeatureController class handles user interactions and events from the VendingFeatureGUI.
 */
public class VendingFeatureController implements ActionListener, ListSelectionListener, ChangeListener, ItemListener { 
    private VendingFeatureGUI gui;
    private Vending vending;
    private SpecialVending specialVending;
    private Money insert;

    /**
     * Creates a new VendingFeatureController instance.
     *
     * @param gui     The VendingFeatureGUI to be controlled.
     * @param vending The Vending model.
     */
    public VendingFeatureController(VendingFeatureGUI gui, Vending vending) {
        this.gui = gui;
        this.vending = vending;
        this.insert = new Money();

        // if vending machine is a special vm, enables the feature of special vending machine
        if (vending instanceof SpecialVending) {
            this.specialVending = (SpecialVending) this.vending;
            gui.enableCustomizePanel();
            gui.setComboBoxes(vending.getSlots());
            gui.setItemChangeListener(this);
        }   

        gui.setListItems(vending.getSlots());

        gui.setActionListener(this);
        gui.setListSelectionListener(this);
        gui.setChangeListener(this);
    }

    /**
     * Updates the receipt in the GUI.
     */
    private void updateReceipt() {
        gui.setReceipt(specialVending.getListItems());
    }

    /**
     * Converts a string to an integer.
     *
     * @param convert The string to convert.
     * @return The converted integer value, or 0 if the conversion fails.
     */
    private int convertToInt(String convert) {
        try {
            return Integer.parseInt(convert);
        } catch (NumberFormatException error) {
            popMessage("Enter a valid Integer!", "Error: Unsuccessful", 0);
            return 0;
        }
    }

    /**
     * Buys a custom sushi from the vending machine.
     */
    private void buyCustomSushi() {
        boolean success = false;
        Money change = new Money();
        int totalCost = specialVending.getTotalCostSushi();

        // if user entered cost is less than or equal total cost then show message
        if (vending.getInsertedMoney().getTotalAmount() < totalCost) {
            popMessage("Money inserted is insufficient. \nTotal cost is ₱" + totalCost + "\n\nYour Money\n" + 
            insert.listDenominations() + "is returned to you.", "Buying Unsuccessful", 0);
        } 
        else if (vending.getMoney().getTotalAmount() >= totalCost || totalCost == insert.getTotalAmount()) {
            vending.getMoney().updateDenominations(insert);
            totalCost = insert.getTotalAmount() - totalCost; 
            success = vending.getMoney().generateChange(change, totalCost);

            if (success) { 
                new PrepareSushiGUI(specialVending);
                popMessage("You have bought item successfully.\n" + (vending.printSold("Specially For You Sushi", 1, change)), "Buying Successful", 1);
                specialVending.updateInventory();
            } 
            else {
                vending.getMoney().returnMoney(insert);
                popMessage("Sorry unable to sell this item.\nYour Money\n" + insert.listDenominations() + 
                "is returned to you.", "Buying Unsuccessful", 0);
            }
        }
        else
            popMessage("Money in the machine is not\nenough to produce change.\n\nYour Money\n" + 
            insert.listDenominations() + "is returned to you.", "Buying Unsuccessful", 0);
    

        // update list after purchase
        vending.getInsertedMoney().resetMoney();
        gui.setListItems(vending.getSlots());

        // update inserted money view 
        insert.resetMoney();
        gui.setLabelAmount(insert.getTotalAmount());

    }

    /**
     * Buys an item from the vending machine.
     */
    private void buyItem() {
        boolean success = false;
        Money change = new Money();
        int quantity = convertToInt(gui.getTfQuantity());
        Slot itemToBuy = vending.getSlots().get(gui.getSelectedItem());
        String itemName = vending.getItems().get(gui.getSelectedItem()).getName();
        int totalCost = itemToBuy.getCost() * quantity; // get total cost

        if (quantity > 0) {
            // if user has selected higher quantity than available
            if (quantity > itemToBuy.getQuantity()) {
                popMessage("Not enough quantity available to sold.", "Buying Unsuccessful", 0);
            } 
            else {
                // if user entered cost is less than or equal total cost then show message
                if (vending.getInsertedMoney().getTotalAmount() < totalCost) {
                    popMessage("Money inserted is insufficient. \nTotal cost is ₱" + totalCost + "\n\nYour Money\n" + 
                    insert.listDenominations() + "is returned to you.", "Buying Unsuccessful", 0);
                } 
                else if (vending.getMoney().getTotalAmount() >= totalCost || totalCost == insert.getTotalAmount()) {
                    vending.getMoney().updateDenominations(insert);
                    totalCost = itemToBuy.buyItem(quantity, insert.getTotalAmount());

                    success = vending.getMoney().generateChange(change, totalCost);

                    if (success) {
                        popMessage("You have bought item successfully.\n" + (vending.printSold(itemName, quantity, change)), "Buying Successful", 1);
                    } 
                    else {
                        vending.getMoney().returnMoney(insert);
                        popMessage("Sorry unable to sell this item.\n\nYour Money\n" + insert.listDenominations() + 
                        "is returned to you.", "Buying Unsuccessful", 0);
                    }
                }
                else
                    popMessage("Money in the machine is not\nenough to produce change.\n\nYour Money\n" + 
                    insert.listDenominations() + "is returned to you.", "Buying Unsuccessful", 0);
            }

                // update list after purchase
                vending.getInsertedMoney().resetMoney();
                gui.setListItems(vending.getSlots());

                // update inserted money view 
                insert.resetMoney();
                gui.setLabelAmount(insert.getTotalAmount());
        }
        else
            popMessage("Enter a quantity greater than 0!", "Buying Unsuccessful", 0);
    }

    /**
     * Handles the actionPerformed event for buttons in the SpecialFeatureGUI.
     *
     * @param e The ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] denominations = {"100", "50", "20", "10", "5", "1"};
        int currInsert = insert.getTotalAmount();
        String action = e.getActionCommand();
        int quantity = -1;

        if (action.equals("Add")) {
            quantity = convertToInt(gui.getTfQtyCustom());

            if (quantity > 0) {
                if (specialVending.addTopping(vending.getItems().get(gui.getTopping()).getName(), quantity))
                    updateReceipt();
                else
                    popMessage("Quantity available is not enough.", "Unsuccesful", 0);
            }

            gui.clearListSelected();
            gui.setAttributesFunction(false);
        }
        else if (action.equals("Create")) {
            buyCustomSushi();
            gui.clearListSelected();
            gui.setListItems(vending.getSlots());
            specialVending.clearCustomize();
            gui.enableCreate(false);
            updateReceipt();
        }
        else if (action.equals("Buy")) {
            buyItem();
            gui.clearListSelected();
            gui.setAttributesFunction(false);
        }
        else if (action.equals("Cancel")) {
            popMessage("Money (" + currInsert + " Pesos) is returned to you.", "Purchasing Cancelled", 1);
            vending.getInsertedMoney().resetMoney();
            gui.dispose();
        }
        else if (action.equals("ADD")) {
            int vendInsert = vending.getInsertedMoney().getTotalAmount();
            if (currInsert == vendInsert)
                popMessage("You need to add any denominations first!", "Inserting Unsuccessful", 0);
            else {
                vending.getInsertedMoney().setMoney(insert);
                popMessage("Money (" + (currInsert - vendInsert)  + " Pesos) Successfully Added!", "Inserting Successful", 1);
            }
        } 
        else {
            for (String d : denominations) {
                if (action.equals(d)) {
                    insert.addDenomination(Integer.parseInt(d), 1);
                    gui.setLabelAmount(insert.getTotalAmount());
                    break;
                }
            }
        }
    }

    /**
     * Displays a message dialog box with an optional icon.
     *
     * @param message The message to display.
     * @param title   The title of the dialog box.
     * @param type    The type of the dialog box (1 for info, 0 for error).
     */
    public void popMessage(String message, String title, int type) {
        if (type == 1)
            JOptionPane.showMessageDialog(null, message, title, type, new ImageIcon("src/images/iconVM.png"));
        else 
            JOptionPane.showMessageDialog(null, message, title, type);

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            if (gui.getSelectedIndexListItems() != 0)
                gui.setAttributesFunction(true);
            else
                gui.setAttributesFunction(false);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == gui.getTabbedPane()) {
            if (gui.getTabbedPane().getSelectedIndex() != 2) {
                insert.setMoney(vending.getInsertedMoney());
                gui.setLabelAmount(insert.getTotalAmount());
            }

            if (vending instanceof SpecialVending) {
                if (gui.getTabbedPane().getSelectedIndex() != 1) {
                    specialVending.clearCustomize();
                    gui.setSelectedMainItem();
                    updateReceipt();
                }
            }
            
            gui.setAttributesFunction(false);
            gui.clearListSelected();
            
        }
    }

	@Override
	public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (!gui.getNori().equals("") && !gui.getRice().equals("")) {
                if (!specialVending.chooseMainItem(gui.getNori(), gui.getRice())) {
                    popMessage("Quantity available is not enough.", "Unsuccessful", 0);
                    gui.setSelectedMainItem();
                    gui.enableCreate(false);
                }
                else 
                    gui.enableCreate(true);

                updateReceipt();
            }
        }
	}
    
}
