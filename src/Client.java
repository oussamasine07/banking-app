import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {

    static int count = 1;

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    // TODO create an array list of associated acounts

    Scanner scr = new Scanner(System.in);

    Client () {}

    Client ( String firstName, String lastName, String email, String address, String phone ) {
        this.id = count;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        count++;
    }


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


    int subMenu () {
        try {
            System.out.println("please enter one of the options");
            System.out.println("0 => quite Client section");
            System.out.println("1 => list all Clients");
            System.out.println("2 => list a single Client");
            System.out.println("3 => create a Client");
            int option = scr.nextInt();
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
