package bank.test;

import bank.Card;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.Test;


public class CardTest {

    @Test
    public void testCreateLocalCard(){
        Card card = new Card(0,"My Card");
        assertEquals("RUB", card.getCurrencyCode());
        assertEquals("My Card", card.getName());
        assertThat(card.getBalance(), equalTo(0f));
    }

    @Test
    public void testNegativeBalanceCard(){
       assertThrows(IllegalArgumentException.class, () -> new Card(-100,"My Card"));
        
    }

    @Test
    public void testCreateUSDCard(){
        Card card = new Card("USD", 0,"My Card");
        assertEquals("My Card", card.getName());
        assertEquals("USD", card.getCurrencyCode());
    }

    @Test
    public void testWrongCurrencyCard(){
          assertThrows(IllegalArgumentException.class,  () -> new Card("blahblah", 0,"My Card"));
    }

    @Test
    public void testNullParametersCard(){
         assertThrows(NullPointerException.class,  () ->new Card(null, 0,null));
        
    }

    @Test
    public void testCardWithMoney(){
        Card card = new Card(100,"My card");
        assertEquals("RUB", card.getCurrencyCode());
        assertEquals("My card", card.getName());
        assertThat(card.getBalance(), equalTo(100f));
    }

    @Test
    public void testRefill(){
        Card card = new Card(100,"My card");
        card.refill(100f);
        assertThat(card.getBalance(), equalTo(200f));
    }

    @Test
    public void testRefillNegative(){
        Card card = new Card(100,"My card");        
        assertThrows(IllegalArgumentException.class,  () -> card.refill(-100f));
        assertThat(card.getBalance(), equalTo(100f));
    }

    @Test
    public void testWithdraw(){
        Card card = new Card(100,"My card");
        card.withdraw(50);
        assertThat(card.getBalance(), equalTo(50f));
    }

    @Test
    public void testWithdrawNegative(){
        Card card = new Card(100,"My card");
        assertThrows(UnsupportedOperationException.class,  () -> card.withdraw(150));
        assertThat(card.getBalance(), equalTo(100f));
    }
}
