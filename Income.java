public class Income extends Transaction {

    public Income(double amount, String category, String date) {
        super(amount, category, date);
    }

    @Override
    public void apply(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        account.updateBalance(amount);
    }
}