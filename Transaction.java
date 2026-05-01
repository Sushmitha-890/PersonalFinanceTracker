public abstract class Transaction {

    protected double amount;
    protected String category;
    protected String date;

    // Constructor with validation
    public Transaction(double amount, String category, String date) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }

        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty");
        }

        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Abstract method (polymorphism)
    public abstract void apply(Account account);

    // Getters
    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }
}