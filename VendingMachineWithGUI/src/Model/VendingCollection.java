package Model;

import java.util.ArrayList;

/**
 * The VendingCollection class represents a collection of Vending Machines.
 * It provides methods to create new Vending machines and manage them.
 */
public class VendingCollection {
    private ArrayList<Vending> vendings; // List of Vending machines
    private int current = -1;

    /**
     * Constructs a new VendingCollection with an empty list of Vending machines.
     */
    public VendingCollection() {
        this.vendings = new ArrayList<Vending>();
    }

    /**
     * Creates a new Vending machine.
     * The user will be asked to input the name of the Vending machine.
     */
    public void createVending(String name, int type) {
        if (type == 0)
            vendings.add(new Vending(name));
        else
            vendings.add(new SpecialVending(name));
    }

    /**
     * Lists all the Vending machines in the collection.
     */
    public String listVendings() {
        String vmList = "";
        int ctr = 1;
        vmList += "Vending Machines:\n";
        for (Vending v : vendings) {
            vmList += "(" + ctr + ") " + v;
            if (v instanceof SpecialVending)
                vmList += " (Special)";
            vmList += "\n";
            ctr++;
        }

        return vmList;
    }

    public boolean checkIfNameExists(String name) {
        boolean exists = false;

        for (Vending v : vendings)
            if (v.getName().equals(name))
                exists = true;
        
        return exists;
    }

    public ArrayList<Vending> getVendings() {
        return this.vendings;
    }

    public void setCurrent(int index) {
        this.current = index;
    }

    public Vending getCurrentVending() {
        return this.vendings.get(current);
    }
}
