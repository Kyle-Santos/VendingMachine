public class Vending {
    private String name;
    private Inventory inventory;
    // date created
    private Money money;

    public Vending() {
        this.name = "No name";
        this.inventory = new Inventory();
        this.money = new Money(1000);
    }

    public Vending(String name) {
        this.name = name;
        this.inventory = new Inventory();
        this.money = new Money(1000);
    }

    // methods
    public void addItem(String name, double calories, int cost, int quantity) {
        Item temp = new Item(name, calories, cost, quantity);
        slot.add(temp);
        System.out.println("Item added successfully!");
    }

    public String buy(String item, int quantity, int money) {
        Money change = new Money(money);

        for (Item i : slot) {
            if (i.getName().equalsIgnoreCase(item) && i.getQuantity() >= quantity) {
                this.money.addMoney(change.getAmount());
                change.setAmount(i.buyItem(quantity, money));
                this.money.deductMoney(change.getAmount());
            }
        }

        // generate different denominations such as 100, 50, 20, 10, 5 peso
        return change.generateChange();
    }

    // public String generateChange(int money) {
    //     int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
    //     String change = "|";
    //     int count;

    //     for (int i = 0; i < denominations.length; i++) {
    //         if (money >= denominations[i]) {
    //             count = money / denominations[i];
    //             change += "| " + count + " of " + denominations[i] + " peso |";
    //             money -= count * denominations[i];
    //         }
    //     }

    //     change += "|";
    //     return change;      
    // }

    public void listItems() {
        System.out.println("\n==| " + name + " |==\n");
        for (Item i : slot) {
            System.out.println("---" + i.getName() + "---" + "\nCost: " + i.getCost() + 
            "\nQuantity Available: " + i.getQuantity() + "\nCalories: " + i.getCalories() + "\n");
        }
    }

    public String toString() {
        return this.name + " || Cash: " + money.getAmount();
    }
}
