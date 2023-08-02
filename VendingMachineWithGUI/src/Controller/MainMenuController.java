package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Model.VendingCollection;
import View.MainMenuGUI;
import View.MaintenanceGUI;
import View.VendingFeatureGUI;

/**
 * The MainMenuController class is responsible for handling actions and events
 * from the MainMenuGUI class and coordinating with the VendingCollection model.
 */
public class MainMenuController implements ActionListener {
    private MainMenuGUI gui;
    private VendingCollection vendings;

    /**
     * Constructor to create a MainMenuController.
     *
     * @param gui      The MainMenuGUI instance.
     * @param vendings The VendingCollection instance.
     */
    public MainMenuController(MainMenuGUI gui, VendingCollection vendings) {
        this.gui = gui;
        this.vendings = vendings;

        gui.setActionListener(this);
    }

    /**
     * Handles the actionPerformed event for buttons in the MainMenuGUI.
     *
     * @param e The ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int selected = gui.getSelectedVM() - 1;
        String action = e.getActionCommand();

        if (action.equals("Regular")) {
            if (vendings.checkIfNameExists(gui.getTfVendingName()))
                popMessage("Enter a unique name.", "Creation Unsuccessful", selected);
            else {
                vendings.createVending(gui.getTfVendingName(), 0);
                popMessage("Vending Machine\n" + gui.getTfVendingName() + "\nSuccessfully Created!", "Creation Successful", 1);
                gui.addVMList(gui.getTfVendingName());
            }
        } else if (action.equals("Special")) {
            if (vendings.checkIfNameExists(gui.getTfVendingName()))
                popMessage("Enter a unique name.", "Creation Unsuccessful", selected);
            else {
                vendings.createVending(gui.getTfVendingName(), 1);
                popMessage("Special Vending Machine\n" + gui.getTfVendingName() + "\nSuccessfully Created!", "Creation Successful", 1);
                gui.addVMList(gui.getTfVendingName());
            }
        } else if (action.equals("List Available VMs")) {
            popMessage(vendings.listVendings(), "Vending Machines", 1);
        } else if (action.equals("Exit")) {
            System.exit(0);
        } else if (selected >= 0) {
            vendings.setCurrent(selected);

            if (action.equals("Features")) {
                VendingFeatureGUI feature = new VendingFeatureGUI();
                new VendingFeatureController(feature, vendings.getCurrentVending());
            } else {
                MaintenanceGUI maintenance = new MaintenanceGUI();
                new MaintenanceController(maintenance, vendings.getCurrentVending());
            }
        } else
            popMessage("Select A Vending Machine First.", "Select Vending Machine", 0);
    }

    /**
     * Displays a message dialog box with an optional icon.
     *
     * @param message The message to display.
     * @param title   The title of the dialog box.
     * @param type    The type of the dialog box (1 for info, 0 for error).
     */
    private void popMessage(String message, String title, int type) {
        if (type == 1)
            JOptionPane.showMessageDialog(null, message, title, type, new ImageIcon("src/images/iconVM.png"));
        else
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

}
