import java.util.ArrayList;
import java.util.List;

public class Account {

    private double balance;
    private List<Transaction> transactions;

    public Account() {
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    // Add transaction safely
    public void addTransaction(Transaction t) {

        if (t == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }

        try {
            t.apply(this); // apply income/expense logic
            transactions.add(t);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Update balance internally only
    public void updateBalance(double amount) {
        balance += amount;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Getter for transaction list
    public List<Transaction> getTransactions() {
        return transactions;
    }
}