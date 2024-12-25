import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Client> clients = new ArrayList<Client>();
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
        System.out.println("q => quite the application");
        String charChoice = src.nextLine();

        if ( charChoice.length() > 1 ) {
            System.out.println("please enter only one single character");
            charChoice = src.nextLine();
        }

        while ( charChoice.charAt(0) != 'h' && charChoice.charAt(0) != 'c' && charChoice.charAt(0) != 'a' && charChoice.charAt(0) != 'q' ) {
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
                    break;
                case 3:
                    // create a client
                    client.create( editing );
                    menu = client.subMenu();
                case 4:
                    menu = client.subMenu();
                    break;
            }
        }


    }
}