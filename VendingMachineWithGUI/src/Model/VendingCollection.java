package Model;

import java.util.ArrayList;

/**
 * The VendingCollection class represents a collection of Vending Machines.
 * It provides methods to create new Vending machines and manage them.
 */
public class VendingCollection {
    private ArrayList<Vending> vendings; // List of Vending machines
    private int current;

    /**
     * Constructs a new VendingCollection with an empty list of Vending machines.
     */
    public VendingCollection() {
        this.vendings = new ArrayList<Vending>();
        current = -1;
    }

    /**
     * Creates a new vending machine and adds it to the list of vending machines.
     *
     * @param name The name of the vending machine to be created.
     * @param type The type of the vending machine to be created (0 for regular vending machine, 1 for special vending machine).
     */
    public void createVending(String name, int type) {
        if (type == 0)
            vendings.add(new Vending(name));
        else
            vendings.add(new SpecialVending(name));
    }

    /**
     * Retrieves a formatted list of all the vending machines.
     *
     * @return A String containing the list of vending machines with their names and types.
     *         Each vending machine is listed with a number in parentheses.
     *         If a vending machine is of type SpecialVending, it is marked as "(Special)".
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

    /**
     * Checks if a Vending machine with the given name already exists in the collection.
     *
     * @param name the name to check for existence
     * @return true if a Vending machine with the given name exists, false otherwise
     */
    public boolean checkIfNameExists(String name) {
        boolean exists = false;

        for (Vending v : vendings)
            if (v.getName().equals(name))
                exists = true;
        
        return exists;
    }

    /**
     * Returns the list of all Vending machines in the collection.
     *
     * @return the list of all Vending machines in the collection
     */
    public ArrayList<Vending> getVendings() {
        return this.vendings;
    }

    /**
     * Returns the currently selected Vending machine in the collection.
     *
     * @return the currently selected Vending machine if one is selected, null otherwise
     */
    public Vending getCurrentVending() {
        return this.vendings.get(current);
    }

    /**
     * Sets the current Vending machine index to the specified index in the collection.
     *
     * @param index the index of the current Vending machine
     */
    public void setCurrent(int index) {
        this.current = index;
    }
}
