package bank;

import lombok.Getter;

import java.util.Currency;
import java.util.Locale;

/**
 * Credit card object
 */
public class CreditCard extends Card {
    @Getter
    private float rate;
    @Getter
    private float limit;

    /**
     * Credit card constructor, all fields required
     * @param currencyCode - ISO 4217 currency code
     * @param name credit card name
     * @param limit available funds
     * @param rate card rate
     *
     * @throws IllegalArgumentException in case of invalid argument
     * @throws NullPointerException in case of null parameters
     */
    public CreditCard(String currencyCode,  String name, float limit, float rate) {
        super(currencyCode, 0f, name);
        verifyRate();
        this.rate = rate;
        this.limit = limit;
    }

    /**
     *  Credit card constructor in local currency
     * @param name credit card name
     * @param limit available funds
     * @param rate card rate
     *
     * @throws IllegalArgumentException in case of invalid argument
     * @throws NullPointerException in case of null parameters
     */
    public CreditCard( String name, float limit, float rate) {
        this(Currency.getInstance(Locale.getDefault()).getCurrencyCode(), name, limit, rate);
    }

    /**
     * Get credit card debt
     * @return 0 is balance > credit card limit, difference in other cases
     */
    public float getDebt(){
        if(balance >= 0)
            return 0f;
        return -balance;
    }

    /**
     *
     * {@inheritDoc}
     *
     */
    @Override
    public void withdraw(float amount)throws UnsupportedOperationException{
        if(amount > balance+limit)
            throw new UnsupportedOperationException("Insufficient funds");
        balance -= amount;
    }

    private void verifyRate(){
        if(rate <0)
            throw new IllegalArgumentException("Rate can't be negative");
    }
}
