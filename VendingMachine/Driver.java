import java.util.*;

public class Driver {
    public static void main(String[] args) {
        //VendingGUI gui = new VendingGUI();
        Scanner sc = new Scanner(System.in);
        List<Vending> regVendings = new ArrayList<Vending>();
        //List<Vending> specVendings = new ArrayList<Vending>();

        String input, f, g;

        do {
            System.out.println("\n(1) Create a Vending Machine\n(2) Test a Vending Machine\n(3) List of Vending Machines\n(4) Exit\n");
            input = sc.nextLine();

            switch(input)
            {
                case "1":
                    System.out.println("\n[Vending Machines]\n(1) Regular\n(2) Special\n");
                    f = sc.nextLine();

                    switch (f) {
                        case "1":
                            regVendings.add(new Vending("Kyle's Vending"));
                            break;
                        
                        // not yet implemented
                        case "2":
                            break;

                        default:
                            System.out.println("\nFailed to Create.\n");
                    }
                    break;

                case "2":
                    do {
                        System.out.println("\n[Functions of Vending]\n(1) Add Item\n(2) Buy Item\n(3) List Available Items\n(4) Exit\n");
                        f = sc.nextLine();

                        switch (f) {
                            case "1":
                                // regVendings.get(0).addItem("Soda", 44, 40, 10);
                                // regVendings.get(0).addItem("Fishcake", 201, 60, 10);
                                break;
                            case "2":
                                regVendings.get(0).listItems();
                                System.out.print("\nWhat Item Do You Wanna Buy?\n>> ");
                                g = sc.nextLine();

                                regVendings.get(0).buy(g, 6, 500);
                                break;
                            case "3":
                                regVendings.get(0).listItems();
                                break;
                            case "4":
                                break;
                            default:
                                System.out.println("\nEnter valid input.");
                        }
                    } while (!f.equals("4"));
                    break;

                case "3":
                    System.out.println("\nRegular Vending Machines:\n");
                    for (Vending v : regVendings) {
                        System.out.println(v);
                    }
                    break;    
                
                case "4":
                    System.out.println("\nThank You For Using the Program!\n");
                    break;
                default:
                    System.out.println("\nEnter valid input.");
            }
        } while(!input.equals("4"));

        sc.close();
    }
}
