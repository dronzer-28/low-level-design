package splitwise.src;

import java.util.List;

/**
 * ExactExpenseType
 */
public class ExactExpenseType implements ExpenseType{
    Double value;
    User payer;
    List<User> userInvolved;
    List<Double> shareAmt;

    public ExactExpenseType(String[] query){

    }

    @Override
    public void validateExpense() throws IllegalArgumentException {
        
    }

    @Override
    public void addExpense() {
        
    }
    
}