import java.io.Serializable;
import java.util.Random;
import java.util.Stack;

public class Deck extends Exception implements Serializable {
	private int nextCardIndex;
	private final int NUM_CARDS_PER_DECK = 52;
	private int numberOfDecks;
	private Card[] deck;
	private Stack<Card> discardPile;

	// Constructor that allows multiple decks
	public Deck(int numDecks) {
		if (numDecks < 1) {
			throw new IllegalArgumentException("Must have at least one deck");
		}

		numberOfDecks = numDecks;
		deck = new Card[NUM_CARDS_PER_DECK * numberOfDecks];
		discardPile = new Stack<>();
		initializeDecks();
	}

	// Default constructor - single deck
	public Deck() {
		this(1);
	}

	// Initialize all decks
	private void initializeDecks() {
		int count = 0;
		try {
			for (int deckNum = 0; deckNum < numberOfDecks; deckNum++) {
				// Hearts
				for (int i = 1; i <= 13; i++) {
					deck[count++] = new Card('H', i);
				}
				// Spades
				for (int i = 1; i <= 13; i++) {
					deck[count++] = new Card('S', i);
				}
				// Clubs
				for (int i = 1; i <= 13; i++) {
					deck[count++] = new Card('C', i);
				}
				// Diamonds
				for (int i = 1; i <= 13; i++) {
					deck[count++] = new Card('D', i);
				}
			}
		} catch(InvalidCardValueException | InvalidCardSuitException exp1) {
			System.out.println("Error initializing deck: " + exp1.getMessage());
		}
		nextCardIndex = 0;
	}

	private void isIndexGood(int index) throws InvalidDeckPositionException {
		if (index < 0 || index >= deck.length) {
			throw new InvalidDeckPositionException(index);
		}
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < deck.length; i++) {
			str.append(deck[i].toString()).append(" ");
		}
		return str.toString();
	}

	private void swapCards(int index1, int index2) throws InvalidDeckPositionException {
		isIndexGood(index1);
		isIndexGood(index2);
		Card hold = deck[index1];
		deck[index1] = deck[index2];
		deck[index2] = hold;
	}

	public void shuffle() throws InvalidDeckPositionException {
		// Return all cards from discard pile to main deck
		while (!discardPile.isEmpty()) {
			deck[nextCardIndex++] = discardPile.pop();
		}
		nextCardIndex = 0;

		// Perform the shuffle
		Random rn = new Random();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < deck.length; j++) {
				swapCards(j, rn.nextInt(deck.length));
			}
		}
	}

	public Card getCard(int index) throws InvalidDeckPositionException {
		isIndexGood(index);
		return deck[index];
	}

	public boolean compareTo(Deck otherDeck) throws InvalidDeckPositionException {
		if (this.deck.length != otherDeck.deck.length) {
			return false;
		}
		for (int i = 0; i < deck.length; i++) {
			if (!deck[i].compareTo(otherDeck.getCard(i))) {
				return false;
			}
		}
		return true;
	}

	public Card nextCard() {
		if (nextCardIndex < 0 || nextCardIndex >= deck.length) {
			throw new IllegalStateException("No more cards in deck. Need to shuffle.");
		}
		Card card = deck[nextCardIndex++];
		discardPile.push(card);
		return card;
	}

	// New methods for deck and discard pile management

	public int getNumberOfDecks() {
		return numberOfDecks;
	}

	public int getRemainingCards() {
		return deck.length - nextCardIndex;
	}

	public int getDiscardPileSize() {
		return discardPile.size();
	}

	public boolean needsShuffle() {
		// Recommend shuffle when 75% of cards have been dealt
		return getRemainingCards() < (deck.length * 0.25);
	}

	public void clearDiscardPile() {
		discardPile.clear();
	}

	public Stack<Card> getDiscardPile() {
		return discardPile;
	}
}