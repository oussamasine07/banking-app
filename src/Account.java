import java.util.Random;

public abstract class  Account {
    private static int count = 1;
    private int id;
    private long accountNumber; // this number should be generated automatically and it should be unique
    private double balance;
    private int ownedBy;

    Account ( double balance, int ownedBy ) {
        this.id = count;
        this.accountNumber = this.generateAccountNumber();
        this.balance = balance;
        this.ownedBy = ownedBy;
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
    public int getOwnedBy () {
        return this.ownedBy;
    }

    public void setOwnedBy ( int  account ) {
        this.ownedBy = account;
    }

    public void encreaseBalance ( double amount ) {
        this.balance += amount;
    }

    public void decreaseBalance ( double amount ) {
        this.balance -= amount;
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
