import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static ArrayList<Client> clients = new ArrayList<Client>();
    static ArrayList<Account> accounts = new ArrayList<Account>();
    static ArrayList<CheckingAccount> checkingAccounts = new ArrayList<CheckingAccount>();
    static ArrayList<SavingsAccount> savingsAccounts = new ArrayList<SavingsAccount>();
    static ArrayList<Operation> operations = new ArrayList<>();
    static Scanner src = new Scanner(System.in);

    static boolean appRunning = true;
    static char mainManu = 'h';
    static boolean editing;

    public static void main(String[] args) {

        while ( appRunning ) {
            switch ( mainManu ) {
                case 'h':
                    mainManu = showManu();
                    break;
                case 'c':
                    // enter client
                    clientFunc();
                    mainManu = showManu();
                    break;
                case 'a':
                    checkingAccountFunc();
                    mainManu = showManu();
                    break;
                case 'o':
                    operationFunc();
                    mainManu = showManu();
                    break;
                case 'q':
                    System.out.println("good bye");
                    appRunning = false;
                    break;

            }
        }
    }

    static char showManu () {
        System.out.println("Please choose one of the rooms )");
        System.out.println("c => enter Client room");
        System.out.println("a => enter account room");
        System.out.println("o => enter operation room");
        System.out.println("q => quite the application");
        String charChoice = src.nextLine();

        if ( charChoice.length() > 1 ) {
            System.out.println("please enter only one single character");
            charChoice = src.nextLine();
        }

        while ( charChoice.charAt(0) != 'h' && charChoice.charAt(0) != 'c' && charChoice.charAt(0) != 'a' && charChoice.charAt(0) != 'q' && charChoice.charAt(0) != 'o' ) {
            System.out.println("in valid manu type allow characters (h,c,a,q)");
            System.out.println("please enter a valid character");
            charChoice = src.nextLine();
        }
        return charChoice.charAt(0);
    }

    static void clientFunc () {
        Client client = new Client();
        boolean subMenuRunning = true, editing = true;
        int menu = client.subMenu();

        while ( subMenuRunning ) {
            switch ( menu ) {
                case 0:
                    subMenuRunning = false;
                    break;
                case 1:
                    client.list();
                    menu = client.subMenu();
                    break;
                case 2:
                    // show single client
                    client.show();
                    menu = client.subMenu();
                    break;
                case 3:
                    // create a client
                    client.create();
                    menu = client.subMenu();
                    break;
                case 4:
                    menu = client.subMenu();
                    break;
            }
        }
    }

    static void operationFunc () {
        Operation operation = new Operation();

        boolean subMenuRunning = true;
        int menu = operation.subMenu();

        while ( subMenuRunning ) {
            switch ( menu ) {
                case 0:
                    subMenuRunning = false;
                    break;
                case 1:
                    operation.list();
                    menu = operation.subMenu();
                    break;
                case 2:

                    menu = operation.subMenu();
                    break;
                case 3:
                    operation.deposit();
                    menu = operation.subMenu();
                    break;
                case 4:
                    operation.withdraw();
                    menu = operation.subMenu();
                    break;
                case 5:
                    operation.transfer();
                    menu = operation.subMenu();
                    break;
                case 7:
                    menu = operation.subMenu();
                    break;

            }
        }
    }

    static void checkingAccountFunc () {
        CheckingAccount checkingAccount = new CheckingAccount();
        SavingsAccount savingsAccount = new SavingsAccount();

        boolean subMenuRunning = true;
        int menu = checkingAccount.subMenu();

        while ( subMenuRunning ) {
            switch ( menu ) {
                case 0:
                    subMenuRunning = false;
                    break;
                case 1:
                    //checkingAccount.list();
                    menu = checkingAccount.subMenu();
                    break;
                case 2:
                    while ( true ) {
                        try {
                            // show single client
                            System.out.println("Please enter Account ID ");
                            int accountId = src.nextInt();
                            src.nextLine();

                            System.out.println("What type of Account?");
                            System.out.println("1 => show Checking Account");
                            System.out.println("2 => show Savings Account");
                            int accountType = src.nextInt();
                            src.nextLine();

                            while ( accountType != 1 && accountType != 2 ) {
                                System.out.println("Invalid account Type ?");
                                System.out.println("1 => show Checking Account");
                                System.out.println("2 => show Savings Account");
                                accountType = src.nextInt();
                                src.nextLine();
                            }

                            switch (accountType) {
                                case 1:
                                    checkingAccount.show( accountId );
                                    break;
                                case 2:
                                    savingsAccount.show( accountId );
                                    break;
                            }
                            break;
                        }
                        catch ( InputMismatchException e ) {
                            System.out.println("please enter valid numbers");
                            src.nextLine();
                        }
                    }
                    menu = checkingAccount.subMenu();
                    break;
                case 3:
                    // create a client
                    while ( true ) {
                        try {
                            System.out.println("What type of Account?");
                            System.out.println("1 => to open a Checking Account");
                            System.out.println("2 => to open a Savings Account");
                            int accountType = src.nextInt();
                            src.nextLine();
                            System.out.println("please enter Client ID ");
                            int clientId = src.nextInt();
                            src.nextLine();
                            switch ( accountType ) {
                                case 1:
                                    checkingAccount.create( clientId );
                                    break;
                                case 2:
                                    savingsAccount.create( clientId );
                                    break;
                            }
                            break;
                        }
                        catch ( InputMismatchException e ) {
                            System.out.println("please enter valid numbers");
                            src.nextLine();
                        }
                    }
                    menu = checkingAccount.subMenu();
                    break;
                case 4:
                    while ( true ) {
                        try {
                            System.out.println("What type of Account?");
                            System.out.println("1 => Checking Account");
                            System.out.println("2 => Savings Account");
                            int accountType = src.nextInt();
                            src.nextLine();
                            System.out.println("please enter Account ID ");
                            int clientId = src.nextInt();
                            src.nextLine();
                            switch ( accountType ) {
                                case 1:
                                    checkingAccount.listAccountOperationsHistory( clientId );
                                    break;
                                case 2:
                                    savingsAccount.create( clientId );
                                    break;
                            }
                            break;
                        }
                        catch ( InputMismatchException e ) {
                            System.out.println("please enter valid numbers");
                        }
                    }
                    menu = checkingAccount.subMenu();
                    break;
                case 5:
                    while ( true ) {
                        try {
                            System.out.println("What type of Account?");
                            System.out.println("1 => Checking Account");
                            System.out.println("2 => Savings Account");
                            int accountType = src.nextInt();
                            src.nextLine();
                            switch ( accountType ) {
                                case 1:
                                    checkingAccount.filterByMinimumBlanace( Main.accounts );
                                    break;
                                case 2:
                                    savingsAccount.filterByMinimumBlanace( Main.accounts );
                                    break;
                            }
                            break;
                        }
                        catch ( InputMismatchException e ) {
                            System.out.println("please enter valid numbers");
                        }
                    }
                    break;
                case 6:
                    menu = checkingAccount.subMenu();
                    break;
            }
        }
    }
}