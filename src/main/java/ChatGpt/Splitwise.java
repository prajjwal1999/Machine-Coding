package ChatGpt;

import java.util.*;

class User {
    String id;
    String name;
    String email;
    String phoneNumber;

    public User(String id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}

abstract class Split {
    User user;
    double amount;

    public Split(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }
}

class EqualSplit extends Split {
    public EqualSplit(User user, double amount) {
        super(user, amount);
    }
}

class ExactSplit extends Split {
    public ExactSplit(User user, double amount) {
        super(user, amount);
    }
}

class PercentageSplit extends Split {
    double percentage;

    public PercentageSplit(User user, double percentage, double amount) {
        super(user, amount);
        this.percentage = percentage;
    }
}

class Expense {
    String id;
    User paidBy;
    double amount;
    List<Split> splits;

    public Expense(String id, User paidBy, double amount, List<Split> splits) {
        this.id = id;
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
    }
}

class ExpenseManager {
    List<Expense> expenses;
    Map<String, Map<String, Double>> balances;

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
        this.balances = new HashMap<>();
    }

    public void addExpense(String id, User paidBy, double amount, List<Split> splits) {
        Expense expense = new Expense(id, paidBy, amount, splits);
        expenses.add(expense);

        for (Split split : splits) {
            balances.putIfAbsent(split.user.id, new HashMap<>());
            balances.putIfAbsent(paidBy.id, new HashMap<>());

            Map<String, Double> userBalance = balances.get(split.user.id);
            userBalance.put(paidBy.id, userBalance.getOrDefault(paidBy.id, 0.0) + split.amount);

            Map<String, Double> paidByBalance = balances.get(paidBy.id);
            paidByBalance.put(split.user.id, paidByBalance.getOrDefault(split.user.id, 0.0) - split.amount);
        }
    }

    public void showBalances() {
        for (String user : balances.keySet()) {
            for (Map.Entry<String, Double> entry : balances.get(user).entrySet()) {
                if (entry.getValue() > 0) {
                    System.out.println(user + " owes " + entry.getKey() + ": " + entry.getValue());
                }
            }
        }
    }
}

public class Splitwise {
    public static void main(String[] args) {
        User u1 = new User("1", "Alice", "alice@example.com", "12345");
        User u2 = new User("2", "Bob", "bob@example.com", "67890");
        User u3 = new User("3", "Charlie", "charlie@example.com", "54321");

        ExpenseManager manager = new ExpenseManager();

        List<Split> splits = Arrays.asList(
                new EqualSplit(u2, 50.0),
                new EqualSplit(u3, 50.0)
        );
        List<Split> splits2 = Arrays.asList(
                new EqualSplit(u2, 500.0),
                new EqualSplit(u3, 500.0)
        );

        manager.addExpense("exp1", u1, 100.0, splits);
        manager.addExpense("exp2", u2, 1000.0, splits2);

        manager.showBalances();
    }
}

