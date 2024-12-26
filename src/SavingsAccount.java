import java.util.InputMismatchException;
import java.util.Scanner;

public class SavingsAccount extends Account {

    private double interestRate;
    private static String accountType = "Savings Account";

    Client client = new Client();

    SavingsAccount () {}
    SavingsAccount ( int ownedBy, double balance, double interestRate ) {
        super(  balance, ownedBy );
        this.interestRate = interestRate;
        Main.savingsAccounts.add( this );
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate ( double interestRate ) {
        this.interestRate = interestRate;
    }

    public static Scanner scr = new Scanner(System.in);

    public void create ( int clientId ) {
        Client client = new Client();
        Client foundClient = client.findById( clientId );

        if ( foundClient != null ) {
            while ( true ) {
                try {
                    // create account
                    System.out.println("Please enter how much money you want to diposit ");
                    double dipositAmount = scr.nextDouble();
                    scr.nextLine();
                    System.out.println("please enter Interest Rate ");
                    double interestRate = scr.nextDouble();
                    scr.nextLine();

                    SavingsAccount newSavingsAccount = new SavingsAccount( foundClient.getId(), dipositAmount, interestRate );
                    // associate account client
                    foundClient.setAccounts( newSavingsAccount );
                    // associate client to account
                    newSavingsAccount.setOwnedBy(foundClient.getId());
                    break;
                }
                catch ( InputMismatchException e ) {
                    System.out.println("invalid balance please enter a valid double");
                    scr.nextLine();
                }
            }
        } else {
            System.out.println("UNFOUND Client");
        }
    }

    public void show ( int accountId ) {
        SavingsAccount foundAccount = this.findAccountById( accountId );

        if ( foundAccount != null ) {
            Client foundClient = client.findById( foundAccount.getOwnedBy() );
            System.out.println("Account Number : " + foundAccount.getAccountNumber());
            System.out.println("Account Holder : " + foundClient.getFirstName() + " " + foundClient.getLastName());
            System.out.println("Account Type : " + SavingsAccount.accountType);
            System.out.println("Balance : " + foundAccount.getBalance());
            System.out.println("Bank Charges : " + foundAccount.getInterestRate());
        } else {
            System.out.println("UNFOUND Account");
        }
    }

    public SavingsAccount findAccountById ( int accountId ) {
        for ( SavingsAccount account : Main.savingsAccounts ) {
            if ( account.getId() == accountId ) {
                return account;
            }
        }
        return null;
    }
}
