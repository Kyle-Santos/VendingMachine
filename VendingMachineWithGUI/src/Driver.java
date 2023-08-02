import Controller.MainMenuController;
import Model.VendingCollection;
import View.MainMenuGUI;

/**
 * The main class that starts the Vending Machine Simulator.
 * This class is used to drive the VendingCollection, MainMenuGUI, and provide a graphical user menu for users.
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
    }

}
