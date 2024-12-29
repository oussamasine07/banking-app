import java.util.*;

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
        System.out.println("********************************************");
        System.out.println("************ New Saving Account ************");
        System.out.println("********************************************");
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
                    Main.accounts.add( newSavingsAccount );
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
        System.out.println("********************************************");
    }

    public void show ( int accountId ) {
        System.out.println("********************************************");
        System.out.println("************** Saving Account **************");
        System.out.println("********************************************");
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
        System.out.println("********************************************");
    }

    public void listAccountOperationsHistory ( int accountId ) {
        System.out.println("********************************************");
        System.out.println("************* Account History **************");
        System.out.println("********************************************");
        SavingsAccount foundAccount = this.findAccountById( accountId );

        if ( foundAccount != null ) {
            Client foundClient = client.findById( foundAccount.getOwnedBy() );
            dislplayAccount( foundAccount, foundClient, true );
        } else {
            System.out.println("UNFOUND Account");
        }
        System.out.println("********************************************");
    };

    public SavingsAccount findAccountById ( int accountId ) {
        for ( SavingsAccount account : Main.savingsAccounts ) {
            if ( account.getId() == accountId ) {
                return account;
            }
        }
        return null;
    }

    public void filterByMinimumBlanace (ArrayList<Account> accounts ) {
        System.out.println("********************************************");
        System.out.println("************* Minimum Balance **************");
        System.out.println("********************************************");
        Account account = accounts.stream()
                .min(Comparator.comparing(Account::getBalance))
                .orElseThrow(NoSuchElementException::new);

        Client owner = client.findById( account.getOwnedBy() );

        this.dislplayAccount( account, owner, false );
        System.out.println("********************************************");
    }

}
