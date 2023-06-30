public class Vending {
    private String name;
    private Inventory inventory;
    private TransactionList history;
    private Money money;

    public Vending() {
        this.name = "No name";
        this.inventory = new Inventory();
        this.history = new TransactionList();
        this.money = new Money(1000);
    }

    public Vending(String name) {
        this.name = name;
        this.inventory = new Inventory();
        this.history = new TransactionList();
        this.money = new Money(1000);
    }

    // methods
    public void addNewItem(Item item) {
        inventory.addItem(item);
    }

    public void buy(String item, int quantity, int money) {
        boolean success = false;
        Money change = new Money(money);

        for (Item i : inventory.getSlot()) {
            if (i.getName().equalsIgnoreCase(item) && (i.getQuantity() >= 
            quantity && i.getCost() * quantity <= money) && !success) {
                success = true;
                this.money.addMoney(change.getAmount());
                change.setAmount(i.buyItem(quantity, money));
                this.money.deductMoney(change.getAmount());
            }
        }

        System.out.println("\nProcessing the amount... \nBuying...");

        if (success)
            printSold(item, quantity, change);
        else    
            System.out.println("\nYour Money " + change.getAmount() + 
            " peso is returned.\nFailed to buy due to insufficient amount.");
    }

    // generate different denominations such as 100, 50, 20, 10, 5 peso
    public String generateChange(Money amount) {
        int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
        String change = "\n";
        int count;

        for (int i = 0; i < denominations.length; i++) {
            if (amount.getAmount() >= denominations[i]) {
                count = amount.getAmount() / denominations[i];
                change += count + " of " + denominations[i] + " peso\n";
                amount.deductMoney(count * denominations[i]);
            }
        }

        return change;      
    }

    public void printSold(String item, int quantity, Money change) {
        String bought = "\nYou bought " + quantity + " of " + item.toUpperCase() + "\n\nYour Change:\n" + generateChange(change);
        history.createTransaction(bought);
    }

    public void listItems() {
        System.out.println("\n==| " + name + " |==\n");
        for (Item i : inventory.getSlot()) {
            System.out.println("---" + i.getName() + "---" + "\nCost: " + i.getCost() + 
            "\nQuantity Available: " + i.getQuantity() + "\nCalories: " + i.getCalories() + "\n");
        }
    }

    @Override
    public String toString() {
        return this.name + " || Cash: " + money.getAmount();
    }
}
