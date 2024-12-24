public class Client {

    static int count = 1;

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;

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


}
