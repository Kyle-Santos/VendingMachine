package Model;

import java.util.ArrayList;


/**
 * The Slot class represents a slot in the vending machine that holds a specific item with quantity and cost.
 */
public class Slot {
    private ArrayList<Item> stock; // keep track of the stock vending machine ArrayList
    private int cost;
    private int quantity; // to keep track of the quantity in stock

    /**
     * Constructs a new Slot with the given item, quantity, and cost.
     *
     * @param item     The item to be stocked in this slot.
     * @param quantity The initial quantity of the item in this slot.
     * @param cost     The cost of the item in this slot.
     */
    public Slot(Item item, int quantity, int cost) {
        this.stock = new ArrayList<Item>();
        this.quantity = quantity;
        this.cost = cost;

        loadStock(item, quantity);
    }

    /**
     * Loads stock into the slot by adding items with the specified quantity.
     *
     * @param item     The item to be stocked.
     * @param quantity The quantity of the item to be added to the slot.
     */
    private void loadStock(Item item, int quantity) {
        // Add stock to the slot
        for (int i = 0; i < quantity; i++) 
            stock.add(new Item(item.getName(), item.getCalories(), item.getType()));
    }

    /**
     * Buys a specified quantity of the item from the slot and returns the change.
     *
     * @param quantity The quantity of the item to be bought.
     * @param money    The amount of money inserted for the purchase.
     * @return The change to be returned to the buyer.
     */

    public int buyItem(int quantity, int money) {
        int change = money - (quantity * cost);
        deductQuantity(quantity);
        
        return change;
    }

    /**
     * Deducts a specified quantity from the slot.
     *
     * @param quantity The quantity to be deducted from the slot.
     */
    public void deductQuantity(int quantity) {
        int itemRemove;

        for (int i = 0; i < quantity; i++) {
            itemRemove = this.quantity - 1;
            stock.remove(itemRemove);
            this.quantity -= 1;
        }
    }

    /**
     * Adds a specified quantity of the item to the slot.
     *
     * @param item     The item to be added to the slot.
     * @param quantity The quantity of the item to be added.
     */
    public void addQuantity(Item item, int quantity) {
        this.quantity += quantity;

        for (int i = 0; i < quantity; i++) 
            stock.add(new Item(item.getName(), item.getCalories(), item.getType()));
    }

    /**
     * Returns a string representation of the item in slot.
     *
     * @return A string representation of the slot in the format "name | Calories: calories | Quantity: quantity | Cost: cost".
     */
    @Override
    public String toString() {
        return stock.get(0).getName() + " | Calories: " + stock.get(0).getCalories() 
        + " | Cost: " + cost + " | Quantity: " + quantity;
    }


    /**
     * Returns the list of stock in the slot.
     *
     * @return The list of stock in the slot.
     */
    public ArrayList<Item> getStock() {
        return this.stock;
    }

    /**
     * Returns the quantity of the item in the slot.
     *
     * @return The quantity of the item in the slot.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Returns the cost of the item in the slot.
     *
     * @return The cost of the item in the slot.
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Sets the cost of this item.
     *
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }
}
