/**
 * @author - Roman Balayar
 * LAb-4
 * CS251
 * Represents a bank account with an account number and balance.
 */
public class BankAccount {
    private int accountNumber; // The account number
    private double balance;    // The current balance

    /**
     * Constructs a new bank account with the given account number.
     * @param accountNumber The account number for this bank account
     */
    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    /**
     * Gets the account number of this bank account.
     * @return The account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets the current balance of this bank account.
     * @return The balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposits funds into this bank account.
     * @param amount The amount to deposit
     * @throws IllegalArgumentException If the amount is negative
     */
    public void depositFunds(double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Attempted to deposit negative amount: " + amount);
        }
        balance += amount;
    }

    /**
     * Withdraws funds from this bank account.
     * @param amount The amount to withdraw
     * @throws InsufficientFundsException If there are insufficient funds to withdraw the specified amount
     * @throws IllegalArgumentException If the amount is negative
     */
    public void withdrawFunds(double amount) throws InsufficientFundsException, IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Attempted to withdraw negative amount: " + amount);
        }
        if (balance < amount) {
            throw new InsufficientFundsException(amount - balance);
        }
        balance -= amount;
    }
}
