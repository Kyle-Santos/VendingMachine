import java.util.ArrayList;

public class TransactionList {
    ArrayList<Transaction> transactions;

    public TransactionList() {
        this.transactions = new ArrayList<Transaction>();
    }

    public void listTransaction() {
        for (Transaction t : this.transactions) 
            System.out.println(t);
    }

    public void createTransaction(String bought) {
        Transaction t = new Transaction(bought);
        this.transactions.add(t);
        System.out.println(t);
    }
}
