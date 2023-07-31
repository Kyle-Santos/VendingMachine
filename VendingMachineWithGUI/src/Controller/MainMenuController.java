package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Model.Vending;
import Model.VendingCollection;
import View.MainMenuGUI;
import View.VendingFeatureGUI;


public class MainMenuController implements ActionListener, DocumentListener { 
    private MainMenuGUI gui;
    private VendingCollection vendings;

    public MainMenuController(MainMenuGUI gui, VendingCollection vendings) {
        this.gui = gui;
        this.vendings = vendings;

        gui.setActionListener(this);
        gui.setDocumentListener(this);
    }

    public void updateView() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Regular")) {
            vendings.createVending(gui.getTfVendingName());
            //JOptionPane.showMessageDialog(null, "Vending Machine " + gui.getTfVendingName() + "\nSuccessfully Created!", "Creation Successful", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/iconVM.png"));
            gui.addVMList(gui.getTfVendingName());
        }
        else if (e.getActionCommand().equals("List Available VMs")) {
            JOptionPane.showMessageDialog(null, vendings.listVendings(), "Vending Machines", JOptionPane.PLAIN_MESSAGE);
        }
        else if (e.getActionCommand().equals("Features")) {
            String selected = gui.getSelectedVM();
            ArrayList<Vending> vms = vendings.getVendings();
            VendingFeatureController vmFeatures;

            for (Vending v : vms) {
                if (v.getName().equals(selected)) {
                    vendings.setCurrent(vms.indexOf(v));
                    VendingFeatureGUI feature = new VendingFeatureGUI();

                    vmFeatures = new VendingFeatureController(feature, vendings);

                    break;
                }
            }
        }
        
    }
    @Override
    public void changedUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void removeUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        
    }

    
}
