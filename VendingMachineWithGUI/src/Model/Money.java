package Model;

import java.util.HashMap;

/**
 * Represents money with total amount and denominations.
 */
public class Money {
    private int totalAmount;
    private HashMap<Integer, Integer> denominations;

    /**
     * Constructs a new Money instance with zero total amount and
     * initialized denominations.
     */
    public Money() {
        this.denominations = new HashMap<Integer, Integer>();

        denominations.put(100, 0);
        denominations.put(50, 0);
        denominations.put(20, 0);
        denominations.put(10, 0);
        denominations.put(5, 0);
        denominations.put(1, 0);

        this.totalAmount = 0;
    }

    /**
     * Update denominations with the specified money.
     *
     * @param pay the Money object to update denominations
     */
    public void updateDenominations(Money pay) {
        int[] denominations = {100, 50, 20, 10, 5, 1};

        for (int i = 0; i < denominations.length; i++)
            if (pay.getDenominations().get(denominations[i]) != 0)
                addDenomination(denominations[i], pay.getDenominations().get(denominations[i]));

        //totalAmount += pay.getTotalAmount();
    }

    /**
     * Generates change with the specified money and payment.
     *
     * @param change the Money object to hold the change
     * @param pay    the payment amount
     * @return       true if change is generated successfully, false otherwise
     */
    public boolean generateChange(Money change, int pay) {
        int[] denominations = {100, 50, 20, 10, 5, 1};
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

    /**
     * Lists the denominations.
     *
     * @return a string representation of denominations
     */
    public String listDenominations() {
        int[] denoms = {100, 50, 20, 10, 5, 1};
        int count;
        String listing = "";

        for (int i = 0; i < denoms.length; i++) {
            count = this.denominations.get(denoms[i]);
            if (count > 0)
                listing += count + " of ₱" + denoms[i] + "\n";
        }

        return listing;
    }

    /**
     * Deducts the specified value from the specified denomination.
     *
     * @param key   the denomination
     * @param value the value to deduct
     */
    private void deductDenomination(int key, int value) {
        int val = denominations.get(key);
        denominations.replace(key, value - val);
        totalAmount -= key * value;
    }

    /**
     * Adds the specified value to the specified denomination.
     *
     * @param key   the denomination
     * @param value the value to add
     */
    public void addDenomination(int key, int value) {
        int val = denominations.get(key);
        denominations.replace(key, value + val);
        totalAmount += key * value;
    }

    /**
     * Resets the money object to have zero denominations and total amount.
     */
    public void resetMoney() {
        denominations.replace(100, 0);
        denominations.replace(50, 0);
        denominations.replace(20, 0);
        denominations.replace(10, 0);
        denominations.replace(5, 0);
        denominations.replace(1, 0);

        this.totalAmount = 0;
    }

    /**
     * Returns the total amount.
     *
     * @return the total amount
     */
    public int getTotalAmount() {
        return this.totalAmount;
    }

    /**
     * Returns the denominations.
     *
     * @return the denominations
     */
    public HashMap<Integer, Integer> getDenominations() {
        return denominations;
    }

    /**
     * Sets the money object with the specified money.
     *
     * @param money the Money object to set the money from
     */
    public void setMoney(Money money) {
        this.denominations = money.denominations;
        this.totalAmount = money.totalAmount;
    }
}
