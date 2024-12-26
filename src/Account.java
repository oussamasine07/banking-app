import java.util.Random;

public abstract class  Account {
    private int count = 1;
    private int id;
    private long accountNumber; // this number should be generated automatically and it should be unique
    private double balance;
    private Client ownedBy;

    Account ( double balance ) {
        this.id = count;
        this.accountNumber = this.generateAccountNumber();
        this.balance = balance;
        this.ownedBy = null;
        Main.accounts.add(this);
        count++;
    }

    Account () {}
    // TODO Add overloaded constructor if needed

    public int getId () {
        return this.id;
    }
    public long getAccountNumber () {
        return this.accountNumber;
    }
    public double getBalance () {
        return this.balance;
    }
    public Client getOwnedBy () {
        return this.ownedBy;
    }

    public void setOwnedBy ( Client account ) {
        this.ownedBy = account;
    }

    public long generateAccountNumber () {
        Random random = new Random();
        long foundAccountNumber = Math.abs(random.nextLong());

        // check if account number already exists
        for ( Account account : Main.accounts ) {
            if ( account.accountNumber == foundAccountNumber ) {
                this.generateAccountNumber();
            }
        }
        return foundAccountNumber;
    }

    public abstract void create( int clientId );

    public abstract void show ( int clientId );

}
