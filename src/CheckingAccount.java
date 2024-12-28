import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckingAccount extends Account {


    private double bankCharges;
    public static String accountType = "checking account";

    public static int option;
    public static Scanner scr = new Scanner(System.in);

    static Client client = new Client();

    CheckingAccount ( int ownedBy, double balance, double bankCharges ) {
        super( balance, ownedBy );
        this.bankCharges = bankCharges;
        Main.checkingAccounts.add(this);
    }

    CheckingAccount () {}

    public double getBankCharges () {
        return this.bankCharges;
    }

    public void setBankCharges ( double bankCharges ) {
        this.bankCharges = bankCharges;
    }

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
                     System.out.println("please enter Bank Charges ");
                     double bankCharges = scr.nextDouble();
                     scr.nextLine();

                     CheckingAccount newCheckingAccount = new CheckingAccount( foundClient.getId(), dipositAmount, bankCharges );
                     // associate account client
                     foundClient.setAccounts( newCheckingAccount );
                     // associate client to account
                     //newCheckingAccount.setOwnedBy(foundClient.getId());
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
        CheckingAccount foundAccount = this.findAccountById( accountId );

         if ( foundAccount != null ) {
             Client foundClient = client.findById( foundAccount.getOwnedBy() );
             dislplayAccount( foundAccount, foundClient, false );
         } else {
             System.out.println("UNFOUND Account");
         }
    }

    public void listAccountOperationsHistory ( int accountId ) {
        CheckingAccount foundAccount = this.findAccountById( accountId );

        if ( foundAccount != null ) {
            Client foundClient = client.findById( foundAccount.getOwnedBy() );
            dislplayAccount( foundAccount, foundClient, true );
        } else {
            System.out.println("UNFOUND Account");
        }
    };

    public void dislplayAccount ( CheckingAccount account, Client client, boolean isShowHistory ) {
        Client foundClient = client.findById( account.getOwnedBy() );
        System.out.println("Account Number : " + account.getAccountNumber());
        System.out.println("Account Holder : " + client.getFirstName() + " " + foundClient.getLastName());
        System.out.println("Account Type : " + CheckingAccount.accountType);
        System.out.println("Balance : " + account.getBalance());
        System.out.println("Bank Charges : " + account.getBankCharges());
        if ( isShowHistory ) {
            System.out.println("we are showing history");
            for ( Operation operation : account.getOperationsHistory() ) {
                operation.displayOperation( operation );
            }
        }
    }

    public CheckingAccount findAccountById ( int accountId ) {
        for ( CheckingAccount account : Main.checkingAccounts ) {
            if ( account.getId() == accountId ) {
                return account;
            }
        }
        return null;
    }

    public CheckingAccount findAccountByOwnerId ( int clientId ) {
        for ( CheckingAccount account : Main.checkingAccounts ) {
            if ( account.getOwnedBy() == clientId ) {
                return account;
            }
        }
        return null;
    }

    int subMenu () {
        try {
            System.out.println("please enter one of the options");
            System.out.println("0 => quite Account section");
            System.out.println("1 => list all Accounts");
            System.out.println("2 => list a single Account");
            System.out.println("3 => create a Account");
            System.out.println("4 => show an Account History");
            option = scr.nextInt();
            scr.nextLine();
            while ( option != 0 && option != 1 && option != 2 && option != 3 && option != 4 ) {
                System.out.println("invalid option please choose one of (0,1,2,3,4)");
                option = scr.nextInt();
                scr.nextLine();
            }
            return option;
        }
        catch ( InputMismatchException e ) {
            System.out.println("please enter a valide number (0,1,2,3)");
            scr.nextLine();
            return 5;
        }
    }

}
