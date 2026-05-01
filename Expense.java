public class Expense extends Transaction {

    public Expense(double amount, String category, String date) {
        super(amount, category, date);
    }

    @Override
    public void apply(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        // Prevent overdraft (important edge case)
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance for this expense");
        }

        account.updateBalance(-amount);
    }
}