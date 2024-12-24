public class CheckingAccount {
    enum type {
        monthly,
        yearly
    }

    private double bankCharges;

    CheckingAccount () {}
    CheckingAccount ( double bankCharges ) {
        this.bankCharges = bankCharges;
    }

    public double getBankCharges () {
        return this.bankCharges;
    }

    public void setBankCharges ( double bankCharges ) {
        this.bankCharges = bankCharges;
    }




}
