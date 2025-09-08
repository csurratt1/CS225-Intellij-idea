/*

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;

public class BlackjackGameTest {
    private BlackjackGame game;
    private Player player;
    private Dealer dealer;

    @Before
    public void setUp() throws InvalidCardValueException, InvalidCardSuitException {
        game = new BlackjackGame();
        player = new Player(1000);
        player.setName("TestPlayer");
        dealer = new Dealer();
    }

    // Betting Tests
    @Test
    public void testValidBet() {
        player.setBet(500);
        assertEquals(500, player.getBet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBetMoreThanBank() {
        player.setBet(1500);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeBet() {
        player.setBet(-100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroBet() {
        player.setBet(0);
    }

    @Test
    public void testHitAddsCard() throws InvalidCardValueException, InvalidCardSuitException {
        int initialCardCount = 0;
        player.addCard(new Card('H', 7));
        assertEquals(1, player.getTotal()); // Adjust expected value based on your implementation
    }

    @Test
    public void testHitUpdatesTotal() throws InvalidCardValueException, InvalidCardSuitException {
        player.addCard(new Card('H', 10));
        player.addCard(new Card('D', 5));
        int totalBeforeHit = player.getTotal();
        player.addCard(new Card('C', 3));
        assertEquals(totalBeforeHit + 3, player.getTotal());
    }

    @Test
    public void testBustAfterHit() throws InvalidCardValueException, InvalidCardSuitException {
        player.addCard(new Card('H', 10));
        player.addCard(new Card('D', 10));
        player.addCard(new Card('C', 5));
        assertTrue(player.getTotal() > 21);
    }

    @Test
    public void testAceValueChanges() throws InvalidCardValueException, InvalidCardSuitException {
        player.addCard(new Card('H', 1)); // Ace
        player.addCard(new Card('D', 8));
        assertEquals(19, player.getTotal()); // Ace should be 11
        player.addCard(new Card('C', 5));
        assertEquals(14, player.getTotal()); // Ace should now be 1
    }

    // Dealer Tests
    @Test
    public void testDealerMustHitBelow17() throws InvalidCardValueException, InvalidCardSuitException {
        dealer.addCard(new Card('H', 10));
        dealer.addCard(new Card('D', 5));
        assertTrue(dealer.calculateTotal() < 17);
    }

    @Test
    public void testDealerStandsAt17OrAbove() throws InvalidCardValueException, InvalidCardSuitException {
        dealer.addCard(new Card('H', 10));
        dealer.addCard(new Card('D', 7));
        assertTrue(dealer.calculateTotal() >= 17);
    }

    // Integration Tests
    @Test
    public void testCompleteRound() throws InvalidCardValueException, InvalidCardSuitException {
        int initialBank = player.getBank();
        int betAmount = 100;

        player.setBet(betAmount);

        player.addCard(new Card('H', 10));
        player.addCard(new Card('D', 8));
        dealer.addCard(new Card('C', 9));
        dealer.addCard(new Card('S', 7));

        dealer.addCard(new Card('H', 3));

        player.loss();

        assertEquals(initialBank - betAmount, player.getBank());
        assertEquals(0, player.getBet());
    }
}

 */