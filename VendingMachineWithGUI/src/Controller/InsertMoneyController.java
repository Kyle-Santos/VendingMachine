package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Model.Money;
import Model.Vending;
import Model.VendingCollection;
import View.InsertMoneyGUI;

public class InsertMoneyController implements ActionListener, DocumentListener { 
    private InsertMoneyGUI gui;
    private VendingCollection vendings;
    private Money insert;

    public InsertMoneyController(InsertMoneyGUI gui, VendingCollection vendings) {
        this.gui = gui;
        this.vendings = vendings;
        this.insert = new Money();

        gui.setActionListener(this);
        gui.setDocumentListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] denominations = {"100", "50", "20", "10", "5", "1"};
        for (String d : denominations) {
            if (e.getActionCommand().equals(d)) {
                ArrayList<Vending> vms = vendings.getVendings();
                Vending v = vms.get(vendings.getCurrent()); 
                insert.addDenomination(Integer.parseInt(d), 1);
                gui.setLabelAmount(insert.getTotalAmount());
                break;
            }
        }

        if (e.getActionCommand().equals("ADD")) {
            gui.setVisible(false);
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

    public Money getInsert() {
        return this.insert;
    }

    
}
