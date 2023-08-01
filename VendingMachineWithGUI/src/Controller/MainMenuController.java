package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.Vending;
import Model.VendingCollection;
import View.MainMenuGUI;
import View.MaintenanceGUI;
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
        ArrayList<Vending> vms = vendings.getVendings();

        if (e.getActionCommand().equals("Regular")) {
            vendings.createVending(gui.getTfVendingName());
            //JOptionPane.showMessageDialog(null, "Vending Machine " + gui.getTfVendingName() + "\nSuccessfully Created!", "Creation Successful", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/images/iconVM.png"));
            gui.addVMList(gui.getTfVendingName());
        }
        else if (e.getActionCommand().equals("List Available VMs")) {
            JOptionPane.showMessageDialog(null, vendings.listVendings(), "Vending Machines", JOptionPane.PLAIN_MESSAGE);
        }
        else if (selected >= 0) {
            if (e.getActionCommand().equals("Features")) {
                vendings.setCurrent(selected);
                VendingFeatureGUI feature = new VendingFeatureGUI();

                new VendingFeatureController(feature, vendings);
            }
            else {
                vendings.setCurrent(selected);
                MaintenanceGUI maintenance = new MaintenanceGUI();

                new MaintenanceController(maintenance, vendings);
            }
        }
        
    }
    
}
