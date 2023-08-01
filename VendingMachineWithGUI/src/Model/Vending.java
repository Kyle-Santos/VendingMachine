package Model;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents a vending machine with inventory, transaction history, and current money.
 */
public class Vending {
    private String name;
    private Inventory inventory;
    private TransactionList history;
    private Money money, insertedMoney;

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

    /**
     * Replenishes the money in the vending machine.
     */
    public void replenishMoney() {
        	
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

        //chooseInsert(insert);
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

    public Inventory getInventory() {
        return this.inventory;
    }

    public Money getMoney()
    {
        return this.money;
    }

    public Money getInsertedMoney() {
        return this.insertedMoney;
    }

    public Item getSelectedItem(int index) {
        return inventory.getSlot().get(index);
    }
}


