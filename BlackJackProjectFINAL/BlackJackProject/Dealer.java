import java.io.Serializable;

public class Dealer implements Serializable {
	private Hand hand = new Hand();

	public boolean isBlackjack() {
		return hand.calculateTotal() == 21;
	}

	public void dealerPlay(Deck deck) {
		System.out.println("\nDealer's Turn:");
		while (hand.calculateTotal() <= 16) {
			Card card = deck.nextCard();
			hand.addCard(card);
			System.out.println("Dealer draws: " + card);
		}
		if (hand.calculateTotal() > 21) {
			System.out.println("Dealer busts!");
		} else {
			System.out.println("Dealer stands.");
		}
	}

	public void addCard(Card card) {
		hand.addCard(card);
	}

	public Hand getHand() {
		return hand;
	}

	public int calculateTotal() {
		return hand.calculateTotal();
	}

	public void clearHand() {
		hand.clearHand();
	}
}
