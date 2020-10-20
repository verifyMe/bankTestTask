package bank;

import lombok.Getter;
import lombok.NonNull;

import java.util.Currency;

/**
 * Abstract bank product
 */
public abstract class BankProduct {

    @Getter
    protected String currencyCode;
    @Getter
    protected float balance;
    @Getter
    protected String name;

    /**
     * Creates product with specified currency and balance
     * @param currencyCode ISO 4217 currency code
     * @param balance initial balance
     * @param name product name
     *
     * @throws IllegalArgumentException in case of negative balance
     * @throws NullPointerException in case of null arguments
     */
    protected BankProduct(@NonNull String currencyCode, float balance, @NonNull String name){
        verifyArguments(currencyCode, balance);
        this.currencyCode = currencyCode;
        this.balance = balance;
        this.name = name;
    }

    /**
     * Creates product with 0.0f balance
     * @param currencyCode ISO 4217 currency code
     * @param name product name
     */
    public BankProduct(@NonNull String currencyCode, @NonNull String name){
        this(currencyCode,0f, name);
    }

    /**
     * Refill balance
     * @param money money to add to the balance
     *
     * @throws IllegalArgumentException in case of negative argument
     */
    public void refill(float money){
        if(money < 0)
            throw new IllegalArgumentException("Amount can't be negative");
        this.balance += money;
    }

    private void verifyArguments(String currencyCode, float balance){
        Currency.getInstance(currencyCode);
        if(balance < 0)
            throw new IllegalArgumentException("Balance can't be negative");
    }
}
