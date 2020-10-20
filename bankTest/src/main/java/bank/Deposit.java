package bank;

/**
 * Deposit product
 */
public class Deposit extends BankProduct {

    private boolean isClosed;

    /**
     * Creates deposit
     * @param currencyCode ISO 4217 currency code
     * @param balance initial balance
     * @param name product name
     *
     * @throws IllegalArgumentException in case of negative balance
     * @throws NullPointerException in case of null arguments
     */
    public Deposit(String currencyCode, float balance, String name) {
        super(currencyCode, balance, name);
        isClosed = false;
    }

    /**
     * Close deposit in case of it is open
     * @throws UnsupportedOperationException in case od closed
     */
    public void close() throws UnsupportedOperationException{
        if(isClosed)
            throw new UnsupportedOperationException("Deposit is closed already");
        isClosed = true;
    }
}
