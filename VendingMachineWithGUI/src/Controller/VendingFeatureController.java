package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.Item;
import Model.Money;
import Model.VendingCollection;
import View.VendingFeatureGUI;

/**
 * The VendingFeatureController class handles user interactions and events from the VendingFeatureGUI.
 */
public class VendingFeatureController implements ActionListener, ListSelectionListener, ChangeListener { 
    private VendingFeatureGUI gui;
    private VendingCollection vendings;
    private Money insert;

    /**
     * Creates a new VendingFeatureController instance.
     *
     * @param gui      The VendingFeatureGUI to be controlled.
     * @param vendings The VendingCollection model.
     */
    public VendingFeatureController(VendingFeatureGUI gui, VendingCollection vendings) {
        this.gui = gui;
        this.vendings = vendings;
        this.insert = new Money();

        gui.setActionListener(this);
        gui.setListSelectionListener(this);
        gui.setChangeListener(this);

        gui.setListItems(vendings.getCurrentVending().getInventory());
    }

    /**
     * Handles the item purchase process.
     */
    private void buyItem() {
        int quantity = 0;
        boolean success = false;
            
        try {
            quantity = Integer.parseInt(gui.getTfQuantity());
            success = true;
        } catch (NumberFormatException error) {
            popMessage("Enter a valid quantity!", "Buying Unsuccessful", 0);
        }

        if (success) {
            success = false;
            if (quantity > 0) {
                // if user has selected higher quantity than available
                Item itemToBuy = vendings.getCurrentVending().getInventory().getSlot().get(gui.getSelectedItem());
                if (quantity > itemToBuy.getQuantity()) {
                    popMessage("Not enough quantity available to sold.", "Buying Unsuccessful", 0);
                } 
                else {
                    Money change = new Money();
                    // get total cost
                    int totalCost = itemToBuy.getCost() * quantity;

                    // if user entered cost is less than or equal total cost then show message
                    if (vendings.getCurrentVending().getInsertedMoney().getTotalAmount() < totalCost) {
                        popMessage("Money inserted is insufficient. Total cost is ₱" + totalCost, "Buying Unsuccessful", 0);
                    } 
                    else if (vendings.getCurrentVending().getMoney().getTotalAmount() >= totalCost) {
                        vendings.getCurrentVending().getMoney().updateDenominations(insert);
                        totalCost = itemToBuy.buyItem(quantity, insert.getTotalAmount());

                        success = vendings.getCurrentVending().getMoney().generateChange(change, totalCost);

                        if (success) {
                            System.out.println("\nProcessing the amount... \nBuying...");
                            vendings.getCurrentVending().printSold(itemToBuy.getName(), quantity, change);
                            System.out.println("Buying Sucessful....\nDispensing Item/s Now......\n");
                            popMessage("You have bought item successfully.", "Buying Successful", 1);
                            
                        } else {
                            System.out.println("\nYour Money\n" + insert.listDenominations()
                                    + "is being returned...\n\nFailed to buy.");
                            popMessage("Sorry unable to sell this item.", "Buying Unsuccessful", 0);
                        }
                    }
                    else
                        popMessage("Money in the machine is not enough to produce change.", "Buying Unsuccessful", 0);

                    System.out.println(totalCost);
                }

                // update list after purchase
                vendings.getCurrentVending().getInsertedMoney().resetMoney();
                gui.setListItems(vendings.getCurrentVending().getInventory());

            }
            else
                popMessage("Enter a quantity greater than 0!", "Buying Unsuccessful", 0);
        }

        gui.clearListSelected();
        gui.setAttributesFunction(false);
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

    /**
     * Handles the actionPerformed event for buttons in the VendingFeatureGUI.
     *
     * @param e The ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] denominations = {"100", "50", "20", "10", "5", "1"};
        int currInsert = insert.getTotalAmount();

        if (e.getActionCommand().equals("Buy")) {
            buyItem();
        }
        else if (e.getActionCommand().equals("Cancel")) {
            popMessage("Money (" + currInsert + " Pesos) is returned to you.", "Purchasing Cancelled", 1);
            vendings.getCurrentVending().getInsertedMoney().resetMoney();
            gui.dispose();
        }
        else if (e.getActionCommand().equals("ADD")) {
            int vendInsert = vendings.getCurrentVending().getInsertedMoney().getTotalAmount();
            if (currInsert == vendInsert)
                popMessage("You need to add any denominations first!", "Inserting Unsuccessful", 0);
            else {
                vendings.getCurrentVending().getInsertedMoney().setMoney(insert);
                popMessage("Money (" + (currInsert - vendInsert)  + " Pesos) Successfully Added!", "Inserting Successful", 1);
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
            if (gui.getTabbedPane().getSelectedIndex() == 0) {
                insert.setMoney(vendings.getCurrentVending().getInsertedMoney());
                gui.setLabelAmount(insert.getTotalAmount());
            }
            else {
                gui.setAttributesFunction(false);
                gui.clearListSelected();
            }
        }
    }
    
}
