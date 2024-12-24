public class SavingsAccount {

    private double interestRate;

    SavingsAccount () {}
    SavingsAccount ( double interestRate ) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate ( double interestRate ) {
        this.interestRate = interestRate;
    }
}
