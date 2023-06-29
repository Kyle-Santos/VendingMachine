import java.util.*;

public class Scan {
    Scanner sc = new Scanner(System.in);
    int i;
    double d;
    String s;

    public Scan() {
        
    }

    public String str() {
        return sc.nextLine();
    }

    public int integer() {
        try {
            return sc.nextInt();
        } catch (java.util.InputMismatchException mismatchException) {
            return 0;
        }
    }
}