import java.util.ArrayList;
import java.util.Random;

public abstract class  Account {
    private static int count = 1;
    private int id;
    private long accountNumber; // this number should be generated automatically and it should be unique
    private double balance;
    private int ownedBy;
    private ArrayList<Operation> operationsHistory = new ArrayList<Operation>();

    Account ( double balance, int ownedBy ) {
        this.id = count;
        this.accountNumber = this.generateAccountNumber();
        this.balance = balance;
        this.ownedBy = ownedBy;
        count++;
    }

    Account () {}



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
    public ArrayList<Operation> getOperationsHistory () {
        return this.operationsHistory;
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

    public void addOperationToHistory ( Operation operation ) {
        this.operationsHistory.add( operation );
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

    public void dislplayAccount ( Account account, Client client, boolean isShowHistory ) {
        Client foundClient = client.findById( account.getOwnedBy() );
        System.out.println("Account Number : " + account.getAccountNumber());
        System.out.println("Account Holder : " + client.getFirstName() + " " + foundClient.getLastName());
        System.out.println("Account Type : " + CheckingAccount.accountType);
        System.out.println("Balance : " + account.getBalance());
        if ( account instanceof CheckingAccount ) {
            CheckingAccount checkingAccount = (CheckingAccount) account;
            System.out.println("Bank Charges: " + checkingAccount.getBankCharges());
        }
        if ( account instanceof SavingsAccount ) {
            SavingsAccount checkingAccount = (SavingsAccount) account;
            System.out.println("Bank Charges: " + checkingAccount.getInterestRate());
        }

        if ( isShowHistory ) {
            System.out.println("we are showing history");
            for ( Operation operation : account.getOperationsHistory() ) {
                operation.displayOperation( operation );
            }
        }
    }

    public abstract void create( int clientId );

    public abstract void show ( int clientId );

    public abstract void listAccountOperationsHistory ( int accountId );

    public abstract  void filterByMinimumBlanace ( ArrayList<Account> accounts );

}
