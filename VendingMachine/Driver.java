import java.util.Scanner;

/**
 * This class is used to drive the VendingCollection and provides a simple textual menu for users.
 */
public class Driver {

    /**
     * The main entry point for the application.
     * It provides an interactive menu to perform operations on VendingCollection object.
     * It allows users to create vending machines, test vending machines, list all vending machines and exit the program.
     *
     * @param args Array of string arguments passed to the main method.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //VendingGUI gui = new VendingGUI();
        VendingCollection collection = new VendingCollection();

        String input; // user's input

        // main program loop
        do {
            System.out.println("\n(1) Create a Vending Machine\n(2) Test a Vending Machine\n(3) List of Vending Machines\n(4) Exit\n");
            input = sc.nextLine();

            // handling user's choice
            switch(input)
            {
                case "1":
                    // Creates a new vending machine
                    collection.createVending();
                    break;

                case "2":
                    // Tests a vending machine
                    collection.testVending();
                    break;

                case "3":
                    // Lists all vending machines
                    collection.listVendings();
                    break;

                case "4":
                    // Exits the program
                    System.out.println("\nThank You For Using the Program!\n");
                    break;

                default:
                    // Handles invalid user's input
                    System.out.println("\nEnter valid input.");
            }
        } while(!input.equals("4")); // repeat until the user chooses to exit

        sc.close(); // closing the scanner object
    }

}
