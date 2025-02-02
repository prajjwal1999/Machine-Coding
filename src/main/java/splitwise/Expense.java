package splitwise;

import java.util.List;

public class Expense {
    private String id;
    private double amount;

    public Expense(String id, double amount, Users paidBy, List<Split> splitList) {
        this.id = id;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splitList = splitList;
    }

    private  Users paidBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Users getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Users paidBy) {
        this.paidBy = paidBy;
    }

    public List<Split> getSplitList() {
        return splitList;
    }

    public void setSplitList(List<Split> splitList) {
        this.splitList = splitList;
    }

    private List<Split> splitList;
}
