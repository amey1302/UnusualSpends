# Credit Card Alert System

## Overview
This project implements a credit card alert system for detecting unusual spending patterns and triggering alerts to users. The system is designed to provide value-added services to credit card users by notifying them when their spending in any particular category is higher than usual.

## Problem Statement
You work at a credit card company and as a value-add they want to start providing alerts to users when their spending in any particular category is higher than usual.
 - Compare the total amount paid for the current month, grouped by category with the previous month 
 - Filter down to the categories for which the user spent at least 50% more this month than last month
 - Compose an e-mail message to the user that lists the categories for which spending was    unusually high

Sample Email - 

Unusual spending of ₹1076 detected!

Hello Baburao!

We have detected unusually high spending on your card in these categories:

* You spent ₹148 on groceries
* You spent ₹928 on travel

Thanks,

The Credit Card Company

Extensions - 

- Change in Email format
- Change in threshold percentage
- Change in usual spending amount calculation logic
- Adding usual spending amount in email
## Architecture
The system follows an MVC architecture with the following components:

### Controller
1. **CustomerController**: Manages customer operations.
2. **CreditCardController**: Handles credit card operations.
3. **TransactionController**: Manages transaction operations.
4. **UnusualSpendsController**: Detects unusual spending patterns and triggers alerts.

### Service
1. **CustomerService**: Validates and manages customers.
2. **CreditCardService**: Manages credit cards and assignments.
3. **TransactionService**: Performs transaction validations and transaction operations.
4. **UnusualSpendsService**: Detects unusual spends and triggers alerts.

## Repository
1. **CustomerRepository**: Stores valid customer information.
2. **CreditCardRepository**: Stores credit card information.
3.  **TransactionRepository**: Stores information about transactions.

## Domain 
  ### Model
   ### Entity: 
    1. Customer: Represents customer data, including id, name, and email.
    2. CreditCard: Stores credit card information, linked to a customer.
    3. Transaction: Contains transaction details such as id, credit card id, amount, category, and date.
  ### value objects
    Category 
  ### Service
    1. SpendAnalyzer: Analyzes transaction data for unusual spending patterns.
    2. EmailComposer : composes email
    3. AlertService: sends email alerts to users.    

