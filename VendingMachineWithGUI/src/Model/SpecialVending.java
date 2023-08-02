package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Special Vending Machine, a subclass of Vending, with additional features to customize sushi.
 */
public class SpecialVending extends Vending {
    private HashMap<String, Integer> customize;
    private int totalCostSushi;

    /**
     * Constructs a new SpecialVending instance with the given name.
     *
     * @param name the name of the SpecialVending machine
     */
    public SpecialVending(String name) {
        super(name);

        customize = new HashMap<String, Integer>();
        totalCostSushi = 0;
    }

    /**
     * Allows the user to choose main ingredients for the sushi.
     *
     * @param nori the name of the nori (seaweed) ingredient
     * @param rice the name of the rice ingredient
     * @return true if the main ingredients are successfully chosen, false otherwise
     */
    public boolean chooseMainItem(String nori, String rice) {
        int searchNori = searchItem(nori);
        int searchRice = searchItem(rice);
        boolean success = false;

        for (Item i : items)
            if (i.getType().equals("Nori") || i.getType().equals("Rice"))
                customize.remove(i.getName());

        if (slots.get(searchNori).getQuantity() >= 1 && slots.get(searchRice).getQuantity() >= 1) {
            customize.put(nori, 1);
            customize.put(rice, 1);
            success = true;
        }

        return success;
    }

    /**
     * Adds a topping with the specified name and quantity to the sushi customization.
     *
     * @param name     the name of the topping
     * @param quantity the quantity of the topping to add
     * @return true if the topping is successfully added, false otherwise
     */
    public boolean addTopping(String name, int quantity) {
        boolean success = false;
        int item = searchItem(name);
        
        if (slots.get(item).getQuantity() > quantity) {
            if (customize.containsKey(name)) {
                if (slots.get(item).getQuantity() >= quantity + customize.get(name)) {
                    customize.put(name, customize.get(name) + quantity);
                    success = true;
                }
            }
            else {
                customize.put(name, quantity);
                success = true;
            }
        }

        return success;
    }

    /**
     * Loads the steps required to prepare the customized sushi.
     *
     * @return an ArrayList of strings representing the preparation steps
     */
    public ArrayList<String> loadSteps() {
        ArrayList<String> steps = new ArrayList<String>();
        Item search = null;

        steps.add("Preparing Ingredients...");
        steps.add("Preparing Equipments...");
        for (Map.Entry<String, Integer> e : customize.entrySet()) {
            search = items.get(searchItem(e.getKey()));
            if (search.getType().equals("Nori")) {
                steps.add("Spreading out " + search.getName() + "...");
            }
            else if (search.getType().equals("Rice")) {
                steps.add("Steaming " + search.getName() + "...");
            }
        }

        for (Map.Entry<String, Integer> e : customize.entrySet()) {
            search = items.get(searchItem(e.getKey())); 
            if (search.getType().equals("Topping"))
                steps.add("Preparing " + search.getName() + "...");
        }

        steps.add("Combining all up...");
        steps.add("Rolling the nori...");
        steps.add("Slicing up to small rolls...");
        steps.add("Your sushi is ready!");

        return steps;
    }

    /**
     * Updates the inventory after customizing the sushi.
     */
    public void updateInventory() {
        int search = -1;

        for (Map.Entry<String, Integer> e : customize.entrySet()) {
            search = searchItem(e.getKey());
            slots.get(search).deductQuantity(e.getValue());
        }
    }

    /**
     * Searches for an item with the specified name in the inventory.
     *
     * @param name the name of the item to search for
     * @return the index of the item if found, -1 otherwise
     */
    public int searchItem(String name) {
        for (Item i : items)
            if (i.getName().equalsIgnoreCase(name))
                return items.indexOf(i);
        
        return -1;
    }

    /**
     * Generates a formatted list of customized sushi items along with the total cost and calories.
     *
     * @return a string representation of the customized sushi list, including the total cost and calories
     */
    public String getListItems() {
        String list = "";
        double totalCalories = 0;
        int search;
        totalCostSushi = 0;

        for (Map.Entry<String, Integer> e : customize.entrySet()) {
            list += e.getKey() + ": " + e.getValue() + "\n";
            search = searchItem(e.getKey());
            totalCostSushi += slots.get(search).getCost() * e.getValue();
            totalCalories += items.get(search).getCalories() * e.getValue();
        }

        list += "\nAmount: " + totalCostSushi;
        list += "\nCalories: " + totalCalories;

        return list;
    }

    /**
     * Returns the total cost of the customized sushi.
     *
     * @return the total cost of the customized sushi
     */
    public int getTotalCostSushi() {
        return this.totalCostSushi;
    }

    /**
     * Clears the customization of the sushi.
     */
    public void clearCustomize() {
        this.customize.clear();
        this.totalCostSushi = 0;
    }
}
