package bank.test;

import bank.CreditCard;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CreditCardTest {

    @Test
    public void createLocalCreditCard(){
        CreditCard card = new CreditCard("My card", 100f, 0.05f);
        assertEquals("My card", card.getName());
        assertThat(card.getDebt(), equalTo(0f));
        assertThat(card.getLimit(), equalTo(100f));
        assertThat(card.getBalance(), equalTo(0f));
        assertThat(card.getRate(), equalTo(0.05f));
    }

    @Test
    public void creditCardRefill(){
        CreditCard card = new CreditCard("My card", 100f, 0.05f);
        card.refill(100f);
        assertThat(card.getDebt(), equalTo(0f));
        assertThat(card.getLimit(), equalTo(100f));
        assertThat(card.getBalance(), equalTo(100f));
    }

    @Test
    public void creditCardWithdraw(){
        CreditCard card = new CreditCard("My card", 100f, 0.05f);
        card.refill(100f);
        card.withdraw(150f);
        assertThat(card.getDebt(), equalTo(50f));
        assertThat(card.getLimit(), equalTo(100f));
        assertThat(card.getBalance(), equalTo(-50f));
    }

    @Test
    public void creditCardWithdrawAboveTheLimit(){
        CreditCard card = new CreditCard("My card", 100f, 0.05f);
        card.refill(100f);
        boolean isThrown = false;
        try {
            card.withdraw(350f);
        }catch (UnsupportedOperationException e){
            isThrown = true;
        }
        assertTrue(isThrown);
    }

    @Test
    public void creditCardRefillAndWithdrawAboveTheLimit(){
        CreditCard card = new CreditCard("My card", 100f, 0.05f);
        card.refill(350f);
        card.withdraw(350f);
        assertThat(card.getDebt(), equalTo(0f));
        assertThat(card.getLimit(), equalTo(100f));
        assertThat(card.getBalance(), equalTo(0f));
    }

    @Test
    public void creditCardWithdrawLimit(){
        CreditCard card = new CreditCard("My card", 100f, 0.05f);
        card.withdraw(100f);
        assertThat(card.getDebt(), equalTo(100f));
        assertThat(card.getLimit(), equalTo(100f));
        assertThat(card.getBalance(), equalTo(-100f));
    }
}
