package splitwise.src;

/**
 * ExpenseType
 */
public interface ExpenseType {
    public static final AppConfig appConfig = AppConfig.getInstance();

    public void validateExpense() throws IllegalArgumentException;
    public void addExpense();
}