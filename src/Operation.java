import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Operation {
    static int counter = 1;
    public int id;
    public LocalDateTime currentDate;
    public double amount;
    public String operationType;
    public Account associatedAccount;
    public Account recievedBy;

    Operation () {}

    Operation ( String operationType, double amount, Account associatedAccount ) {
        this.id = counter;
        this.operationType = operationType;
        this.amount = amount;
        this.currentDate = LocalDateTime.now();
        this.associatedAccount = associatedAccount;
        Main.operations.add(this);
        counter++;
    }

    Operation ( String operationType, double amount, Account associatedAccount, Account recievedBy ) {
        this.id = counter;
        this.operationType = operationType;
        this.amount = amount;
        this.currentDate = LocalDateTime.now();
        this.associatedAccount = associatedAccount;
        this.recievedBy = recievedBy;
        Main.operations.add(this);
        counter++;
    }

    public Scanner scr = new Scanner(System.in);
    public int option;
    public CheckingAccount checkingAccount = new CheckingAccount();
    public SavingsAccount savingsAccount = new SavingsAccount();
    public Client client = new Client();

    public void list () {
        if ( Main.operations.size() > 0 ) {
            System.out.println("Do you want to Apply Filters");
            System.out.println("0 => to cancel Filter");
            System.out.println("1 => to confirm filter");
            int confirmFilter = scr.nextInt();
            scr.nextLine();
            if ( confirmFilter == 1 ) {

                ArrayList<Operation> operations = this.applyFilter();
                for ( Operation  operation : operations ) {
                    this.displayOperation( operation );
                }

            } else {
                for ( Operation  operation : Main.operations ) {
                    this.displayOperation( operation );
                }
            }
        } else {
            System.out.println("NO Operations yet");
        }
    }

    public void displayOperation ( Operation operation ) {
        System.out.println( "Operation : " + operation.operationType );
        System.out.println( "Made by : " + getOwnerFullName( operation.associatedAccount.getOwnedBy() ) );
        System.out.println( "Ampount : " + operation.amount);
        System.out.println("Date Of Operation : " + operation.currentDate);
    }

    public String getOwnerFullName ( int clientId ) {
        for ( Client client : Main.clients ) {
            if ( client.getId() == clientId ) {
                return client.getFirstName() + " " + client.getLastName();
            }
        }
        return null;
    }

    public void deposit () {
        try {
            System.out.println("What type of account");
            System.out.println("1 => Checking Account");
            System.out.println("2 => Savings Account");
            int accountType = scr.nextInt();
            scr.nextLine();

            int accountId;
            double amount;

            while ( accountType != 1 && accountType != 2 ) {
                System.out.println("invalid account");
                accountType = scr.nextInt();
                scr.nextLine();
            }

            System.out.println("Enter account ID: ");
            accountId = scr.nextInt();
            scr.nextLine();

            switch ( accountType ) {
                case 1:
                    // get account
                    CheckingAccount checkingAcc = this.findCheckingAccountById( accountId );
                    if ( checkingAcc != null ) {
                        System.out.println("Enter the amount you want to deposit ");
                        amount = scr.nextDouble();
                        scr.nextLine();
                        Operation newOperation = new Operation( "deposit", amount, checkingAcc);
                        checkingAcc.encreaseBalance( amount );
                        checkingAcc.addOperationToHistory( newOperation );
                    } else {
                        System.out.println("UNFOUND Account.");
                    }
                    break;
                case 2:
                    SavingsAccount savingsAcc = this.findSavingsAccountById( accountId );
                    if ( savingsAcc != null ) {
                        System.out.println("Enter the amount you want to deposit ");
                        amount = scr.nextDouble();
                        scr.nextLine();
                        Operation newOperation = new Operation( "deposit", amount, savingsAcc);
                        savingsAcc.encreaseBalance( amount );
                        savingsAcc.addOperationToHistory( newOperation );
                    } else {
                        System.out.println("UNFOUND Account.");
                    }
                    break;
            }
        }
        catch ( InputMismatchException e ) {
            System.out.println("please enter a valide number (0,1,2,3)");
            scr.nextLine();
        }
    }

    public void withdraw () {
        try {
            System.out.println("What type of account");
            System.out.println("1 => Checking Account");
            System.out.println("2 => Savings Account");
            int accountType = scr.nextInt();
            scr.nextLine();

            int accountId;
            double amount;

            while ( accountType != 1 && accountType != 2 ) {
                System.out.println("invalid account");
                accountType = scr.nextInt();
                scr.nextLine();
            }

            System.out.println("Enter account ID: ");
            accountId = scr.nextInt();
            scr.nextLine();

            switch ( accountType ) {
                case 1:
                    // get account
                    CheckingAccount checkingAcc = this.findCheckingAccountById( accountId );
                    if ( checkingAcc != null ) {
                        System.out.println("Enter the amount you want to withdraw ");
                        amount = scr.nextDouble();
                        scr.nextLine();

                        if ( checkingAcc.getBalance() < amount ) {
                            System.out.println("Unsufficiant funds");
                        } else {
                            Operation newOperation = new Operation( "withdraw", amount, checkingAcc);
                            checkingAcc.decreaseBalance( amount );
                            checkingAcc.addOperationToHistory( newOperation );
                        }
                    } else {
                        System.out.println("UNFOUND Account.");
                    }
                    break;
                case 2:
                    SavingsAccount savingsAcc = this.findSavingsAccountById( accountId );
                    if ( savingsAcc != null ) {
                        System.out.println("Enter the amount you want to widthdraw ");
                        amount = scr.nextDouble();
                        scr.nextLine();

                        if ( savingsAcc.getBalance() < amount ) {
                            System.out.println("Unsufficiant funds");
                        } else {
                            Operation newOperation = new Operation( "withdraw", amount, savingsAcc);
                            savingsAcc.encreaseBalance( amount );
                            savingsAcc.addOperationToHistory( newOperation );
                        }

                    } else {
                        System.out.println("UNFOUND Account.");
                    }
                    break;
            }
        }
        catch ( InputMismatchException e ) {
            System.out.println("please enter a valide number (0,1,2,3)");
            scr.nextLine();
        }
    }

    public void transfer () {
        try {
            // get account of sender
            System.out.println("Please enter ID of the sender");
            int senderId = scr.nextInt();
            scr.nextLine();
            CheckingAccount foundSenderAccount = checkingAccount.findAccountById( senderId );

            // get account of reciever
            System.out.println("Please enter ID of the reciever");
            int recieverId = scr.nextInt();
            scr.nextLine();
            CheckingAccount foundRecieverAccount = checkingAccount.findAccountById( recieverId );

            double amount;
            // check if accounts exist
            if ( foundSenderAccount != null ) {
                if ( foundRecieverAccount != null ) {
                    // check if sender has sufficient funds
                    System.out.println("Please enter the amount you want to transfer");
                    amount = scr.nextDouble();
                    if ( amount > foundSenderAccount.getBalance() ) {
                        System.out.println("Unsufficiant funds");
                    } else {
                        // make the transfer
                        Operation newOperation = new Operation("transfer", amount, foundSenderAccount,  foundRecieverAccount);
                        foundSenderAccount.decreaseBalance( amount );
                        foundRecieverAccount.encreaseBalance( amount );
                        foundSenderAccount.addOperationToHistory( newOperation );
                        foundRecieverAccount.addOperationToHistory( newOperation );
                    }

                } else {
                    System.out.println("the reciever you have entered does not exist");
                }
            } else {
                System.out.println("the sender you have entered does not exist");
            }

        }
        catch ( InputMismatchException e ) {
            System.out.println("please enter a valide number");
        }
    }

    public ArrayList<Operation> applyFilter () {
        ArrayList<Operation> filteredOps = new ArrayList<Operation>();
        int option;
        while ( true ) {
            try {

                System.out.println("Apply filter : ");
                System.out.println("    1 => by Transaction Type");
                System.out.println("    2 => by Minimum Deposit");
                System.out.println("    3 => by Date");
                option = scr.nextInt();
                scr.nextLine();

                switch ( option ) {
                    case 1:
                        System.out.println("1 => Filter by DEPOSIT Type");
                        System.out.println("2 => Filter by WITHDRAW Type");
                        System.out.println("3 => Filter by TRANSFER Type");
                        int type = scr.nextInt();
                        scr.nextLine();
                        String choiceOfType = type == 1 ? "deposit" : type == 2 ? "withdraw" : "transfer";

                        return filterByTransactionType( Main.operations, choiceOfType);
                    case 2:

                        break;
                    case 3:
                        System.out.println("1 => Filter by Time Range");
                        System.out.println("2 => Filter by Specific Date");
                        int dateType = scr.nextInt();
                        scr.nextLine();
                        if ( dateType == 1 ) {
                            return filterByDateRange( Main.operations );
                        }
                        if ( dateType == 2 ) {
                            return filterBySpecificDate( Main.operations );
                        }
                        break;
                    default:
                        System.out.println("the option you've entered does not exist.");
                }

                break;
            }
            catch ( InputMismatchException e ) {
                System.out.println("Invalid number option, only integers accepted");
            }
        }
        return filteredOps;
    }

    // filters
    public ArrayList<Operation> filterByTransactionType ( ArrayList<Operation> operations, String type ) {
        ArrayList<Operation> filteredOps = new ArrayList<Operation>();
        for ( Operation operation : operations ) {
            if ( operation.operationType.equals( type ) ) {
                filteredOps.add( operation );
            }
        }
        return filteredOps;
    }

    public ArrayList<Operation> filterByDateRange ( ArrayList<Operation> operations) {
        System.out.println("Enter dates, you SHOULD Respect this date formate (yyyy-mm-dd or yyyy mm dd)");

        System.out.println("Enter start date");
        String startDate = scr.nextLine();
        while ( !isValidDate( startDate) ) {
            System.out.println("invalid start date please re-enter like so (yyyy-mm-dd or yyyy mm dd)");
            startDate = scr.nextLine();
        }

        System.out.println("Enter end date");
        String endDate = scr.nextLine();
        while ( !isValidDate( endDate ) ) {
            System.out.println("invalid end date please re-enter like so (yyyy-mm-dd or yyyy mm dd)");
            endDate = scr.nextLine();
        }

        int[] fullDateStart = getDate(startDate);
        int[] fullDateEnd = getDate(endDate);

        LocalDate searchDateStart = LocalDate.of( fullDateStart[0], fullDateStart[1], fullDateStart[2]);
        LocalDate searchDateEnd = LocalDate.of( fullDateEnd[0], fullDateEnd[1], fullDateEnd[2]);

        return operations.stream()
                .filter(op -> {
                    LocalDate getLocalDate = op.currentDate.toLocalDate();
                    return (
                            ( getLocalDate.isEqual(searchDateStart) || getLocalDate.isAfter( searchDateStart) )
                            &&
                            ( getLocalDate.isEqual(searchDateEnd) || getLocalDate.isBefore( searchDateEnd ) )
                    );
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Operation> filterBySpecificDate ( ArrayList<Operation> operations ) {
        System.out.println("Enter dates, you SHOULD Respect this date formate (yyyy-mm-dd or yyyy mm dd)");
        System.out.println("Enter date ");
        String date = scr.nextLine();

        int[] fullDate = getDate(date);
        LocalDate searchDate = LocalDate.of( fullDate[0], fullDate[1], fullDate[2]);

        return operations.stream()
                .filter(op -> {
                    LocalDate getLocalDate = op.currentDate.toLocalDate();
                    return ( getLocalDate.isEqual(searchDate) );
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public int[] getDate ( String date ) {
        String regex = "[\\s\\-]";
        String[] dates = date.split( regex );
        int dateYear = Integer.parseInt( dates[0] );
        int dateMonth = Integer.parseInt( dates[1] );
        int dateDay = Integer.parseInt( dates[2] );
        int[] fullDate = { dateYear, dateMonth, dateDay};

        return fullDate;
    }

    public boolean isValidDate ( String text ) {
        String pattern = "(^(\\d{4}-\\d{2}-\\d{2})$|^(\\d{4} \\d{2} \\d{2})$)";
        Pattern ptr = Pattern.compile(pattern);
        Matcher match = ptr.matcher(text);

        return match.find();
    }

    CheckingAccount findCheckingAccountById ( int accountId ) {
        for ( CheckingAccount account : Main.checkingAccounts ) {
            if ( account.getId() == accountId ) {
                return account;
            }
        }
        return null;
    }

    SavingsAccount findSavingsAccountById ( int accountId ) {
        for ( SavingsAccount account : Main.savingsAccounts) {
            if ( account.getId() == accountId ) {
                return account;
            }
        }
        return null;
    }

    int subMenu () {
        try {
            System.out.println("please enter one of the options");
            System.out.println("0 => quite Operation section");
            System.out.println("1 => list all Operations");
            System.out.println("2 => list a single Operation");
            System.out.println("3 => make a deposit operation");
            System.out.println("4 => make a withdraw operation");
            System.out.println("5 => make a transfer operation");
            option = scr.nextInt();
            scr.nextLine();
            while ( option != 0 && option != 1 && option != 2 && option != 3 && option != 4 && option != 5 ) {
                System.out.println("invalid option please choose one of (0,1,2,3,4)");
                option = scr.nextInt();
                scr.nextLine();
            }
            return option;
        }
        catch ( InputMismatchException e ) {
            System.out.println("please enter a valide number (0,1,2,3)");
            scr.nextLine();
            return 7;
        }
    }

}
