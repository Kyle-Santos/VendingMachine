import java.util.Scanner;
import java.util.InputMismatchException;

public class Vending {
    private String name;
    private Inventory inventory;
    private TransactionList history;
    private Money money;

    public Vending() {
        this.name = "No name";
        this.inventory = new Inventory();
        this.history = new TransactionList();
        this.money = new Money();
    }

    public Vending(String name) {
        this.name = name;
        this.inventory = new Inventory();
        this.history = new TransactionList();
        this.money = new Money();
    }

    // methods


    // Maintenance
    public void addNewItem(Item item) {
        inventory.addItem(item);
    }

    public void restockItem(String name, int quantity) {
        for (Item i : inventory.getSlot())
            if (name.equals(i.getName())) {
                i.addQuantity(quantity);
                System.out.println("\nRestocking....\nAdding " + quantity + "....\nSucess.\n");
            }
    }

    public void updatePrice(String name, int price) {
        for (Item i : inventory.getSlot())
            if (name.equals(i.getName())) {
                System.out.print("\nItem Found.\nUpdating price....\nSucess.\nFrom " + i.getCost());
                i.setCost(price);
                System.out.println(" to " + price + ".\n");
            }
    }

    public void collectMoney() {
        System.out.println("Collecting money of Vending Machine " + this.name + ".....\nWithdrawing " + money.getTotalAmount() + ".....\nSuccess.\n");
        this.money.setTotalAmount(0);
    }


    public void buy() {

        Scanner sc = new Scanner(System.in);
        Money insert = new Money();
        boolean ok = false;
        String input, choice, y;
        int quantity = 0;

        System.out.print("\nWhat Item Do You Wanna Buy? ");
        input = sc.nextLine();
        sc.nextLine(); 

        try {
            System.out.print("\nHow many are you buying? ");
            quantity = sc.nextInt();
        } 
        catch (InputMismatchException e) {
            sc.nextLine();
        }
        sc.nextLine();

        if (quantity > 0) {
            while (!ok) {
                System.out.println("\nTotal inserted: " + insert.totalAmount);
                System.out.print("\nInsert/Enter Your Money: \n[1] ₱1\t\t[6] ₱100\n[2] ₱5\t\t[7] ₱200\n[3] ₱10\t\t[8] ₱500\n[4] ₱20\t\t[9] ₱1,000\n[5] ₱50\t\t[10] Done\n\n");
                choice = sc.nextLine();
                
                switch (choice) {
                    case "1":
                        System.out.println("You inserted ₱1.");
                        insert.addDenomination(1, 1);
                        break;
                    case "2":
                        System.out.println("You inserted ₱5.");
                        insert.addDenomination(5, 1);
                        break;
                    case "3":
                        System.out.println("You inserted ₱10.");
                        insert.addDenomination(10, 1);
                        break;
                    case "4":
                        System.out.println("You inserted ₱20.");
                        insert.addDenomination(20, 1);
                        break;
                    case "5":
                        System.out.println("You inserted ₱50.");
                        insert.addDenomination(50, 1);
                        break;
                    case "6":
                        System.out.println("You inserted ₱100.");
                        insert.addDenomination(100, 1);
                        break;
                    case "7":
                        System.out.println("You inserted ₱200.");
                        insert.addDenomination(200, 1);
                        break;
                    case "8":
                        System.out.println("You inserted ₱500.");
                        insert.addDenomination(500, 1);
                        break;
                    case "9":
                        System.out.println("You inserted ₱1,000.");
                        insert.addDenomination(1000, 1);
                        break;
                    case "10":
                        ok = true;
                        break;
                    default:
                        System.out.println("Invalid selection.");
                }
            }

            System.out.print("\nAre you sure about buying the item (Y/N)? ");
            y = sc.nextLine();

            if (y.equalsIgnoreCase("Y")) {
                boolean success = false;
                Money change = new Money();
                int totalCost;

                for (Item i : inventory.getSlot()) {
                    if (!success && input.equalsIgnoreCase(i.getName())) {
                        totalCost = i.getCost() * quantity;
                        if (insert.getTotalAmount() <= totalCost)
                            System.out.println("\nMoney inserted is insufficient. Total cost is ₱" + totalCost);
                        else if (i.getQuantity() >= quantity) {
                            System.out.println("\nThe quantity in the machine is not enough. Only " +  i.getQuantity());
                        }  
                        else if (this.money.getTotalAmount() >= totalCost) {
                            // quantity, denominations, totalamount
                            this.money.updateDenominations(insert);
                            totalCost = i.buyItem(quantity, insert.getTotalAmount());

                            success = this.money.generateChange(change, totalCost);
                        }
                        else 
                            System.out.println("\nMoney in the machine is not enough to produce change.");

                    }
                }

                if (success) {
                    System.out.println("\nProcessing the amount... \nBuying...");
                    printSold(input, quantity, change);
                    System.out.println("Buying Sucessful....\nDispensing Item/s Now......\n");
                }
                else    
                    System.out.println("\nYour Money \n" + insert.listDenominations() + 
                    "is being returned...\n\nFailed to buy.");
            }
            else 
                System.out.print("\nYour " + insert.getTotalAmount() + " peso is returned to you\n");
        }
        else 
            System.out.println("\nINVALID INPUT.\n");
    }

    // Vending Feautures
    public void printSold(String item, int quantity, Money change) {
        String bought = "\nYou bought " + quantity + " of " + item.toUpperCase() + "\n\nYour Change:\n" + change.listDenominations();
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
        return this.name + " || Cash: " + this.money.getTotalAmount();
    }

    public String getName() {
        return this.name;
    }
}
