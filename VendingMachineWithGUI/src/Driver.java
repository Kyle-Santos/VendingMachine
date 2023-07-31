import Controller.MainMenuController;
import Model.VendingCollection;
import View.MainMenuGUI;

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
        MainMenuGUI gui = new MainMenuGUI();
        VendingCollection collection = new VendingCollection();

        new MainMenuController(gui, collection);

        System.out.println("\nWELCOME TO THE VENDING MACHINE SIMULATOR");
    }

}
