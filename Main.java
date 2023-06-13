import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Item> itemSlot = new ArrayList<Item>();

        Vending test = new Vending();;
        Item soda = new Item("Soda", 44, 10, 40);
        Item fishcake = new Item("Fishcake", 201, 10, 60);
        
        itemSlot.add(soda);
        itemSlot.add(fishcake);

        System.out.println("(1) Create a Vending Machine\n(2) Test a Vending Machine\n(3) Exit\n");
        String input = sc.nextLine();

        System.out.println(itemSlot);

        switch(input)
        {
            case "1":
                System.out.println("\n[Vending Machines]\n(1) Regular\n(2) Special\n");
                break;
            case "2":
                String f;
                do {
                    System.out.println("\n[Functions of Vending]\n(1) Add Item\n(2) Buy Item\n");
                    f = sc.nextLine();

                    switch (f) {
                        case "1":
                            test.addItem("Soda", 44, 10, 40);
                            test.addItem("Fishcake", 201, 10, 60);
                            break;
                        case "2":
                            String result = test.buy("Soda", 6, 500);
                            System.out.println(result);
                            break;
                        case "3":
                            test.listItems();
                            break;
                    }
                } while (f != "3");
                break;
            case "3":
                break;
            default:
                System.out.println("\nEnter valid input.");
        }

        sc.close();
    }
}