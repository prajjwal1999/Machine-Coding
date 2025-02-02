package splitwise;

public class Split {
    private Users user;
    private double amount;

    public Split(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
