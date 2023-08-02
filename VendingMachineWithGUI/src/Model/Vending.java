package Model;

import java.util.ArrayList;

/**
 * The Vending class represents a vending machine with a name, slots, transaction history, inserted money, and current money.
 */
public class Vending {
    protected String name; 
    protected ArrayList<Item> items; // keep track of the items vending machine ArrayList
    protected ArrayList<Slot> slots; // store items in each slots
    protected TransactionList history;
    protected Money money, insertedMoney;

    /**
     * Constructs a new Vending instance with default name.
     */
    public Vending() {
        this.name = "No name";
        this.slots = new ArrayList<Slot>();
        this.history = new TransactionList();
        this.money = new Money();
        this.insertedMoney = new Money();
        this.items = new ArrayList<Item>();

        loadDefaultItems();
    }

    /**
     * Constructs a new Vending instance with specified name.
     *
     * @param name the name of the vending machine
     */
    public Vending(String name) {
        this.name = name;
        this.slots = new ArrayList<Slot>();
        this.history = new TransactionList();
        this.money = new Money();
        this.insertedMoney = new Money();
        this.items = new ArrayList<Item>();

        loadDefaultItems();
    }

    /**
     * Loads default items into the vending machine slots.
     */
    private void loadDefaultItems() {
        items.add(new Item("Plain Nori", 30.35, "Nori"));
        items.add(new Item("Spicy Nori", 30.35, "Nori"));
        items.add(new Item("Wasabi Nori", 30.35, "Nori"));
        items.add(new Item("Exquisite Nori", 30.35, "Nori"));
        items.add(new Item("Sushi Rice", 354, "Rice"));
        items.add(new Item("Black Rice", 375, "Rice"));
        items.add(new Item("Brown Rice", 365, "Rice"));
        items.add(new Item("Prawn", 85, "Topping"));
        items.add(new Item("Crab Stick", 190, "Topping"));
        items.add(new Item("Cucumber", 60, "Topping"));
        items.add(new Item("Sashimi", 176, "Topping"));
        items.add(new Item("Mango", 120, "Topping"));
        items.add(new Item("Wasabi", 31, "Topping"));

        slots.add(new Slot(items.get(0), 30, 21));
        slots.add(new Slot(items.get(1), 30, 25));
        slots.add(new Slot(items.get(2), 30, 29));
        slots.add(new Slot(items.get(3), 30, 40));
        slots.add(new Slot(items.get(4), 30, 50));
        slots.add(new Slot(items.get(5), 30, 65));
        slots.add(new Slot(items.get(6), 30, 60));
        slots.add(new Slot(items.get(7), 30, 156));
        slots.add(new Slot(items.get(8), 30, 75));
        slots.add(new Slot(items.get(9), 30, 88));
        slots.add(new Slot(items.get(10), 30, 264));
        slots.add(new Slot(items.get(11), 30, 33));
        slots.add(new Slot(items.get(12), 30, 72));
    }


    // Maintenance methods

    /**
     * Restocks an item at a specific index with a specified quantity.
     *
     * @param index    the index of the item
     * @param quantity the quantity to be added
     */
    public void restockItem(int index, int quantity) {
        slots.get(index).addQuantity(items.get(index), quantity);
    }

    /**
     * Adds a new item to the vending machine inventory.
     *
     * @param name     the name of the new item
     * @param calories the calories of the new item
     * @param cost     the cost of the new item
     * @param qty      the quantity of the new item
     * @param type     the type of the new item
     */
    public void addItem(String name, double calories, int cost, int qty, String type) {
        Item i = new Item(name, calories, type);
        items.add(i);
        slots.add(new Slot(i , qty, cost));
    }

    /**
     * Updates the price of an item at a specific index.
     *
     * @param index the index of the item
     * @param price the new price
     */
    public void updatePrice(int index, int price) {
        slots.get(index).setCost(price);
    }

    /**
     * Collects money from the vending machine and resets the money to 0.
     */
    public void collectMoney() {
        Money reset = new Money();
        this.money = reset;
        this.money.generateChange(money, this.money.getTotalAmount());
    }

    
    // Vending features

    /**
     * Prints the details of the sold item.
     *
     * @param item     the name of the sold item
     * @param quantity the quantity of the sold item
     * @param change   the change returned to the buyer
     */
    public String printSold(String item, int quantity, Money change) {
        String bought = "\nYou bought " + quantity + " of " + item.toUpperCase() + "\n\nYour Change:\n" + change.listDenominations();
        history.createTransaction(bought);

        return bought;
    }

    /**
     * Returns a string representation of the vending machine.
     *
     * @return a string representation of the vending machine
     */
    @Override
    public String toString() {
        return this.name + " || Cash: " + this.money.getTotalAmount();
    }

    /**
     * Returns the name of the vending machine.
     *
     * @return the name of the vending machine
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the slots of the vending machine.
     *
     * @return the slots of the vending machine
     */
    public ArrayList<Slot> getSlots() {
        return this.slots;
    }

    /**
     * Returns the items of the vending machine.
     *
     * @return the items of the vending machine
     */
    public ArrayList<Item> getItems() {
        return this.items;
    }

    /**
     * Returns the current money available in the vending machine.
     *
     * @return the current money available in the vending machine
     */
    public Money getMoney()
    {
        return this.money;
    }

    /**
     * Returns the money that has been inserted into the vending machine.
     *
     * @return the money that has been inserted into the vending machine
     */
    public Money getInsertedMoney() {
        return this.insertedMoney;
    }

    /**
     * Returns the selected item at the specified index in the vending machine slots.
     *
     * @param index the index of the item in the slots
     * @return the selected item if found, null otherwise
     */
    public Item getSelectedItem(int index) {
        return items.get(index);
    }

    /**
     * Returns the string representation of the item at the specified index in the vending machine slots.
     *
     * @param index the index of the item in the slots
     * @return the string representation of the item if found, null otherwise
     */
    public String getItemInfo(int index) {
        return this.slots.get(index).toString();
    }

    /**
     * Returns the transaction history of the vending machine.
     *
     * @return the transaction history of the vending machine
     */
    public String getTransactionList() {
        return history.listTransaction();
    }
}


