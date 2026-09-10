package splitwise;
// ##Requirements
// User: Each user should have a userId, name, email, mobile number.
// Expense: Could either be EQUAL, EXACT or PERCENT
// Users can add any amount, select any type of expense and split with any of the available users.
// The percent and amount provided could have decimals upto two decimal places.
// In case of percent, you need to verify if the total sum of percentage shares is 100 or not.
// In case of exact, you need to verify if the total sum of shares is equal to the total amount or not.
// The application should have a capability to show expenses for a single user as well as balances for everyone.
// When asked to show balances, the application should show balances of a user with all the users where there is a non-zero balance.
// The amount should be rounded off to two decimal places. Say if User1 paid 100 and amount is split equally among 3 people. Assign 33.34 to first person and 33.33 to others.
// Out of scope: GUI, input


// ##Entities and Relationships
// - User: denotes a single user entity
// - UserBalances: manages the balances user is a part of.
// - Splitwise: Orchestrator, will have methods of add expense and show balances
// - Enum: ExpenseType (EQUAL, EXACT, PERCENT)
// - Enum: OperationType (SHOW, EXPENSE)


// ##Class Design
// User:
//      - userId: String
//      - name: String
//      - email: String
//      - mobNo: String
//      - balances: Map<String,Double>
//      + User(userId, name, email, mobNo)
//      + addTransaction(userId, value)
//      + getters & setters

// Constants:
//      enum SupportedExpenseType
//      enum OperationType

// interface Balances:
//      + appConfig: AppConfig
//      + getBalances() -> List<String>

// UserBalances:
//      - user : User
//      + UserBalances(userId)
//      + getBalances() -> List<String>

// AllUserBalances:
//      - users: Map<String, User>
//      + AllUserBalances()
//      + getBalance() -> List<String>

// SplitWise:
//      - appConfig: AppConfig
//      - expenseType: ExpenseType
//      - balances: Balances
//      + Splitwise()
//      + addUser(userId, name, email, mobNo) -> void
//      + getBalance(showInput: List<String>) -> List<String>
//      + addExpense(addInput: List<String>) -> void

// AppConfig:
// - users: Map<String, User>
// - instance: AppConfig
// + synchornized getInstance(): instance
// + getUserById(userId) -> User
// + getAllUsers() -> Collections.unmodifiableMap(users);
// + synchornized addUser(userId, name, email, mobNo) -> void

// interface ExpenseType:
//      + appConfig: AppConfig
//      + validateExpense()
//      + addExpense()

// EqualExpenseType implements ExpenseType
// - value: int
// - payer: User
// - involved: List<User>
// + EqualExpenseType(query: List<String>)
// + validateExpense()
// + addExpense()

// ExactExpenseType implements ExpenseType
// - value: int
// - payer: User
// - involved: List<User>
// - shareAmt: List<Double>
// + EqualExpenseType(query: List<String>)
// + validateExpense()
// + addExpense()


// PercentExpenseType implements ExpenseType
// - value: int
// - payer: User
// - involved: List<User>
// - sharePgt: List<Double>
// + EqualExpenseType(query: List<String>)
// + validateExpense()
// + addExpense()



// Explaination: when we call show for a userId through Splitwise.getBalance(showInput), it will check the input len and accordingly we create a object of UserBalances or AllUserBalances, store it in blances variable and then return balances.getBalance()
// when we add a expense, we call Splitwise.addExpense() and then we take out the expense type form the input and accordingly create object of either EqualExpenseType or the others, and then call expenseType.validateExpense and expenseType.addExpense on it.



// Sample Input
// SHOW
// SHOW u1
// EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
// SHOW u4
// SHOW u1