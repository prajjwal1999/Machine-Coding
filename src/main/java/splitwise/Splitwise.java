package splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Splitwise {
    private List<Users> users;
    private Map<String,Map<String,Double>> balanceSheet;
    public Splitwise() {
        this.users = new ArrayList<>();
        this.balanceSheet = new HashMap<>();
    }
    public void addUser(Users user){
        users.add(user);
        balanceSheet.put(user.getId(),new HashMap<>());
    }
    public void addExpense(Expense expense){
        Users paidBy = expense.getPaidBy();
        double totalAmount = expense.getAmount();
        List<Split> splits = expense.getSplitList();
        for (Split split:splits){
            Users user = split.getUser();

        }
    }

}
