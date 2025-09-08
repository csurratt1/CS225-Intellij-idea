import java.io.Serializable;

public class Player implements Serializable {
	private double bank; // Changed from int to double
	private int bet;
	private String name;
	private Hand hand;

	public Player(int startingBank) {
		if (startingBank <= 0) {
			throw new IllegalArgumentException("Initial bank amount must be positive");
		}
		bank = startingBank;
		hand = new Hand();
	}

	public Player() {
		this(100); // Default starting bank of 100
	}

	public double getBank() {
		return bank;
	}

	public int getBet() {
		return bet;
	}

	public String getName() {
		return name;
	}

	public Hand getHand() {
		return hand;
	}

	public int getTotal() {
		return hand.calculateTotal();
	}

	public void setName(String name) {
		this.name = name;
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

	public void addCard(Card card) {
		hand.addCard(card);
	}

	public void clearHand() {
		hand.clearHand();
	}

	public void purchaseItem(double cost) {
		if (bank >= cost) {
			bank -= cost;
			System.out.println("Purchase successful! Remaining balance: $" + bank);
		} else {
			throw new IllegalStateException("Insufficient balance for purchase.");
		}
	}

	public void bust() {
		bank -= bet;
		bet = 0;
	}

	public void win() {
		bank += bet;
		bet = 0;
	}

	public void blackjack() {
		bank += bet * 1.5; // Correct calculation for blackjack payout
		bet = 0;
	}

	public void push() {
		bet = 0;
	}

	public void loss() {
		bank -= bet;
		bet = 0;
	}
}
