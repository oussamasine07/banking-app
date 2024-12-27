import java.time.LocalDateTime;

public class Operation {
    static int counter = 1;
    public int id;
    public LocalDateTime currentDate;
    public double amount;
    public Account associatedAccount;

    Operation () {}

    Operation ( double amount, Account associatedAccount ) {
        this.id = counter;
        this.amount = amount;
        this.currentDate = LocalDateTime.now();
        this.associatedAccount = associatedAccount;
        Main.operations.add(this);
        counter++;
    }

}
