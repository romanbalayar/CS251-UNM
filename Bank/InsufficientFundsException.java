/**
 * @author - Roman Balayar
 * LAB-4
 * CS251
 * Exception thrown when there are insufficient funds in an account
 * for a particular operation.
 */
public class InsufficientFundsException extends Exception {
    private double shortfall;

    /**
     * Constructs an InsufficientFundsException with the given shortfall.
     * @param shortfall The amount by which the funds are insufficient
     */
    public InsufficientFundsException(double shortfall) {
        super("You need more money!");
        this.shortfall = shortfall;
    }

    /**
     * Gets the amount by which the funds are insufficient.
     * @return The shortfall amount
     */
    public double getShortfallAmount() {
        return shortfall;
    }
}
