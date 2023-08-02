package Model;

/**
 * Represents a vending machine with inventory, transaction history, and current money.
 */
public class Vending {
    protected String name;
    protected Inventory inventory;
    protected TransactionList history;
    protected Money money, insertedMoney;

    /**
     * Constructs a new Vending instance with default name.
     */
    public Vending() {
        this.name = "No name";
        this.inventory = new Inventory();
        this.history = new TransactionList();
        this.money = new Money();
        this.insertedMoney = new Money();
    }

    /**
     * Constructs a new Vending instance with specified name.
     *
     * @param name the name of the vending machine
     */
    public Vending(String name) {
        this.name = name;
        this.inventory = new Inventory();
        this.history = new TransactionList();
        this.money = new Money();
        this.insertedMoney = new Money();
    }

    // Maintenance methods

    /**
     * Restocks an item at a specific index with a specified quantity.
     *
     * @param index    the index of the item
     * @param quantity the quantity to add
     */
    public void restockItem(int index, int quantity) {
        Item i = inventory.getSlot().get(index);
        i.addQuantity(quantity);
        System.out.println("\nRestocking....\nAdding " + quantity + "....\nSucess.\n");
    }

    /**
     * Updates the price of an item at a specific index.
     *
     * @param index the index of the item
     * @param price the new price
     */
    public void updatePrice(int index, int price) {
        Item i = inventory.getSlot().get(index);
        System.out.print("\nItem Found.\nUpdating price....\nSucess.\nFrom " + i.getCost());
        i.setCost(price);
        System.out.println(" to " + price + ".\n");
    }

    /**
     * Collects money from the vending machine and resets the money to 0.
     */
    public void collectMoney() {
        Money reset = new Money();
        System.out.println("Collecting money of Vending Machine " + this.name + ".....\nWithdrawing " + money.getTotalAmount() + ".....\nSuccess.\n");
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
    public void printSold(String item, int quantity, Money change) {
        String bought = "\nYou bought " + quantity + " of " + item.toUpperCase() + "\n\nYour Change:\n" + change.listDenominations();
        history.createTransaction(bought);
    }

    /**
     * Lists all the items in the vending machine.
     */
    public void listItems() {
        System.out.println("\n=====| " + name + " |=====\n");
        System.out.println(inventory);
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
     * Returns the inventory of the vending machine.
     *
     * @return the inventory of the vending machine
     */
    public Inventory getInventory() {
        return this.inventory;
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
     * Returns the selected item at the specified index in the vending machine inventory.
     *
     * @param index the index of the item in the inventory
     * @return the selected item if found, null otherwise
     */
    public Item getSelectedItem(int index) {
        return inventory.getSlot().get(index);
    }
}


