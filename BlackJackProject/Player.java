import java.io.Serializable;

public class Player implements Serializable {
	private int bank;
	private int bet;
	private String name;
	private Hand hand;
	private int initialBank;
	private Hand splitHand;    // New field for split hand
	private int splitBet;      // New field for split hand bet

	// Constructor with initial bank amount
	public Player(int startingBank) {
		if (startingBank <= 0) {
			throw new IllegalArgumentException("Initial bank amount must be positive");
		}
		bank = startingBank;
		initialBank = startingBank;
		hand = new Hand();
		splitHand = null;      // Initialize split hand as null
	}

	// Default constructor (for backward compatibility)
	public Player() {
		this(100);  // Default starting bank of 100
	}

	// Get methods
	public int getBank() {
		return bank;
	}

	public int getBet() {
		return this.bet;
	}

	public String getName() {
		return name;
	}

	public Hand getHand() {
		return hand;
	}

	public int getInitialBank() {
		return initialBank;
	}

	public int getTotal() {
		return hand.calculateTotal();
	}

	// Set methods
	public void setName(String name1) {
		name = name1;
	}

	public void setBet(int newBet) {
		if (newBet <= 0) {
			throw new IllegalArgumentException("Bet must be positive");
		}
		if (newBet > bank) {
			throw new IllegalArgumentException("Bet cannot exceed bank amount");
		}
		bet = newBet;
	}

	// Hand management methods
	public void addCard(Card card) {
		hand.addCard(card);
	}

	public String getHandString() {
		String str = "Cards:" + hand.toString();
		return str;
	}

	public void clearHand() {
		hand.clearHand();
	}

	// Bank management methods
	public void addToBank(int amount) {
		if (amount > 0) {
			bank += amount;
		} else {
			throw new IllegalArgumentException("Amount to add must be positive");
		}
	}

	public void removeFromGame() {
		bank = -1;
	}

	public void resetBank() {
		bank = 0;
	}

	// Bet resolution methods
	public void bust() {
		bank -= bet;
		bet = 0;
	}

	public void win() {
		bank += bet;
		bet = 0;
	}

	public void loss() {
		bank -= bet;
		bet = 0;
	}

	public void blackjack() {
		bank += bet * 1.5;
		bet = 0;
	}

	public void push() {
		bet = 0;
	}

	// New methods for double down
	public void doubleDown() {
		if (bank >= bet) {
			bank -= bet;  // Remove additional bet from bank
			bet *= 2;     // Double the bet
		} else {
			throw new IllegalStateException("Insufficient funds to double down");
		}
	}

	// New methods for split functionality
	public boolean canSplit() {
		if (hand.getNumberOfCards() != 2) return false;
		Card card1 = hand.getCard(0);
		Card card2 = hand.getCard(1);
		return card1.getValue() == card2.getValue() && bank >= bet;
	}

	public void split() {
		if (!canSplit()) {
			throw new IllegalStateException("Cannot split current hand");
		}
		if (splitHand == null) {
			splitHand = new Hand();
		}
		// Move second card to split hand
		Card secondCard = hand.removeCard(1);
		splitHand.addCard(secondCard);

		// Set split bet equal to original bet
		splitBet = bet;
		bank -= bet;  // Remove the additional bet from bank
	}

	public boolean hasSplit() {
		return splitHand != null && splitHand.getNumberOfCards() > 0;
	}

	public Hand getSplitHand() {
		return splitHand;
	}

	public void clearSplitHand() {
		splitHand = null;
		splitBet = 0;
	}

	public int getSplitBet() {
		return splitBet;
	}

	public void setSplitBet(int newBet) {
		if (newBet <= 0) {
			throw new IllegalArgumentException("Split bet must be positive");
		}
		if (newBet > bank) {
			throw new IllegalArgumentException("Split bet cannot exceed bank amount");
		}
		splitBet = newBet;
	}

	public void purchaseItem(double price){
		if(bank >= price){
			bank -= price;
			System.out.println("Purchase successful");
		}
		else {
			System.out.println("Insufficient balance");
		}
	}
}