import java.io.Serializable;
import java.util.Random;
import java.util.Stack;

public class Deck implements Serializable {

	private Card[] deck;
	private int nextCardIndex;
	private Stack<Card> discardPile;

	public Deck() {
		deck = new Card[52];
		initializeDeck();
		shuffle();
	}

	private void initializeDeck() {
		int count = 0;
		try {
			// Initialize cards for Hearts, Spades, Clubs, and Diamonds
			for (char suit : new char[]{'H', 'S', 'C', 'D'}) {
				for (int value = 1; value <= 13; value++) {
					deck[count++] = new Card(suit, value);
				}
			}
		} catch (InvalidCardValueException | InvalidCardSuitException e) {
			System.out.println("Error initializing deck: " + e.getMessage());
		}
		nextCardIndex = 0;
		discardPile = new Stack<>();
	}

	public void shuffle() {
		Random rand = new Random();
		for (int i = 0; i < deck.length; i++) {
			int randomIndex = rand.nextInt(deck.length);
			Card temp = deck[i];
			deck[i] = deck[randomIndex];
			deck[randomIndex] = temp;
		}
		nextCardIndex = 0;
	}

	public Card nextCard() {
		if (nextCardIndex >= deck.length) {
			throw new IllegalStateException("No more cards in the deck. Please shuffle.");
		}
		Card card = deck[nextCardIndex++];
		discardPile.push(card);
		return card;
	}

	public void returnToDeck(Stack<Card> discardPile) {
		while (!discardPile.isEmpty()) {
			deck[nextCardIndex++] = discardPile.pop();
		}
		shuffle();
	}
}
