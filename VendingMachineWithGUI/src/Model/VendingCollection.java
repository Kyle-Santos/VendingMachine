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
    public void createVending(String name) {
        vendings.add(new Vending(name));
        System.out.print("\nVending Machine Successfully Created.\n");
    }

    /**
     * Lists all the Vending machines in the collection.
     */
    public String listVendings() {
        String vmList = "";
        int ctr = 1;
        vmList += "Regular Vending Machines:\n";
        for (Vending v : vendings) {
            vmList += "(" + ctr + ") " + v + "\n";
            ctr++;
        }

        return vmList;
    }

    public ArrayList<Vending> getVendings() {
        return this.vendings;
    }

    public int getCurrent() {
        return this.current;
    }

    public void setCurrent(int index) {
        this.current = index;
    }

    public Vending getCurrentVending() {
        return this.vendings.get(current);
    }
}
