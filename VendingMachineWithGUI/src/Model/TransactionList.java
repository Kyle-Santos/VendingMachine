package Model;

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
     * Returns a string representation of the transaction history.
     *
     * @return A string containing the details of all transactions made in the vending machine.
     */
    public String listTransaction() {
        String list = "The Transaction History of This Vending Machine\n";
        for (Transaction t : this.transactions)
            list += t;

        return list;
    }

    /**
     * This method creates a new transaction and adds it to the list.
     *
     * @param bought A string detailing what was bought in the transaction.
     */
    public void createTransaction(String bought) {
        Transaction t = new Transaction(bought);
        this.transactions.add(t);
    }
}
