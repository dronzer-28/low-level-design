package splitwise.src;

/**
 * Splitwise
 */
public class Splitwise {
    AppConfig appConfig = AppConfig.getInstance();
    ExpenseType expenseType;
    Balances balances;

    public void addUser();
}

// SplitWise:
//      - appConfig: AppConfig
//      - expenseType: ExpenseType
//      - balances: Balances
//      + Splitwise()
//      + addUser(userId, name, email, mobNo) -> void
//      + getBalance(showInput: List<String>) -> List<String>
//      + addExpense(addInput: List<String>) -> void