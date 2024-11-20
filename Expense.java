import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Expense implements Serializable {

    private static final long serialVersionUID = 1L;

    private String category;  // Category of the expense (e.g., "Food", "Utilities")
    private double amount;    // Amount of the expense
    private LocalDate date;   // Date of the expense

    // Constructor
    public Expense(String category, double amount, String date) {
        if (!ExpenseValidator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Amount must be a non-negative finite number.");
        }

        if (!ExpenseValidator.isValidCategory(category)) {
            throw new IllegalArgumentException("Category cannot be null or empty.");
        }

        this.date = ExpenseValidator.parseDate(date);
        this.category = category;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Expense [Category=%s, Amount=%.2f, Date=%s]", category, amount, date);
    }
}
