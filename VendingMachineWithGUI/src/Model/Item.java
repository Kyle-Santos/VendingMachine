package Model;

/**
 * Represents an item with name, calories, and type.
 */
public class Item {
    private String name;
    private final double calories;
    private String type;

    /**
     * Constructs an empty Item with default values for name, calories, and type.
     */
    public Item() {
        this.name = "";
        this.calories = 0;
        this.type = "";
    }

    /**
     * Constructs an Item with the specified name, calories, and type.
     *
     * @param name     The name of the item.
     * @param calories The number of calories in the item.
     * @param type     The type of the item.
     */

    public Item(String name, double calories, String type) {
        this.name = name;
        this.calories = calories;
        this.type = type;
    }

    /**
     * Returns a string representation of this item.
     *
     * @return a string representation of this item
     */
    @Override
    public String toString() {
        return name + " | Calories: " + calories;
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
     * Returns the calories of this item.
     *
     * @return the calories of this item
     */
    public double getCalories() {
        return calories;
    }


    /**
     * Returns the type of this item.
     *
     * @return the type of this item
     */
    public String getType() {
        return this.type;
    }
}


