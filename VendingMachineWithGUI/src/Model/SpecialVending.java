package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpecialVending extends Vending {
    private HashMap<String, Integer> customize;
    private int totalCostSushi;

    public SpecialVending(String name) {
        super(name);

        customize = new HashMap<String, Integer>();
        totalCostSushi = 0;
    }

    public boolean chooseMainItem(String nori, String rice) {
        Item searchNori = searchItem(nori);
        Item searchRice = searchItem(rice);
        boolean success = false;

        for (Item i : inventory.getSlot())
            if (i.getType().equals("Nori") || i.getType().equals("Rice"))
                customize.remove(i.getName());

        if (searchNori.getQuantity() >= 1 && searchRice.getQuantity() >= 1) {
            customize.put(nori, 1);
            customize.put(rice, 1);
            success = true;
        }

        return success;
    }

    public boolean addTopping(String name, int quantity) {
        boolean success = false;
        Item item = searchItem(name);
        
        if (item.getQuantity() > quantity) {
            if (customize.containsKey(name)) {
                if (item.getQuantity() >= quantity + customize.get(name)) {
                    customize.put(name, customize.get(name) + quantity);
                    success = true;
                }
            }
            else {
                customize.put(name, quantity);
                success = true;
            }
        }

        return success;
    }

    public ArrayList<String> loadSteps() {
        ArrayList<String> steps = new ArrayList<String>();
        Item search = null;

        steps.add("Preparing Ingredients...");
        steps.add("Preparing Equipments...");
        for (Map.Entry<String, Integer> e : customize.entrySet()) {
            search = searchItem(e.getKey());
            if (search.getType().equals("Nori")) {
                steps.add("Spreading out " + search.getName() + "...");
            }
            else if (search.getType().equals("Rice")) {
                steps.add("Steaming " + search.getName() + "...");
            }
        }

        for (Map.Entry<String, Integer> e : customize.entrySet()) {
            search = searchItem(e.getKey()); 
            if (search.getType().equals("Topping"))
                steps.add("Preparing " + search.getName() + "...");
        }

        steps.add("Combining all up...");
        steps.add("Rolling the nori...");
        steps.add("Slicing up to small rolls...");
        steps.add("Your sushi is ready!");

        return steps;
    }

    public void updateInventory() {
        Item search = null;

        for (Map.Entry<String, Integer> e : customize.entrySet()) {
            search = searchItem(e.getKey());
            search.setQuantity(search.getQuantity() - e.getValue());
        }
    }

    public Item searchItem(String name) {
        Item search = null;

        for (Item i : inventory.getSlot())
            if (i.getName().equalsIgnoreCase(name))
                return i;
        
        return search;
    }

    public String getListItems() {
        String list = "";
        double totalCalories = 0;
        Item search = null;
        totalCostSushi = 0;

        for (Map.Entry<String, Integer> e : customize.entrySet()) {
            list += e.getKey() + ": " + e.getValue() + "\n";
            search = searchItem(e.getKey());
            totalCostSushi += search.getCost() * e.getValue();
            totalCalories += search.getCalories() * e.getValue();
        }

        list += "\nAmount: " + totalCostSushi;
        list += "\nCalories: " + totalCalories;

        return list;
    }

    public int getTotalCostSushi() {
        return this.totalCostSushi;
    }

    public void clearCustomize() {
        customize.clear();
    }
}
