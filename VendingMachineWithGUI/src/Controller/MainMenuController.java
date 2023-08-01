package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Model.SpecialVending;
import Model.VendingCollection;
import View.MainMenuGUI;
import View.MaintenanceGUI;
import View.SpecialFeatureGUI;
import View.VendingFeatureGUI;


public class MainMenuController implements ActionListener { 
    private MainMenuGUI gui;
    private VendingCollection vendings;

    public MainMenuController(MainMenuGUI gui, VendingCollection vendings) {
        this.gui = gui;
        this.vendings = vendings;

        gui.setActionListener(this);
    }

    public void updateView() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selected = gui.getSelectedVM() - 1;

        if (e.getActionCommand().equals("Regular")) {
            if (vendings.checkIfNameExists(gui.getTfVendingName()))
                popMessage("Enter a unique name.", "Creation Unsuccessful", selected);
            else {
                vendings.createVending(gui.getTfVendingName(), 0);
                popMessage("Vending Machine\n" + gui.getTfVendingName() + "\nSuccessfully Created!", "Creation Successful", 1);
                gui.addVMList(gui.getTfVendingName());
            }
        }
        else if (e.getActionCommand().equals("Special")) {
            if (vendings.checkIfNameExists(gui.getTfVendingName()))
                popMessage("Enter a unique name.", "Creation Unsuccessful", selected);
            else {
                vendings.createVending(gui.getTfVendingName(), 1);
                popMessage("Special Vending Machine\n" + gui.getTfVendingName() + "\nSuccessfully Created!", "Creation Successful", 1);
                gui.addVMList(gui.getTfVendingName());
            }
        }
        else if (e.getActionCommand().equals("List Available VMs")) {
            popMessage(vendings.listVendings(), "Vending Machines", 1);
        }
        else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
        else if (selected >= 0) {
            if (e.getActionCommand().equals("Features")) {
                vendings.setCurrent(selected);

                if (vendings.getCurrentVending() instanceof SpecialVending) {
                    SpecialVending specialVend = (SpecialVending) vendings.getCurrentVending();
                    SpecialFeatureGUI specialFeature = new SpecialFeatureGUI();
                    new SpecialFeatureController(specialFeature, specialVend);
                }
                else {
                    VendingFeatureGUI feature = new VendingFeatureGUI();
                    new VendingFeatureController(feature, vendings);
                }
            }
            else {
                vendings.setCurrent(selected);
                MaintenanceGUI maintenance = new MaintenanceGUI();

                new MaintenanceController(maintenance, vendings);
            }
        }
        else
            popMessage("Select A Vending Machine First.", "Select Vending Machine", 0);
        
    }

    public void popMessage(String message, String title, int type) {
        if (type == 1)
            JOptionPane.showMessageDialog(null, message, title, type, new ImageIcon("src/images/iconVM.png"));
        else 
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);

    }
    
}
