package splitwise.src;

import java.util.List;

/**
 * PercentExpenseType
 */
public class PercentExpenseType implements ExpenseType{
    Double value;
    User payer;
    List<User> userInvolved;
    List<Double> sharePercent;

    public PercentExpenseType(String[] query){

    }

    @Override
    public void validateExpense() throws IllegalArgumentException {
        
    }

    @Override
    public void addExpense() {
        
    }  
}