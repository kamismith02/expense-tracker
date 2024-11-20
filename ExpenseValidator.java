import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ExpenseValidator {

    // Validate that the amount is a non-negative finite number
    public static boolean isValidAmount(double amount) {
        return amount >= 0 && Double.isFinite(amount);
    }

    // Validate that the category is not null or empty
    public static boolean isValidCategory(String category) {
        return category != null && !category.trim().isEmpty();
    }

    // Parse and validate the date string (YYYY-MM-DD format)
    public static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date);  // Automatically validates the date format
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");
        }
    }
}
