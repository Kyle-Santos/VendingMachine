import java.util.ArrayList;
import java.util.Scanner;

public class VendingCollection {
    ArrayList<Vending> regVendings = new ArrayList<Vending>();

    public VendingCollection() {
        this.regVendings = new ArrayList<Vending>();
    }

    public void createVending() {
        String f;
        Scanner sc = new Scanner(System.in);

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
        String choice, f, g;
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter the name of Vending Machine: ");
        f = sc.nextLine();

        for (Vending v : regVendings)
            if (f.equalsIgnoreCase(v.getName())) {
                do 
                {
                    System.out.print("\n[What do you wanna Test?]\n(1) Vending Features\n(2) Maintenance Features\n(3) Exit\n\nENTER INPUT: ");
                    choice = sc.nextLine(); 

                    switch (choice) {
                        
                    }
                } while(!choice.equals("3"));
                do {
                    System.out.println("\n[Functions of Vending]\n(1) Add Item\n(2) Buy Item\n(3) List Available Items\n(4) Exit\n");
                    g = sc.nextLine();

                    switch (g) {
                        case "1":
                            // regVendings.get(0).addItem("Soda", 44, 40, 10);
                            // regVendings.get(0).addItem("Fishcake", 201, 60, 10);
                            break;
                        case "2":
                            v.buy();
                            break;

                        case "3":
                            v.listItems();
                            break;
                        case "4":
                            break;
                        default:
                            System.out.println("\nEnter valid input.");
                    }
                } while (!g.equals("4"));

            }

        sc.close();
    }

    public void listVendings() {
        System.out.println("\nRegular Vending Machines:\n");
        for (Vending v : regVendings) {
            System.out.println(v);
        }
    }
}
