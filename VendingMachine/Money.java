import java.util.HashMap;
import java.util.Map;

public class Money {
    int totalAmount;
    HashMap<Integer, Integer> denominations;

    public Money() {
        this.denominations = new HashMap<Integer, Integer>();

        denominations.put(1000, 0); 
        denominations.put(500, 0);  
        denominations.put(200, 0); 
        denominations.put(100, 0); 
        denominations.put(50, 0); 
        denominations.put(20, 0); 
        denominations.put(10, 0); 
        denominations.put(5, 0); 
        denominations.put(1, 0);

        this.totalAmount = 0;
    }

    public void updateDenominations(Money pay) {
        int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};

        for (int i = 0; i < denominations.length; i++)
            addDenomination(denominations[i], pay.getDenominations().get(i));

        totalAmount += pay.getTotalAmount();
    }

    public boolean generateChange(Money change, int pay) {
        int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
        int count, vCount;
        boolean success = false;

        for (int i = 0; i < denominations.length; i++) {
            vCount = this.denominations.get(denominations[i]);
            if (pay >= denominations[i] && vCount > 0) {
                count = (pay / denominations[i]);

                if (count > vCount) 
                    count = vCount;
                
                change.addDenomination(denominations[i], count);
                deductDenomination(denominations[i], count);

                pay -= denominations[i] * count;
            }
        }

        if (pay != 0) 
            updateDenominations(change);
        else
            success = true;

        return success;      
    }

    public String listDenominations() {
        int[] denoms = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
        int count;
        String listing = "";

        for (int i = 0; i < denoms.length; i++) {
            count = this.denominations.get(denoms[i]);
            if (count > 0)
                listing += count + " of ₱" + denoms[i] + "\n";
        }

        return listing;
    }

    public void setDenomination(int key, int value) {
        denominations.replace(key, value);
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void deductDenomination(int key, int value) {
        int val = denominations.get(key);
        denominations.replace(key, value - val);
        totalAmount -= key * value;
    }

    public void addDenomination(int key, int value) {
        int val = denominations.get(key);
        denominations.replace(key, value + val);
        totalAmount += key * value;
    }

    public int getTotalAmount() {
        return this.totalAmount;
    }

    public HashMap<Integer, Integer> getDenominations() {
        return denominations;
    }

}
