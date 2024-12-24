public abstract class  Account {
    private int count = 1;
    private int id;
    private long accountNumber; // this number should be generated automatically and it should be unique
    private double balance;
    private Client ownedBy;

    Account () {
        this.id = count;
        count++;
    }
    // TODO Add overloaded constructor if needed

}
