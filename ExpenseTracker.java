import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {
    // List to store expenses
    private static ArrayList<Expense> expenses = new ArrayList<>();
    // File name for storing expenses
    private static final String FILE_NAME = "expenses.dat";

    // Main method to run the program
    public static void main(String[] args) {
        // Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Load expenses from file
        loadExpenses();

        // Display welcome message
        System.out.println("Welcome to the Expense Tracker!");

        boolean running = true;
        // Main program loop
        while (running) {
            // Display menu options to the user
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Calculate Total Expenses");
            System.out.println("4. Save and Exit");

            // Read the user's choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Perform the corresponding action based on user's choice
            switch (choice) {
                case 1:
                    addExpense(scanner);
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    calculateTotalExpenses();
                    break;
                case 4:
                    saveExpenses();
                    running = false; // Stop the loop to exit the program
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        // Close the scanner to prevent resource leaks
        scanner.close();
    }

    // Method to add an expense
    private static void addExpense(Scanner scanner) {
        // Read category for the expense
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        // Read amount for the expense
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        // Read date of the expense
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        // Create a new expense object and add it to the list
        expenses.add(new Expense(category, amount, date));
        System.out.println("Expense added successfully!");
    }

    // Method to view all recorded expenses
    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            System.out.println("Recorded Expenses:");
            for (Expense expense : expenses) {
                System.out.println(expense); // Print each expense
            }
        }
    }

    // Method to calculate and display the total expenses
    private static void calculateTotalExpenses() {
        double total = 0;
        // Calculate the sum of all expenses
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        // Display the total expenses
        System.out.println("Total Expenses: $" + total);
    }

    // Method to save expenses to a file
    private static void saveExpenses() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            // Write the list of expenses to a file
            oos.writeObject(expenses);
            System.out.println("Expenses saved successfully!");
        } catch (IOException e) {
            // Handle potential I/O errors
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    // Method to load expenses from a file
    private static void loadExpenses() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            // Read the object from file and check if it's an ArrayList
            Object obj = ois.readObject();

            // Verify if the object is an instance of ArrayList
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> tempList = (ArrayList<?>) obj;

                // Suppress unchecked cast warning
                @SuppressWarnings("unchecked")
                ArrayList<Expense> castedList = (ArrayList<Expense>) tempList;

                // Assign to the expenses list
                expenses = castedList;
                System.out.println("Expenses loaded successfully!");
            } else {
                System.out.println("Error: File contents are not as expected.");
            }
        } catch (FileNotFoundException e) {
            // If no previous expenses file exists, start fresh
            System.out.println("No previous expenses found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            // Handle errors related to I/O or class not found
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }
}
