import java.io.Serializable; // Import Serializable interface for file operations

// Expense class implements Serializable to allow objects to be written to and read from files
public class Expense implements Serializable {

    // Fields to store expense details
    private String category; // Category of the expense (e.g., "Food", "Utilities")
    private double amount; // Amount of the expense
    private String date; // Date of the expense (in the format YYYY-MM-DD)

    // Constructor to initialize the expense object
    public Expense(String category, double amount, String date) {
        // Validate the amount to ensure it is not negative
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        // Validate the date format to ensure it's in the format YYYY-MM-DD
        if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid date format. Use YYYY-MM-DD.");
        }

        // Assign the provided values to the fields
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    // Getter method for the category field
    public String getCategory() {
        return category;
    }

    // Getter method for the amount field
    public double getAmount() {
        return amount;
    }

    // Getter method for the date field
    public String getDate() {
        return date;
    }

    // Override toString() method to provide a meaningful string representation of
    // the expense object
    @Override
    public String toString() {
        // Return a string that shows the expense details
        return "Expense [Category=" + category + ", Amount=" + amount + ", Date=" + date + "]";
    }
}
