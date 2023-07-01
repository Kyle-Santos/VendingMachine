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
        slot.add(new Item("Nori (5g)", 30.35, 21, 30));
        slot.add(new Item("Salmon (100g)", 117, 300, 30));
        slot.add(new Item("Crab Stick (200g)", 190, 75, 30));
        slot.add(new Item("Cucumber (500g)", 88, 88, 30));
        slot.add(new Item("Sushi Rice (500g)", 354, 50, 30));
        slot.add(new Item("Tuna Saku Bar (200g)", 256, 264, 30));
        slot.add(new Item("Mango", 300, 53, 30));
        slot.add(new Item("Wasabi (43g)", 31, 72, 30));
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
    public void addItem(Item item) {
        slot.add(item);
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
