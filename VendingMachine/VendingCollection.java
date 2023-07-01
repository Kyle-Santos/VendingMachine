import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VendingCollection {
    ArrayList<Vending> regVendings = new ArrayList<Vending>();

    public VendingCollection() {
        this.regVendings = new ArrayList<Vending>();
    }

    public void createVending() {
        Scanner sc = new Scanner(System.in);
        String f;

        System.out.println("\n[Vending Machines]\n(1) Regular\n(2) Special\n");
        f = sc.nextLine();
        switch (f) {
            case "1":
                System.out.print("\nEnter the name of the NEW Vending Machine: ");
                f = sc.nextLine();
                regVendings.add(new Vending(f));
                System.out.print("\nRegular Vending Machine Successfully Created.\n");
                break;
            
            // not yet implemented
            case "2":
                break;

            default:
                System.out.println("\nFailed to Create.\n");
        }
    }

    public void testVending() {
        Scanner sc = new Scanner(System.in);
        String choice, f, g;
        int n, m, price;
        System.out.print("\nEnter the name of Vending Machine: ");
        f = sc.nextLine();

        for (Vending v : regVendings)
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
                            } while (!g.equals("5"));
                            break;

                        case "3":
                            break;
                        default:
                            System.out.println("\nEnter Valid Input.");
                    }
                } while(!choice.equals("3"));
            }
    }

    public void listVendings() {
        System.out.println("\nRegular Vending Machines:\n");
        for (Vending v : regVendings) {
            System.out.println(v);
        }
    }
}
