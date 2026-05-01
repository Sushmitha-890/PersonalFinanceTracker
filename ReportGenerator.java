import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReportGenerator {

    // ✅ OVERALL REPORT (NEW)
    public void generateOverallReport(Account account) {

        if (account.getTransactions().isEmpty()) {
            System.out.println("\n No data available.");
            return;
        }

        double totalIncome = 0;
        double totalExpense = 0;

        Map<String, Double> incomeCategories = new HashMap<>();
        Map<String, Double> expenseCategories = new HashMap<>();

        for (Transaction t : account.getTransactions()) {

            if (t instanceof Income) {
                totalIncome += t.getAmount();
                incomeCategories.put(
                        t.getCategory(),
                        incomeCategories.getOrDefault(t.getCategory(), 0.0) + t.getAmount()
                );

            } else if (t instanceof Expense) {
                totalExpense += t.getAmount();
                expenseCategories.put(
                        t.getCategory(),
                        expenseCategories.getOrDefault(t.getCategory(), 0.0) + t.getAmount()
                );
            }
        }

        System.out.println("\n=========== OVERALL REPORT ===========");
        System.out.printf("Total Income  : Rs. %.2f\n", totalIncome);
        System.out.printf("Total Expense : Rs. %.2f\n", totalExpense);
        System.out.println("-------------------------------------");
        System.out.printf("Net Balance   : Rs. %.2f\n", account.getBalance());

        System.out.println("\n--- Income Categories ---");
        for (Map.Entry<String, Double> e : incomeCategories.entrySet()) {
            System.out.println(e.getKey() + " : Rs. " + e.getValue());
        }

        System.out.println("\n--- Expense Categories ---");
        if (expenseCategories.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            for (Map.Entry<String, Double> e : expenseCategories.entrySet()) {
                System.out.println(e.getKey() + " : Rs. " + e.getValue());
            }
        }

        System.out.println("=====================================\n");
    }

    // ✅ MONTHLY REPORT (UPDATED CLEAN VERSION)
    public void generateMonthlyReport(Account account, int month, int year) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        double totalIncome = 0;
        double totalExpense = 0;

        Map<String, Double> incomeCategories = new HashMap<>();
        Map<String, Double> expenseCategories = new HashMap<>();

        boolean dataFound = false;

        for (Transaction t : account.getTransactions()) {

            LocalDate date = LocalDate.parse(t.date, formatter);

            if (date.getMonthValue() == month && date.getYear() == year) {

                dataFound = true;

                if (t instanceof Income) {
                    totalIncome += t.getAmount();
                    incomeCategories.put(
                            t.getCategory(),
                            incomeCategories.getOrDefault(t.getCategory(), 0.0) + t.getAmount()
                    );

                } else if (t instanceof Expense) {
                    totalExpense += t.getAmount();
                    expenseCategories.put(
                            t.getCategory(),
                            expenseCategories.getOrDefault(t.getCategory(), 0.0) + t.getAmount()
                    );
                }
            }
        }

        // ❌ NO DATA CASE
        if (!dataFound) {
            System.out.println("\n No records found for " + month + "/" + year);
            System.out.println("👉 Please try another month/year.\n");
            return;
        }

        // ⚠️ OPTIONAL WARNING
        if (totalIncome == 0 && totalExpense > 0) {
            System.out.println("\n!!!! Warning: Expenses exist without income for this period.");
        }

        // ✅ REPORT OUTPUT
        System.out.println("\n========= MONTHLY REPORT =========");
        System.out.println("Month: " + month + " Year: " + year);

        System.out.printf("Total Income  : Rs. %.2f\n", totalIncome);
        System.out.printf("Total Expense : Rs. %.2f\n", totalExpense);
        System.out.println("---------------------------------");
        System.out.printf("Net Balance   : Rs. %.2f\n", (totalIncome - totalExpense));

        System.out.println("\n--- Income Categories ---");
        if (incomeCategories.isEmpty()) {
            System.out.println("No income recorded.");
        } else {
            for (Map.Entry<String, Double> e : incomeCategories.entrySet()) {
                System.out.println(e.getKey() + " : Rs. " + e.getValue());
            }
        }

        System.out.println("\n--- Expense Categories ---");
        if (expenseCategories.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            for (Map.Entry<String, Double> e : expenseCategories.entrySet()) {
                System.out.println(e.getKey() + " : Rs. " + e.getValue());
            }
        }

        System.out.println("=================================\n");
    }
}