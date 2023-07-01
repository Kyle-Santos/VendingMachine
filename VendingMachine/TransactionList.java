import java.util.ArrayList;

/**
 * This class represents a list of transactions made on the vending machine.
 */
public class TransactionList {
    ArrayList<Transaction> transactions; // List to store transactions

    /**
     * Default constructor that initializes the transactions list.
     */
    public TransactionList() {
        this.transactions = new ArrayList<Transaction>();
    }

    /**
     * This method prints all the transactions made.
     */
    public void listTransaction() {
        for (Transaction t : this.transactions)
            System.out.println(t);
    }

    /**
     * This method creates a new transaction and adds it to the list.
     *
     * @param bought A string detailing what was bought in the transaction.
     */
    public void createTransaction(String bought) {
        Transaction t = new Transaction(bought);
        this.transactions.add(t);
        System.out.println(t);
    }
}
