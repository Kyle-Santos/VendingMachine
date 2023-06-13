import java.util.ArrayList;

public class Vending {
    private String name;
    // date created
    private ArrayList<Item> slot;
    private final int maxSlot = 8;
    private final int maxItem = 10;
    private int cash;

    public Vending() {
        this.name = "No name";
        slot = new ArrayList<Item>();
        this.cash = 1000;
    }

    public Vending(String name) {
        this.name = name;
        slot = new ArrayList<Item>();
        this.cash = 1000;
    }

    // methods
    public void addItem(String name, double calories, int quantity, int cost) {
        if (slot.size() < maxSlot) {
            Item temp = new Item(name, calories, quantity, cost);
            slot.add(temp);
            System.out.println("Item added successfully!");
        }
        else {
            System.out.println("Failed!");
        }
    }

    public String buy(String item, int quantity, int money) {
        int change = money;
        for (Item i : slot) {
            if (i.getName().equalsIgnoreCase(item) && i.getQuantity() >= quantity) {
                change = i.buyItem(quantity, money);
            }
        }

        // generate different denominations such as 100, 50, 20, 10, 5 peso
        return generateChange(change);
    }

    public String generateChange(int money) {
        int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
        String change = "|";
        int count;

        for (int i = 0; i < denominations.length; i++) {
            if (money >= denominations[i]) {
                count = money / denominations[i];
                change += "| " + count + " of " + denominations[i] + " peso |";
                money -= count * denominations[i];
            }
        }

        change += "|";
        return change;      
    }

    public void listItems() {
        for (Item i : slot) {
            System.out.println("\n---" + i.getName() + "---" + "\nCost: " + i.getCost() + 
            "\nQuantity Available: " + i.getQuantity() + "\nCalories: " + i.getCalories());
        }
    }
}
