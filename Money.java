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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
