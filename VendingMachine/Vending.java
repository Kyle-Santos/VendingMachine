import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents a vending machine with inventory, transaction history, and current money.
 */
public class Vending {
    private String name;
    private Inventory inventory;
    private TransactionList history;
    private Money money;

    /**
     * Constructs a new Vending instance with default name.
     */
    public Vending() {
        this.name = "No name";
        this.inventory = new Inventory();
        this.history = new TransactionList();
        this.money = new Money();
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
    }

    // Maintenance methods

    /**
     * Adds a new item to the vending machine.
     */
    public void addNewItem() {
        Scanner sc = new Scanner(System.in);
        String name = "";
        int quantity = 0, cost = 0;
        double calories = 0;

        System.out.print("\nWhat is the name of the new item? ");
        name = sc.nextLine();

        do {
            try {
                System.out.print("\nWhat is the quantity? ");
                quantity = sc.nextInt();

                System.out.print("\nWhat is the price? ");
                cost = sc.nextInt();

                System.out.print("\nWhat is the amount of calories? ");
                calories = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("\nEnter a valid input.");
                sc.nextLine();
            }
        } while (quantity <= 0 || cost <= 0 || calories <= 0);

        inventory.addItem(new Item(name, calories, cost, quantity));
    }

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

    /**
     * Replenishes the money in the vending machine.
     */
    public void replenishMoney() {
        chooseInsert(this.money);
    }

    /**
     * Provides options for inserting money into the vending machine.
     *
     * @param insert the Money object where the inserted money will be stored
     */
    public void chooseInsert(Money insert) {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        String choice;
        while (!ok) {
            System.out.println("\nTotal inserted: " + insert.getTotalAmount());
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
    }

    /**
     * Handles the process of buying an item from the vending machine.
     */
    public void buy() {
        Scanner sc = new Scanner(System.in);
        Money insert = new Money();
        Item i = new Item("none", 0);
        String y;
        int input = 0, quantity = 0;

        do {
            try {
                listItems();
                System.out.print("What Item Do You Wanna Buy? ");
                input = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nEnter a Valid Input.");
                sc.nextLine();
            }

            try {
                System.out.print("\nHow many are you buying? ");
                quantity = sc.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("\nEnter a Valid Input.");
                sc.nextLine();
            }
        } while (input <= 0 || quantity <= 0);

        chooseInsert(insert);
        sc.nextLine();

        System.out.print("\nAre you sure about buying the item (Y/N)? ");
        y = sc.nextLine();

        if (y.equalsIgnoreCase("Y")) {
            boolean success = false;
            Money change = new Money();
            int totalCost;
            i = inventory.getSlot().get(input - 1);
            if (!success) {
                totalCost = i.getCost() * quantity;
                if (insert.getTotalAmount() <= totalCost)
                    System.out.println("\nMoney inserted is insufficient. Total cost is ₱" + totalCost);
                else if (i.getQuantity() <= quantity) {
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

            if (success) {
                System.out.println("\nProcessing the amount... \nBuying...");
                printSold(i.getName(), quantity, change);
                System.out.println("Buying Sucessful....\nDispensing Item/s Now......\n");
            }
            else
                System.out.println("\nYour Money\n" + insert.listDenominations() +
                        "is being returned...\n\nFailed to buy.");
        }
        else
            System.out.print("\nYour Money\n" + insert.listDenominations() + "is returned to you\n");

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
}


