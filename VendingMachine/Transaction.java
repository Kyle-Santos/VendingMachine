import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Transaction class represents a purchase transaction.
 * Each transaction has details about what was bought and the time it occurred.
 */
public class Transaction {
    private String bought; // The details of what was bought in the transaction
    private Date date; // The date and time the transaction occurred

    // Formatter to display date in a more readable format
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     * Constructs a new Transaction with the given purchase details.
     * The date of the transaction is set to the current date and time.
     *
     * @param bought the details of what was bought in the transaction
     */
    public Transaction(String bought) {
        this.bought = bought;
        this.date = new Date();
        formatter.format(date);
    }

    /**
     * Returns a string representation of this Transaction, including
     * the details of what was bought and the date of the transaction.
     *
     * @return a string representation of this Transaction
     */
    @Override
    public String toString() {
        return "\n" + bought + "\n==================================\nWhen: " + date + "\n==================================\n";
    }
}
