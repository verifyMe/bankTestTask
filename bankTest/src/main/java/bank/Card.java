package bank;

import java.util.Currency;
import java.util.Locale;

/**
 * Debit card object
 */
public class Card extends BankProduct {

    /**
     * Create debit card in any currency
     * @param currencyCode ISO 4217 currency code
     * @param balance initial balance
     * @param name card name

     * @throws IllegalArgumentException in case of invalid argument
     * @throws NullPointerException in case of null parameters
     */
    public Card(String currencyCode, float balance,String name) {
        super(currencyCode, balance, name);
    }

    /**
     * Create debit card in local currency
     * @param balance initial balance
     * @param name card name

     * @throws IllegalArgumentException in case of invalid argument
     * @throws NullPointerException in case of null parameters
     */
    public Card(float balance, String name) {
        super(Currency.getInstance(Locale.getDefault()).getCurrencyCode(), balance, name);
    }

    /**
     * Withdraw specified amount from balance
     * @param amount funds tu withdraw
     *
     * @throws UnsupportedOperationException in case of operation forbidden or failed
     */
    public void withdraw(float amount) throws UnsupportedOperationException{
        if(amount > balance)
            throw new UnsupportedOperationException("Insufficient funds");
        balance -= amount;
    }

}
