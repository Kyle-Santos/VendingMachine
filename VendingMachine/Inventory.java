import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> slot;

    public Inventory() {
        slot = new ArrayList<Item>();

        slot.add(new Item("Nori (5g)", 30.35, 21, 30));
        slot.add(new Item("Salmon (100g)", 117, 300, 30));
        slot.add(new Item("Crab Stick (200g)", 190, 75, 30));
        slot.add(new Item("Cucumber (500g)", 88, 88, 30));
        slot.add(new Item("Sushi Rice (500g)", 354, 50, 30));
        slot.add(new Item("Tuna Saku Bar (200g)", 256, 264, 30));
        slot.add(new Item("Mango", 300, 53, 30));
        slot.add(new Item("Wasabi (43g)", 31, 72, 30));
    }

    public void addItem(Item item) {
        slot.add(item);
        System.out.println("Item added successfully!");
    }

    public ArrayList<Item> getSlot() {
        return this.slot;
    }
}
