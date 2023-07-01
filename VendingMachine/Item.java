/**
 * Represents an item with name, cost, quantity, and calories.
 */
public class Item {
    //private final int id;
    private String name;
    private int quantity, cost;
    private final double calories;

    /**
     * Constructs a new item with the specified name and calories.
     * The initial cost and quantity of the item are set to 0.
     *
     * @param name     the name of the item
     * @param calories the calories of the item
     */
    public Item(String name, double calories) {
        this.name = name;
        this.calories = calories;
        cost = 0;
        quantity = 0;
    }

    /**
     * Constructs a new item with the specified name, calories, cost and quantity.
     *
     * @param name     the name of the item
     * @param calories the calories of the item
     * @param cost     the cost of the item
     * @param quantity the quantity of the item
     */
    public Item(String name, double calories, int cost, int quantity) {
        this.name = name;
        this.calories = calories;
        this.cost = cost;
        this.quantity = quantity;
    }

    /**
     * Buys the specified quantity of this item with the specified amount of money.
     * The quantity of the item is reduced accordingly.
     *
     * @param quantity the quantity of the item to buy
     * @param money    the amount of money used to buy the item
     * @return the change after buying the item
     */
    public int buyItem(int quantity, int money) {
        int change = money - (quantity * this.cost);
        setQuantity(this.getQuantity() - quantity);

        return change;
    }

    /**
     * Adds the specified quantity to the existing quantity of this item.
     *
     * @param quantity the quantity to add
     */
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    /**
     * Returns a string representation of this item.
     *
     * @return a string representation of this item
     */
    @Override
    public String toString() {
        return name + " | Calories: " + calories + " | Quantity: " + quantity + " | Cost: Php " + cost;
    }

    /**
     * Returns the name of this item.
     *
     * @return the name of this item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the quantity of this item.
     *
     * @return the quantity of this item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the calories of this item.
     *
     * @return the calories of this item
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Returns the cost of this item.
     *
     * @return the cost of this item
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets the name of this item.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the cost of this item.
     *
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Sets the quantity of this item.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


