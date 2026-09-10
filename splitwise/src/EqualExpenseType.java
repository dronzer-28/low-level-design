package splitwise.src;

import java.util.List;

/**
 * EqualExpenseType
 */
public class EqualExpenseType implements ExpenseType{
    Double value;
    User payer;
    List<User> userInvolved;

    public EqualExpenseType(String[] query){

    }

    @Override
    public void validateExpense() throws IllegalArgumentException {
        
    }

    @Override
    public void addExpense() {
        
    }
}

// EqualExpenseType implements ExpenseType
// - value: int
// - payer: User
// - involved: List<User>
// + EqualExpenseType(query: List<String>)
// + validateExpense()
// + addExpense()