import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {

    static int count = 1;

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private ArrayList<Account> accounts = new ArrayList<Account>();

    Scanner scr = new Scanner(System.in);


    Client () {}

    Client ( String firstName, String lastName, String email, String address, String phone ) {
        this.id = count;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        Main.clients.add(this);
        count++;
    }

    static int option;

    public int getId () { return this.id; }
    public String getFirstName () {
        return this.firstName;
    }
    public String getLastName () {
        return this.lastName;
    }
    public String getEmail () {
        return this.email;
    }
    public String getAddress () {
        return this.address;
    }
    public String getPhone () {
        return this.phone;
    }

    public void setFirstName ( String firstName ) {
        this.firstName = firstName;
    }
    public void setLastName ( String lastName ) {
        this.lastName = lastName;
    }
    public void setEmail ( String email ) {
        this.email = email;
    }
    public void setAddress ( String address ) {
        this.address = address;
    }
    public void setPhone ( String phone ) {
        this.phone = phone;
    }
    public void setAccounts ( Account account ) {
        this.accounts.add( account );
    }

    public boolean isValidletters ( String text ) {
        String pattern = "^[a-z\\s]+$";
        Pattern ptr = Pattern.compile(pattern);
        Matcher match = ptr.matcher(text);

        return match.find();
    }

    public boolean isValidEmail ( String text ) {
        String pattern = "^([a-zA-Z0-9_.-]+)@([a-z]+)\\.([a-z]+)$";
        Pattern ptr = Pattern.compile(pattern);
        Matcher match = ptr.matcher(text);

        return match.find();
    }

    public boolean isValidPhone ( String text ) {
        String pattern = "^((06)|(05)|(07))([0-9]{8})$";
        Pattern ptr = Pattern.compile(pattern);
        Matcher match = ptr.matcher(text);

        return match.find();
    }

    public boolean isValidAddress ( String text ) {
        String pattern = "^[a-zA-Z0-9\\s-°,]+$";
        Pattern ptr = Pattern.compile(pattern);
        Matcher match = ptr.matcher(text);

        return match.find();
    }

    public void create () {
        // using regex
            // validate client name it name should be all letters
        System.out.println("please enter first name");
        String firstName = scr.nextLine();
        while ( !isValidletters( firstName ) ) {
            System.out.println("invalid letters pleaser re-enter first name");
            firstName = scr.nextLine();
        }
        
        System.out.println("please enter last name");
        String lastName = scr.nextLine();
        while ( !isValidletters( lastName ) ) {
            System.out.println("invalid letters pleaser re-enter last name");
            lastName = scr.nextLine();
        }

        System.out.println("please enter email");
        String email = scr.nextLine();
        while ( !isValidEmail( email ) ) {
            System.out.println("invalid email please re-enter email");
            email = scr.nextLine();
        }

        System.out.println("please enter phone ");
        String phone = scr.nextLine();
        while ( !isValidPhone( phone ) ) {
            System.out.println("invalid phone it should be like so (0500000000) or (0600000000) or (0700000000)");
            phone = scr.nextLine();
        }

        System.out.println("please enter address");
        String address = scr.nextLine();
        while ( !isValidAddress( address ) ) {
            System.out.println("invalid address allowed characters (letters,digits,,°)");
            address = scr.nextLine();
        }

        System.out.println("Do you confirm adding this Client (YES/NO)");
        System.out.println("y => to Cancel client");
        System.out.println("n => to Create Client");

        String confirm = scr.nextLine();

        if ( confirm.length() > 1) {
            System.out.println("you entered more than one character");
            confirm = scr.nextLine();
        }

        while ( confirm.charAt(0) != 'y' && confirm.charAt(0) != 'n' ) {
            System.out.println("please enter only (y or n)");
            confirm = scr.nextLine();
        }


        if ( confirm.charAt(0) == 'y') {
            // create new client
            new Client( firstName, lastName, email, address, phone );
            // give the user the option to create a new account
            System.out.println("Do you want to create an account for this client ? enter (y or n)");
            confirm = scr.nextLine();
            if ( confirm.charAt(0) == 'y' ) {
                // create new account here
                System.out.println(" account created ");
                // ask wich type of account
            } else {
                System.out.println("new Client created");
            }
        } else {
            System.out.println("creating new client is canceled");
        }
    }

    public void list () {
        System.out.println("******************* ALL CLIENTS ******************");
        if ( Main.clients.size() == 0) {
            System.out.println("No Clients YET");
        } else {
            for ( Client client : Main.clients ) {
                System.out.println("Full Name: " + client.firstName + " " + client.lastName);
                System.out.println("Email : " + client.email);
                System.out.println("Phone : " + client.phone);
                System.out.println("Address : " + client.address);
            }
        }
    }

    public void show () {

        System.out.println("Please enter Client id");
        int clientId = scr.nextInt();

        Client client = findById( clientId );

        System.out.println("******************* ALL DETAILS ******************");

        if ( client != null ) {
            System.out.println("Full Name: " + client.firstName + " " + client.lastName);
            System.out.println("Email : " + client.email);
            System.out.println("Phone : " + client.phone);
            System.out.println("Address : " + client.address);
            if ( client.accounts.size() > 0) {
                for ( Account account : client.accounts ) {
                    System.out.println("Account Number: " + account.getAccountNumber());
                    System.out.println("Balance : " + account.getBalance());
                }
            } else {
                System.out.println("NO Accounts Yet");
            }
        } else {
            System.out.println("Unfound Client");
        }


    }

    public Client findById ( int id ) {
        Client foundClient = null;

        for ( Client client : Main.clients ) {
            if ( client.id == id) {
                foundClient = client;
                break;
            }
        }

        return foundClient;
    }

    int subMenu () {
        try {
            System.out.println("please enter one of the options");
            System.out.println("0 => quite Client section");
            System.out.println("1 => list all Clients");
            System.out.println("2 => list a single Client");
            System.out.println("3 => create a Client");
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
            return 4;
        }
    }

}
