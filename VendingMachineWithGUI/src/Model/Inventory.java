package Model;

import java.util.ArrayList;

/**
 * This class represents the Inventory, which consists of different items.
 */
public class Inventory {
    private ArrayList<Item> slot; // Slot for items in the inventory

    /**
     * Constructor for Inventory. Initializes an ArrayList of items.
     */
    public Inventory() {
        slot = new ArrayList<Item>();

        // Add initial items to the inventory
        slot.add(new Item("Plain Nori", 30.35, 21, 30, "Nori"));
        slot.add(new Item("Spicy Nori", 30.35, 25, 30, "Nori"));
        slot.add(new Item("Wasabi Nori", 30.35, 29, 30, "Nori"));
        slot.add(new Item("Exquisite Nori", 30.35, 40, 30, "Nori"));
        slot.add(new Item("Sushi Rice", 354, 50, 30, "Rice"));
        slot.add(new Item("Black Rice", 375, 65, 30, "Rice"));
        slot.add(new Item("Brown Rice", 365, 60, 30, "Rice"));
        slot.add(new Item("Prawn", 85, 156, 30, "Topping"));
        slot.add(new Item("Crab Stick", 190, 75, 30, "Topping"));
        slot.add(new Item("Cucumber", 88, 88, 30, "Topping"));
        slot.add(new Item("Sashimi", 256, 264, 30, "Topping"));
        slot.add(new Item("Mango", 300, 53, 30, "Topping"));
        slot.add(new Item("Wasabi", 31, 72, 30, "Topping"));
    }

    /**
     * Overrides the toString method. It provides a formatted string of all the items in the inventory.
     *
     * @return A formatted string representation of the items in the inventory.
     */
    @Override
    public String toString() {
        String s = "";
        int j = 1;

        for (Item i : slot) {
            s += "["+j+"] " + i + "\n";
            j++;
        }
        return s;
    }

    /**
     * Adds a new item to the inventory.
     *
     * @param item The item to be added to the inventory.
     */
    public void addItem(String name, double calories, int cost, int qty, String type) {
        slot.add(new Item(name, calories, cost, qty, type));
        System.out.println("Item added successfully!");
    }

    /**
     * Getter method for the slots.
     *
     * @return An ArrayList of items present in the inventory.
     */
    public ArrayList<Item> getSlot() {
        return this.slot;
    }
}
