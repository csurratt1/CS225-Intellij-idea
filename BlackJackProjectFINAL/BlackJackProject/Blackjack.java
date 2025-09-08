import javax.swing.SwingUtilities;

public class Blackjack {

	public static void main(String[] args) {
		// Launch the game with the GUI
		SwingUtilities.invokeLater(() -> {
			BlackjackGame game = new BlackjackGame();
			new BlackjackGUI(game); // Start the GUI
		});
	}
}
