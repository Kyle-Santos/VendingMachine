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

import Model.Item;
import Model.Money;
import Model.SpecialVending;
import View.PrepareSushiGUI;
import View.SpecialFeatureGUI;

/**
 * The SpecialFeatureController class handles user interactions and events from the SpecialFeatureGUI.
 */
public class SpecialFeatureController implements ActionListener, ListSelectionListener, ChangeListener, ItemListener { 
    private SpecialFeatureGUI gui;
    private SpecialVending vending;
    private Money insert;

    /**
     * Creates a new SpecialFeatureController instance.
     *
     * @param gui     The SpecialFeatureGUI to be controlled.
     * @param vending The SpecialVending model.
     */
    public SpecialFeatureController(SpecialFeatureGUI gui, SpecialVending vending) {
        this.gui = gui;
        this.vending = vending;
        this.insert = new Money();

        gui.setListItems(vending.getInventory());

        gui.setActionListener(this);
        gui.setListSelectionListener(this);
        gui.setChangeListener(this);
        gui.setItemChangeListener(this);
    }

    /**
     * Updates the receipt in the GUI.
     */
    private void updateReceipt() {
        gui.setReceipt(vending.getListItems());
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
        int totalCost = vending.getTotalCostSushi();

        new PrepareSushiGUI(vending);
        // if user entered cost is less than or equal total cost then show message
        if (vending.getInsertedMoney().getTotalAmount() < totalCost) {
            popMessage("Money inserted is insufficient. Total cost is ₱" + totalCost, "Buying Unsuccessful", 0);
        } 
        else if (vending.getMoney().getTotalAmount() >= totalCost) {
            vending.getMoney().updateDenominations(insert);
            totalCost = insert.getTotalAmount() - totalCost; 
            success = vending.getMoney().generateChange(change, totalCost);

            if (success) { 
                new PrepareSushiGUI(vending);
                System.out.println("\nProcessing the amount... \nBuying...");
                System.out.println("Buying Sucessful....\nDispensing Item/s Now......\n");
                popMessage("You have bought item successfully.", "Buying Successful", 1);
                
            } 
            else {
                popMessage("Sorry unable to sell this item.\nYour Money\n" + insert.listDenominations() + 
                "is returned to you.", "Buying Unsuccessful", 0);
            }
        }
        else
            popMessage("Money in the machine is not enough to produce change.", "Buying Unsuccessful", 0);
    

        // update list after purchase
        vending.getInsertedMoney().resetMoney();

    }

    /**
     * Buys an item from the vending machine.
     */
    private void buyItem() {
        boolean success = false;
        Money change = new Money();
        int quantity = convertToInt(gui.getTfQuantity());
        Item itemToBuy = vending.getInventory().getSlot().get(gui.getSelectedItem());
        int totalCost = itemToBuy.getCost() * quantity;

        if (quantity > 0) {
            // if user has selected higher quantity than available
            if (quantity > itemToBuy.getQuantity()) {
                popMessage("Not enough quantity available to sold.", "Buying Unsuccessful", 0);
            } 
            else {
                // if user entered cost is less than or equal total cost then show message
                if (vending.getInsertedMoney().getTotalAmount() < totalCost) {
                    popMessage("Money inserted is insufficient. Total cost is ₱" + totalCost, "Buying Unsuccessful", 0);
                } 
                else if (vending.getMoney().getTotalAmount() >= totalCost) {
                    vending.getMoney().updateDenominations(insert);
                    totalCost = itemToBuy.buyItem(quantity, insert.getTotalAmount());

                    success = vending.getMoney().generateChange(change, totalCost);

                    if (success) {
                        System.out.println("\nProcessing the amount... \nBuying...");
                        vending.printSold(itemToBuy.getName(), quantity, change);
                        System.out.println("Buying Sucessful....\nDispensing Item/s Now......\n");
                        popMessage("You have bought item successfully.", "Buying Successful", 1);
                        
                    } 
                    else {
                        popMessage("Sorry unable to sell this item.\nYour Money\n" + insert.listDenominations() + 
                        "is returned to you.", "Buying Unsuccessful", 0);
                    }
                }
                else
                    popMessage("Money in the machine is not enough to produce change.", "Buying Unsuccessful", 0);
            }

               // update list after purchase
               vending.getInsertedMoney().resetMoney();
               gui.setListItems(vending.getInventory());
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
            System.out.println(quantity);

            if (quantity > 0) {
                if (vending.addTopping(vending.getInventory().getSlot().get(gui.getTopping()).getName(), quantity))
                    updateReceipt();
                else
                    popMessage("Quantity available is not enough.", "Unsuccesful", 0);
            }

            gui.clearListSelected();
            gui.setAttributesFunction(false);
        }
        else if (action.equals("Create")) {
            buyCustomSushi();
            gui.enableCreate(false);
            gui.clearListSelected();
            gui.setListItems(vending.getInventory());
            vending.clearCustomize();
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
            
            gui.setAttributesFunction(false);
            gui.clearListSelected();
            
        }
    }

	@Override
	public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (!gui.getNori().equals("") && !gui.getRice().equals("")) {
                if (!vending.chooseMainItem(gui.getNori(), gui.getRice())) {
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
