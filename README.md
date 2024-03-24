
# Unusual Spends

#### Description :

You work at a credit card company and as a value-add they want to start providing alerts to users when their spending in any particular category is higher than usual.
 - Compare the total amount paid for the current month, grouped by category with the previous month 
 - Filter down to the categories for which the user spent at least 50% more this month than last month
 - Compose an e-mail message to the user that lists the categories for which spending was unusually high

#### OOMD Design for the Credit Card Company

# OOMD For Unusual Spends
 # domain 
  - # DTO 
     - ## UnusualSpendingEmailAlertDTO
        - ### State
          - private Customer customer;
    	  - private Map<Category, Double> unusualSpends;
	      - private double totalUnusualAmount;
  - # Exception
     - creditcardexception : contains all defined exceptions for creditCard Validation
     - customerexception   : contains all defined exceptions for customer Data Validation
     - transactionexceptin : contains all defined exceptions for transaction Data Validation
  - # model
	- ## class Category: enum
    - ## class Customer
      - ### State
		- private final int id;
        - private final String name;
    	- private final String email;
    	- private CreditCard creditCard;
      - ### Behaviour
		- Smart Constructor
		- getters and setters
	- ## class CreditCard
      - ### State
	  	  - private creditcardId  : int
	  	  - private List<Transaction> transactions
	
      - ### Behaviour
        - Smart Constructor 
 		- addTransaction
        - getters and setters
            
  
    - ## class Transaction
        - ### State
		    - private final int transactionId;
            - private final LocalDate date;
            - private final double amount;
            - private final Category category;
        - ### Behaviour
	        - Smart Constructor
            - Transaction Validations
            - getters and setters

    - ## class UnusualSpend
        - ### Behaviour
            -  public static double getTotalAmountSpent()
            -  public static List<Transaction> currentMonthTransactions(List<Transaction> transactions)
            -  public static List<Transaction> previousMonthTransactions(List<Transaction> transactions)
            -  public static Map<Category, Double> calculateUnusualSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage)
            -  public static double totalUnusualAmountSpend(Map<Category, Double> unusualSpends)
            -  public static Map<Category, Double> totalSpending(List<Transaction> transactions)

    - ## Services
        - ## class EmailAlert
            - ### Behaviour
                - public String sendEmail(String subject, String body, String customerEmailId)
        - ## class EmailAlertMessage
            - ### Behaviour
                - public static String generateUnusualSpendingEmail(UnusualSpendingEmailAlertDTO emailDTO)
- ## class CreditCardManager
    - ### Behaviour
        - public Customer createCustomer(int customerId, String customerName, String customerEmail)
        - public void createCreditCardForCustomer(int creditCardId, Customer customer)
        - public void createTransactionForACreditCard(int transactionId, LocalDate date, double amount, Category category, CreditCard creditCard)
        - public boolean analyzeUnusualSpendsFor(CreditCard creditCard)
          







			
		
