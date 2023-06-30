public class Item {
    //private final int id;
    private String name;
    private int quantity, cost;
    private final double calories;

    public Item(String name, double calories) {
        this.name = name;
        this.calories = calories;
        cost = 0;
        quantity = 0;
    }

    public Item(String name, double calories, int cost) {
        this.name = name;
        this.calories = calories;
        this.quantity = 0;
        this.cost = cost;
    }

    public Item(String name, double calories, int cost, int quantity) {
        this.name = name;
        this.calories = calories;
        this.cost = cost;
        this.quantity = quantity;
    }


    /* methods */
    public int buyItem(int quantity, int money) {
        int change = money - (quantity * this.cost); 
        setQuantity(this.getQuantity() - quantity);

        return change;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return name + "\nCalories: " + calories + "\nQuantity: " + quantity + "\nCost: Php " + cost;
    }


    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCalories() {
        return calories;
    }

    public int getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}