import java.util.Scanner;

public class BlackjackGame {

	private Scanner ki = new Scanner(System.in);
	private int users;
	private Player[] players;
	private Deck deck;
	private Dealer dealer = new Dealer();
	private int gamesPlayed = 0;  // Track number of games played
	private Player bestPlayer = null;  // Track player with the highest money
	private InGameShop shop = new InGameShop();

	// Starts game and displays the rules
	public void initializeGame() {
		String names;
		int startingBank;

		System.out.println("Welcome to Blackjack!");
		System.out.println("");
		System.out.println("  BLACKJACK RULES: ");
		System.out.println("    -Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down.");
		System.out.println("    -Cards are equal to their value with face cards being 10 and an Ace being 1 or 11.");
		System.out.println("    -The players cards are added up for their total.");
		System.out.println("    -Players Hit to gain another card from the deck. Players Stay to keep their current card total.");
		System.out.println("    -Dealer Hits until they equal or exceed 17.");
		System.out.println("    -The goal is to have a higher card total than the dealer without going over 21.");
		System.out.println("    -If the player total equals the dealer total, it is a Push and the hand ends.");
		System.out.println("    -Players win their bet if they beat the dealer. Players win 1.5x their bet if they get Blackjack which is 21.");
		System.out.println("");
		System.out.println("  SHOP FEATURES: ");
		System.out.println("    -Players can visit the shop after each hand.");
		System.out.println("    -Card Skins and Music Packs are available for purchase using your winnings.");
		System.out.println("    -Different Card Skins give your cards a unique appearance.");
		System.out.println("    -Music Packs add atmosphere to your gameplay experience.");
		System.out.println("");
		System.out.println("");

		// Gets the amount of players and creates them
		do {
			System.out.print("How many people are playing (1-6)? ");
			while (!ki.hasNextInt()) {
				System.out.println("Please enter a number between 1 and 6");
				ki.next(); // Clear invalid input
			}
			users = ki.nextInt();
		} while (users > 6 || users < 1);

		players = new Player[users];
		deck = new Deck();

		// Asks for player names and starting bank amounts
		for (int i = 0; i < users; i++) {
			System.out.print("What is player " + (i + 1) + "'s name? ");
			names = ki.next();

			// Get and validate starting bank amount
			do {
				System.out.print("How much would " + names + " like to start with? $");
				try {
					String input = ki.next();
					// Check if input is numeric and within reasonable bounds
					if (input.matches("\\d+") && input.length() <= 9) {  // Limit to 9 digits (up to 999,999,999)
						startingBank = Integer.parseInt(input);
						if (startingBank <= 0) {
							System.out.println("Starting amount must be greater than 0. Please try again.");
							continue;
						}
						break;  // Valid input received
					} else {
						System.out.println("Please enter a valid amount between 1 and 999,999,999");
						ki.nextLine(); // Clear the scanner buffer
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input. Please enter a number between 1 and 999,999,999");
					ki.nextLine(); // Clear the scanner buffer
				}
			} while (true);

			players[i] = new Player(startingBank);
			players[i].setName(names);
			System.out.println(names + " starts with $" + startingBank);
			System.out.println();
		}
	}


	// Shuffles the deck
	public void shuffle() throws InvalidDeckPositionException, InvalidCardSuitException, InvalidCardValueException {
		deck.shuffle();
	}

	public void getBets() {
		int betValue;

		for (int i = 0; i < users; i++) {
			if (players[i].getBank() > 0) {
				do {
					System.out.print("How much do you want to bet " + players[i].getName() +
							" (1-" + players[i].getBank() + ")? ");

					// First check if input is actually an integer
					if (!ki.hasNextInt()) {
						System.out.println("Please enter a valid number, not letters or special characters.");
						ki.next(); // Clear the invalid input
						continue;
					}

					try {
						String input = ki.next();
						// Check if input is numeric and within reasonable bounds
						if (input.matches("\\d+") && input.length() <= 9) {
							betValue = Integer.parseInt(input);
							if (betValue > 0 && betValue <= players[i].getBank()) {
								players[i].setBet(betValue);
								break;  // Valid bet received
							} else {
								System.out.println("Bet must be between 1 and " + players[i].getBank());
							}
						} else {
							System.out.println("Please enter a valid bet amount between 1 and " + players[i].getBank());
						}
					} catch (NumberFormatException e) {
						System.out.println("Please enter a valid number.");
						ki.nextLine(); // Clear the scanner buffer
					}
				} while (true);
				System.out.println("");
			}
		}
	}

	// Deals the cards to the players and dealer
	public void dealCards() {
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < users; i++) {
				if(players[i].getBank() > 0) {
					players[i].addCard(deck.nextCard());
				}
			}
			dealer.addCard(deck.nextCard());
		}
	}

	// Initial check for dealer or player Blackjack
	public void checkBlackjack() {
		if (dealer.isBlackjack()) {
			System.out.println("Dealer has BlackJack!");
			for (int i = 0; i < users; i++) {
				if (players[i].getTotal() == 21) {
					System.out.println(players[i].getName() + " pushes");
					players[i].push();
				} else {
					System.out.println(players[i].getName() + " loses");
					players[i].bust();
				}
			}
		} else {
			if (dealer.peek()) {
				System.out.println("Dealer peeks and does not have a BlackJack");
			}

			for (int i = 0; i < users; i++) {
				if (players[i].getTotal() == 21) {
					System.out.println(players[i].getName() + " has blackjack!");
					players[i].blackjack();
				}
			}
		}
	}

	// Offer double down option to player
	public void offerDoubleDown(Player player) {
		if (player.getBank() >= player.getBet() && player.getTotal() >= 9 && player.getTotal() <= 11) {
			System.out.print(player.getName() + ", would you like to double down? (Y/N): ");
			String response = ki.next().toUpperCase();

			if (response.charAt(0) == 'Y') {
				player.doubleDown();
				player.addCard(deck.nextCard());
				System.out.println(player.getName() + " has " + player.getHandString());
			}
		}
	}

	// Offer split option to player
	public void offerSplit(Player player) {
		if (player.canSplit()) {
			System.out.print(player.getName() + ", would you like to split? (Y/N): ");
			String response = ki.next().toUpperCase();

			if (response.charAt(0) == 'Y') {
				player.split();

				// Deal one card to each hand
				player.addCard(deck.nextCard());
				player.getSplitHand().addCard(deck.nextCard());

				// Play first hand
				System.out.println("\nPlaying first hand:");
				playHand(player, player.getHand());

				// Play second hand
				System.out.println("\nPlaying second hand:");
				playHand(player, player.getSplitHand());
			}
		}
	}

	// Play a single hand (used for regular play and split hands)
	private void playHand(Player player, Hand hand) {
		System.out.println(player.getName() + " has Cards:" + hand.toString());

		String command;
		char c;
		do {
			do {
				System.out.print(player.getName() + " (H)it or (S)tand? ");
				command = ki.next();
				c = command.toUpperCase().charAt(0);
			} while (!(c == 'H' || c == 'S'));

			if (c == 'H') {
				hand.addCard(deck.nextCard());
				System.out.println(player.getName() + " has Cards:" + hand.toString());
			}
		} while (c != 'S' && hand.calculateTotal() <= 21);
	}

	// This code takes the user commands to hit or stand
	public void hitOrStand() {
		for (int i = 0; i < users; i++) {
			if (players[i].getBet() > 0) {
				System.out.println();
				System.out.println(players[i].getName() + " has " + players[i].getHandString());

				// Offer double down and split before regular play
				if (players[i].getTotal() >= 9 && players[i].getTotal() <= 11) {
					offerDoubleDown(players[i]);
				}
				if (players[i].canSplit()) {
					offerSplit(players[i]);
				}

				// Only continue with regular play if player didn't split
				if (!players[i].hasSplit()) {
					String command;
					char c;
					do {
						do {
							System.out.print(players[i].getName() + " (H)it or (S)tand? ");
							command = ki.next();
							c = command.toUpperCase().charAt(0);
						} while (!(c == 'H' || c == 'S'));
						if (c == 'H') {
							players[i].addCard(deck.nextCard());
							System.out.println(players[i].getName() + " has " + players[i].getHandString());
						}
					} while (c != 'S' && players[i].getTotal() <= 21);
				}
			}
		}
	}

	// Code for the dealer to play
	public void dealerPlays() {
		boolean isSomePlayerStillInTheGame = false;
		for (int i = 0; i < users && !isSomePlayerStillInTheGame; i++) {
			if (players[i].getBet() > 0 && players[i].getTotal() <= 21) {
				isSomePlayerStillInTheGame = true;
			}
		}
		if (isSomePlayerStillInTheGame) {
			dealer.dealerPlay(deck);
		}
	}

	// Settle a single hand
	private void settleHand(Player player, Hand hand, int bet) {
		if (hand.calculateTotal() > 21) {
			System.out.println(player.getName() + " has busted");
			player.bust();
		} else if (hand.calculateTotal() == dealer.calculateTotal()) {
			System.out.println(player.getName() + " has pushed");
			player.push();
		} else if (hand.calculateTotal() < dealer.calculateTotal() && dealer.calculateTotal() <= 21) {
			System.out.println(player.getName() + " has lost");
			player.loss();
		} else if (hand.calculateTotal() == 21) {
			System.out.println(player.getName() + " has won with blackjack!");
			player.blackjack();
		} else {
			System.out.println(player.getName() + " has won");
			player.win();
		}
	}

	// Modified settleBets to handle split hands
	public void settleBets() {
		System.out.println();

		for (int i = 0; i < users; i++) {
			if (players[i].getBet() > 0) {
				settleHand(players[i], players[i].getHand(), players[i].getBet());

				if (players[i].hasSplit()) {
					settleHand(players[i], players[i].getSplitHand(), players[i].getSplitBet());
					players[i].clearSplitHand();
				}
			}
		}
	}

	// This prints the players hands
	public void printStatus() {
		for (int i = 0; i < users; i++) {
			if(players[i].getBank() > 0) {
				System.out.println(players[i].getName() + " has " + players[i].getHandString());
			}
		}
		System.out.println("Dealer has " + dealer.getHandString(true, true));
	}

	// This prints the players banks and tells the player if they are out of the game
	public void printMoney() {
		for (int i = 0; i < users; i++) {
			if(players[i].getBank() > 0) {
				System.out.println(players[i].getName() + " has $" + players[i].getBank());
			}
			if(players[i].getBank() == 0) {
				System.out.println(players[i].getName() + " has $" + players[i].getBank() + " and is out of the game.");
				players[i].removeFromGame();
			}
		}
	}

	// Sequential search to find player by name
	public Player findPlayerByName(String searchName) {
		if (searchName == null || searchName.trim().isEmpty()) {
			return null;
		}

		searchName = searchName.toLowerCase().trim();

		for (int i = 0; i < users; i++) {
			if (players[i] != null && players[i].getName() != null) {
				if (players[i].getName().toLowerCase().trim().equals(searchName)) {
					return players[i];
				}
			}
		}

		return null;
	}

	// Display player statistics
	public void displayPlayerStats(String playerName) {
		Player player = findPlayerByName(playerName);

		if (player != null) {
			System.out.println("\n=== Player Statistics ===");
			System.out.println("Name: " + player.getName());
			System.out.println("Current Bank: $" + player.getBank());
			System.out.println("Initial Bank: $" + player.getInitialBank());
			System.out.println("Current Bet: $" + player.getBet());

			int profit = player.getBank() - player.getInitialBank();
			if (profit > 0) {
				System.out.println("Total Profit: $" + profit);
			} else if (profit < 0) {
				System.out.println("Total Loss: $" + Math.abs(profit));
			} else {
				System.out.println("Break Even");
			}

			if (player.getBank() > 0) {
				System.out.println("Current Hand: " + player.getHandString());
				System.out.println("Hand Total: " + player.getTotal());
			}

			// Compare to leader
			if (bestPlayer != null) {
				int difference = bestPlayer.getBank() - player.getBank();
				if (difference > 0) {
					System.out.println("$" + difference + " behind the leader (" + bestPlayer.getName() + ")");
				} else if (difference < 0) {
					System.out.println("$" + Math.abs(difference) + " ahead of the leader (" + bestPlayer.getName() + ")");
				} else {
					System.out.println("Tied with the leader");
				}
			}

			System.out.println("=======================\n");
		} else {
			System.out.println("\nNo player found with name: " + playerName);
		}
	}

	// Find and display current leader
	private void findAndDisplayLeader() {
		Player currentLeader = null;
		int highestBank = -1;

		System.out.println("\n=== Current Leader After " + gamesPlayed + " Games ===");

		for (int i = 0; i < users; i++) {
			if (players[i].getBank() > highestBank && players[i].getBank() > 0) {
				highestBank = players[i].getBank();
				currentLeader = players[i];
			}
		}

		if (currentLeader != null) {
			System.out.println("Current Leader: " + currentLeader.getName());
			System.out.println("Current Bank: $" + currentLeader.getBank());
			int profit = currentLeader.getBank() - currentLeader.getInitialBank();
			System.out.println("Profit: $" + profit);
			System.out.println("================================\n");

			// Update all-time best player if applicable
			if (bestPlayer == null || currentLeader.getBank() > bestPlayer.getBank()) {
				bestPlayer = currentLeader;
			}
		} else {
			System.out.println("No current leader - all players are out of the game.");
			System.out.println("================================\n");
		}
	}

	// This code resets all hands
	public void clearHands() {
		for (int i = 0; i < users; i++) {
			players[i].clearHand();
			if (players[i].hasSplit()) {
				players[i].clearSplitHand();
			}
		}
		dealer.clearHand();
	}

	public boolean playAgain() {
		String command;
		char c;
		Boolean playState = true;

		gamesPlayed++;  // Keep your existing game counter

		// Check for leader after every 3 games (keep your existing code)
		if (gamesPlayed % 3 == 0) {
			findAndDisplayLeader();
		}

		if(forceEnd()) {
			playState = false;
		} else {
			do {
				System.out.println("\nWhat would you like to do?");
				System.out.println("1. Visit Shop");
				System.out.println("2. Play Again");
				System.out.println("3. End Game");

				command = ki.next();

				switch(command) {
					case "1":
						visitShop(); // Call the shop method
						System.out.print("\nDo you want to play again (Y/N)? ");
						c = ki.next().toUpperCase().charAt(0);
						if(c == 'N') {
							playState = false;
						}
						break;
					case "2":
						playState = true;
						break;
					case "3":
						playState = false;
						break;
					default:
						System.out.println("Invalid choice. Please try again.");
				}
			} while (!command.matches("[123]"));
		}
		return playState;
	}

	private void visitShop() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nWelcome to the Shop!");

		// First select which player is shopping
		System.out.println("\nWhich player is shopping?");
		for (int i = 0; i < users; i++) {
			if (players[i].getBank() > 0) {
				System.out.println((i+1) + ". " + players[i].getName() + " ($" + players[i].getBank() + ")");
			}
		}

		System.out.print("Enter player number (1-" + users + "): ");
		int playerChoice = scanner.nextInt();
		scanner.nextLine(); // Clear buffer

		if (playerChoice < 1 || playerChoice > users) {
			System.out.println("Invalid player selection.");
			return;
		}

		Player currentPlayer = players[playerChoice - 1];
		System.out.println("\n" + currentPlayer.getName() + "'s current balance: $" + currentPlayer.getBank());

		while(true) {
			System.out.println("\n1. Card Skins");
			System.out.println("2. Music Options");
			System.out.println("3. Exit Shop");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Clear buffer

			switch(choice) {
				case 1:
					shop.displayCardSkins();
					System.out.println("Enter the name of the Card Skin you want to buy");
					String cardSkinName = scanner.nextLine();
					shop.buyCardSkin(currentPlayer, cardSkinName);
					break;

				case 2:
					shop.displayMusicOptions();
					System.out.println("Enter the name of the Music Pack you want to buy");
					String musicOptionName = scanner.nextLine();
					shop.buyMusicOption(currentPlayer, musicOptionName);
					break;

				case 3:
					return;

				default:
					System.out.println("Invalid choice. Try again.");
			}
		}
	}

	// This says true or false to forcing the game to end
	public boolean forceEnd() {
		boolean end = false;
		int endCount = 0;

		for (int i = 0; i < users; i++) {
			if(players[i].getBank() == -1) {
				endCount++;
			}
		}
		if(endCount == users) {
			end = true;
		}
		if(end) {
			System.out.println("");
			System.out.println("All players have lost and the game ends.");
		}

		return end;
	}

	// Modified endGame to include final statistics
	public void endGame() {
		int endAmount;
		String endState = " no change.";
		System.out.println("");
		System.out.println("=== Final Game Statistics ===");
		System.out.println("Total Games Played: " + gamesPlayed);

		if (bestPlayer != null) {
			System.out.println("Best Performing Player: " + bestPlayer.getName());
			System.out.println("Highest Bank Amount: $" + bestPlayer.getBank());
			int bestProfit = bestPlayer.getBank() - bestPlayer.getInitialBank();
			System.out.println("Highest Profit: $" + bestProfit);
		}

		System.out.println("\n=== Individual Results ===");
		for (int i = 0; i < users; i++) {
			if(players[i].getBank() == -1) {
				players[i].resetBank();
			}
			endAmount = players[i].getBank() - players[i].getInitialBank();
			if(endAmount > 0) {
				endState = " gain of ";
			}
			else if(endAmount < 0) {
				endState = " loss of ";
			}
			System.out.println(players[i].getName() + " has ended the game with $" + players[i].getBank());
			if(endState != " no change.") {
				System.out.println("A" + endState + "$" + Math.abs(endAmount));
			}
			else {
				System.out.println("No change from their starting value.");
			}
			System.out.println("");
		}

		System.out.println("\nThank you for playing!");


	}
} //End class