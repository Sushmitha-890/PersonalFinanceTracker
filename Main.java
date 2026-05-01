import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {

    public static boolean isValidDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return dateStr.equals(date.format(formatter));
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Account account = new Account();
        FileHandler.loadData(account); // LOAD DATA
        ReportGenerator report = new ReportGenerator();

        while (true) {
            System.out.println("\n====== Personal Finance Tracker ======");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Report");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            sc.nextLine();

            switch (choice) {

                // ADD INCOME
                case 1:
                    try {
                        double incAmount;
                        while (true) {
                            System.out.print("Enter amount: ");
                            incAmount = sc.nextDouble();
                            sc.nextLine();
                            if (incAmount <= 0) {
                                System.out.println("Error: Amount must be greater than 0");
                            } else break;
                        }

                        String incCategory;
                        while (true) {
                            System.out.print("Enter category: ");
                            incCategory = sc.nextLine();
                            if (incCategory.trim().isEmpty()) {
                                System.out.println("Error: Category cannot be empty");
                            } else break;
                        }

                        String incDate;
                        while (true) {
                            System.out.print("Enter date (DD/MM/YYYY): ");
                            incDate = sc.nextLine();
                            if (!isValidDate(incDate)) {
                                System.out.println("Error: Invalid date format!");
                            } else break;
                        }

                        account.addTransaction(new Income(incAmount, incCategory, incDate));
                        FileHandler.saveTransaction("INCOME", incAmount, incCategory, incDate);
            System.out.printf("Success: Income added successfully! (Rs. %.2f)\n", incAmount);

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;

                // ADD EXPENSE
                case 2:
                    try {
                        double expAmount;
                        while (true) {
                            System.out.print("Enter amount: ");
                            expAmount = sc.nextDouble();
                            sc.nextLine();
                            if (expAmount <= 0) {
                                System.out.println("Error: Amount must be greater than 0");
                            } else break;
                        }

                        String expCategory;
                        while (true) {
                            System.out.print("Enter category: ");
                            expCategory = sc.nextLine();
                            if (expCategory.trim().isEmpty()) {
                                System.out.println("Error: Category cannot be empty");
                            } else break;
                        }

                        String expDate;
                        while (true) {
                            System.out.print("Enter date (DD/MM/YYYY): ");
                            expDate = sc.nextLine();
                            if (!isValidDate(expDate)) {
                                System.out.println("Error: Invalid date format!");
                            } else break;
                        }

                        try {
                            account.addTransaction(new Expense(expAmount, expCategory, expDate));
                            FileHandler.saveTransaction("EXPENSE", expAmount, expCategory, expDate);
                            System.out.printf("Success: Expense added successfully! (Rs. %.2f)\n", expAmount);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    } catch (Exception e) {
                        System.out.println("Invalid input! Please try again.");
                        sc.nextLine();
                    }
                    break;

                // UPDATED REPORT SECTION
                case 3:

    // CHECK FIRST
    if (account.getTransactions().isEmpty()) {
        System.out.println("\nNo records found.");
        System.out.println("Please add income first.\n");
        break;
    }

    // Step 1 → Show overall report
    report.generateOverallReport(account);

    // Step 2 - Show options ONLY if data exists
    while (true) {
        System.out.println("\nReport Options:");
        System.out.println("1. View Monthly Report");
        System.out.println("2. Back to Main Menu");
        System.out.print("Enter choice: ");

        int option;

        try {
            option = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input!");
            sc.nextLine();
            continue;
        }

        if (option == 1) {
            try {
                System.out.print("Enter month (1-12): ");
                int month = sc.nextInt();

                System.out.print("Enter year: ");
                int year = sc.nextInt();
                sc.nextLine();

                report.generateMonthlyReport(account, month, year);

            } catch (Exception e) {
                System.out.println("Invalid input!");
                sc.nextLine();
            }

        } else if (option == 2) {
            break;
        } else {
            System.out.println("Invalid choice!");
        }
    }
    break;
                // EXIT
                case 4:
                    System.out.println("Exiting application. Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}