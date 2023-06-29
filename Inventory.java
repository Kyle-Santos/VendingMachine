import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> slot;

    public Inventory() {
        slot = new ArrayList<Item>();
    }

    public void loadItems() {
        slot.add(new Item("Nori", 30.35, 21, 30));
        slot.add(new Item("Salmon", 30.35, 21, 30));
        slot.add(new Item("Nori", 30.35, 21, 30));
        slot.add(new Item("Nori", 30.35, 21, 30));
        slot.add(new Item("Nori", 30.35, 21, 30));
        slot.add(new Item("Nori", 30.35, 21, 30));
        slot.add(new Item("Nori", 30.35, 21, 30));
        slot.add(new Item("Nori", 30.35, 21, 30));
    }
}
