import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String bought;
    private Date date;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  

    public Transaction(String bought) {
        this.bought = bought;
        this.date = new Date();
        formatter.format(date);
    }

    @Override
    public String toString() {
        return "\n" + bought + "\n==================================\nWhen: " + date + "\n==================================\n";
    }
}
