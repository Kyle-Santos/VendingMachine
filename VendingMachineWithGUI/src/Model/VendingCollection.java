package Model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The VendingCollection class represents a collection of Vending Machines.
 * It provides methods to create new Vending machines and manage them.
 */
public class VendingCollection {
    private ArrayList<Vending> vendings; // List of Vending machines
    private int current = -1;

    /**
     * Constructs a new VendingCollection with an empty list of Vending machines.
     */
    public VendingCollection() {
        this.vendings = new ArrayList<Vending>();
    }

    /**
     * Creates a new Vending machine.
     * The user will be asked to input the name of the Vending machine.
     */
    public void createVending(String name) {
        vendings.add(new Vending(name));
        System.out.print("\nVending Machine Successfully Created.\n");
    }

    /**
     * Tests a Vending machine by displaying a menu to perform various operations.
     * The user will be asked to input the name of the Vending machine to test.
     */
    public void testVending() {
        Scanner sc = new Scanner(System.in);
        String choice, f, g;
        int n, m, price;
        System.out.print("\nEnter the name of Vending Machine: ");
        f = sc.nextLine();

        for (Vending v : vendings)
            if (f.equalsIgnoreCase(v.getName())) {
                do
                {
                    System.out.print("\n[What do you wanna Test?]\n(1) Vending Features\n(2) Maintenance Features\n(3) Exit\n\nENTER INPUT: ");
                    choice = sc.nextLine();

                    switch (choice) {
                        case "1":
                            do {
                                System.out.println("\n[Vending Features]\n(1) Buy Item\n(2) List Available Items\n(3) Exit\n");
                                g = sc.nextLine();

                                switch (g) {
                                    case "1":
                                        v.buy();
                                        break;
                                    case "2":
                                        v.listItems();
                                        break;
                                    case "3":
                                        break;
                                    default:
                                        System.out.println("\nEnter valid input.");
                                }
                            } while (!g.equals("3"));
                            break;
                        case "2":
                            do {
                                System.out.println("\n[Maintenance Features]\n(1) Restock Specific Item\n(2) Set New Price of an Item\n(3) Collect the Money\n(4) Replenish Money\n(5) Add Item\n(6) Exit\n");
                                g = sc.nextLine();

                                switch (g) {
                                    case "1":
                                        v.listItems();

                                        try {
                                            System.out.print("\nWhat item do you wanna restock? ");
                                            n = sc.nextInt();
                                            System.out.print("\nHow many are you restocking? ");
                                            m = sc.nextInt();
                                        } catch (InputMismatchException e) {
                                            System.out.println("\nEnter Valid Input.");
                                            break;
                                        }

                                        v.restockItem(n - 1, m);
                                        sc.nextLine();
                                        break;
                                    case "2":
                                        v.listItems();

                                        try {
                                            System.out.print("\nWhat item do you wanna set a new price? ");
                                            n = sc.nextInt();
                                            System.out.print("\nWhat should be the new price? ");
                                            price = sc.nextInt();
                                        } catch (InputMismatchException e) {
                                            System.out.println("\nEnter Valid Input.");
                                            break;
                                        }

                                        v.updatePrice(n - 1, price);
                                        break;

                                    case "3":
                                        v.collectMoney();
                                        break;
                                    case "4":
                                        v.replenishMoney();
                                        break;
                                    case "5":
                                        v.addNewItem();
                                        break;
                                    case "6":
                                        break;
                                    default:
                                        System.out.println("\nEnter valid input.");
                                }
                            } while (!g.equals("6"));
                            break;

                        case "3":
                            break;
                        default:
                            System.out.println("\nEnter Valid Input.");
                    }
                } while(!choice.equals("3"));
            }
    }

    /**
     * Lists all the Vending machines in the collection.
     */
    public String listVendings() {
        String vmList = "";
        int ctr = 1;
        vmList += "Regular Vending Machines:\n";
        for (Vending v : vendings) {
            vmList += "(" + ctr + ") " + v + "\n";
            ctr++;
        }

        return vmList;
    }

    public ArrayList<Vending> getVendings() {
        return this.vendings;
    }

    public int getCurrent() {
        return this.current;
    }

    public void setCurrent(int index) {
        this.current = index;
    }

    public Vending getCurrentVending() {
        return this.vendings.get(current);
    }
}
