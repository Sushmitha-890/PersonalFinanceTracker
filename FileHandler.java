import java.io.*;

public class FileHandler {

    private static final String FILE_NAME = "data.txt";

    // SAVE
    public static void saveTransaction(String type, double amount, String category, String date) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);
            fw.write(type + "," + amount + "," + category + "," + date + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // LOAD ⭐ NEW
   public static void loadData(Account account) {
    try {
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String line;

        while ((line = br.readLine()) != null) {

            String[] parts = line.split(",");

            String type = parts[0];
            double amount = Double.parseDouble(parts[1]);
            String category = parts[2];
            String date = parts[3];

            // 🔥 DO NOT use addTransaction here
            if (type.equals("INCOME")) {
                account.updateBalance(amount);
                account.getTransactions().add(new Income(amount, category, date));

            } else if (type.equals("EXPENSE")) {
                account.updateBalance(-amount);
                account.getTransactions().add(new Expense(amount, category, date));
            }
        }

        br.close();

    } catch (Exception e) {
        System.out.println("No previous data found.");
    }
} }
