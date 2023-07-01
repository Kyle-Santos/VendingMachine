import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //VendingGUI gui = new VendingGUI();
        VendingCollection collection = new VendingCollection();
        

        String input; // input

        do {
            System.out.println("\n(1) Create a Vending Machine\n(2) Test a Vending Machine\n(3) List of Vending Machines\n(4) Exit\n");
            input = sc.nextLine();

            switch(input)
            {
                case "1":
                    collection.createVending();
                    break;

                case "2":
                    collection.testVending();     
                    break;

                case "3":
                    collection.listVendings();
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
