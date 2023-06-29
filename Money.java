public class Money {
    int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public void addMoney(int money) {
        this.amount += money;
    }

    public void deductMoney(int money) {
        this.amount -= money;
    }

    public String generateChange() {
        int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
        String change = "|";
        int count;

        for (int i = 0; i < denominations.length; i++) {
            if (amount >= denominations[i]) {
                count = amount / denominations[i];
                change += "| " + count + " of " + denominations[i] + " peso |";
                amount -= count * denominations[i];
            }
        }

        change += "|";
        return change;      
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
