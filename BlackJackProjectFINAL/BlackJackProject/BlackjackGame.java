import java.util.Scanner;

public class BlackjackGame {
	private Player player;
	private Dealer dealer;
	private Deck deck;
	private int gamesPlayed;

	public BlackjackGame() {
		dealer = new Dealer();
		deck = new Deck();
		gamesPlayed = 0;
	}

	public void initializeGame(String playerName, int startingCash) {
		player = new Player(startingCash);
		player.setName(playerName);
		System.out.println("Game initialized for player: " + player.getName() + " with $" + player.getBank());
	}

	public void shuffle() {
		deck.shuffle();
		System.out.println("Deck shuffled.");
	}

	public void dealCards() {
		player.clearHand();
		dealer.clearHand();

		player.addCard(deck.nextCard());
		player.addCard(deck.nextCard());
		dealer.addCard(deck.nextCard());
		dealer.addCard(deck.nextCard());
	}

	public void clearHands() {
		player.clearHand();
		dealer.clearHand();
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void incrementGamesPlayed() {
		gamesPlayed++;
	}

	public Player getPlayer() {
		return player;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public Deck getDeck() {
		return deck;
	}
}
