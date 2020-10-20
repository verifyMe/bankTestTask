package bank.test;

import bank.Card;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;


public class CardTest {
    private boolean isThrown = false;

    @Before
    public void setNotThrown(){
        isThrown = false;
    }

    @Test
    public void testCreateLocalCard(){
        Card card = new Card(0,"My Card");
        assertEquals("RUB", card.getCurrencyCode());
        assertEquals("My Card", card.getName());
        assertThat(card.getBalance(), equalTo(0f));
    }

    @Test
    public void testNegativeBalanceCard(){
        try{
            new Card(-100,"My Card");
        }catch (IllegalArgumentException e){
            isThrown = true;
        }
        assertTrue(isThrown);
    }

    @Test
    public void testCreateUSDCard(){
        Card card = new Card("USD", 0,"My Card");
        assertEquals("My Card", card.getName());
        assertEquals("USD", card.getCurrencyCode());
    }

    @Test
    public void testWrongCurrencyCard(){
        try{
            new Card("blahblah", 0,"My Card");
        }catch (IllegalArgumentException e){
            isThrown = true;
        }
        assertTrue(isThrown);
    }

    @Test
    public void testNullParametersCard(){
        try{
            new Card(null, 0,null);
        }catch (NullPointerException e){
            isThrown = true;
        }
        assertTrue(isThrown);
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
        try {
            card.refill(-100f);
        }
        catch (IllegalArgumentException e){
            isThrown = true;
        }
        assertTrue(isThrown);
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
        try{
            card.withdraw(150);
        }catch (UnsupportedOperationException e){
            isThrown = true;
        }
        assertTrue(isThrown);
        assertThat(card.getBalance(), equalTo(100f));
    }
}
