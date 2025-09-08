import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BlackjackGUI {
    private JFrame frame;
    private JPanel mainPanel, gamePanel;
    private CardLayout cardLayout;

    private BlackjackGame game;
    private JLabel gameStateLabel;
    private JPanel playerCardsPanel, dealerCardsPanel;
    private JLabel playerTotalLabel, dealerTotalLabel, playerMoneyLabel, playerBetLabel;
    private JButton hitButton, standButton;

    private Map<String, Integer> backgrounds;
    private String selectedBackground = "Default";

    public BlackjackGUI(BlackjackGame game) {
        this.game = game;

        frame = new JFrame("Blackjack Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createIntroPanel(), "Intro");
        mainPanel.add(createGamePanel(), "Game");
        mainPanel.add(createShopPanel(), "Shop");
        mainPanel.add(createEndPanel(), "End");

        frame.add(mainPanel);
        frame.setVisible(true);

        cardLayout.show(mainPanel, "Intro");
    }

    private JPanel createIntroPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Blackjack!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        panel.add(titleLabel, BorderLayout.NORTH);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            String playerName = JOptionPane.showInputDialog(frame, "Enter your name:", "Player 1");
            int startingCash = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter your starting cash:", "1000"));
            game.initializeGame(playerName, startingCash);
            resetGamePanel();
            cardLayout.show(mainPanel, "Game");
        });
        panel.add(startButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createGamePanel() {
        gamePanel = new JPanel(new BorderLayout());

        gameStateLabel = new JLabel("Game State", SwingConstants.CENTER);
        gameStateLabel.setFont(new Font("Serif", Font.BOLD, 24));
        gamePanel.add(gameStateLabel, BorderLayout.NORTH);

        playerCardsPanel = new JPanel();
        playerCardsPanel.setLayout(new BoxLayout(playerCardsPanel, BoxLayout.Y_AXIS));
        dealerCardsPanel = new JPanel();
        dealerCardsPanel.setLayout(new BoxLayout(dealerCardsPanel, BoxLayout.Y_AXIS));

        playerTotalLabel = new JLabel("Player Total: 0");
        dealerTotalLabel = new JLabel("Dealer Total: 0");
        playerMoneyLabel = new JLabel("Money: $0");
        playerBetLabel = new JLabel("Bet: $0");

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.add(playerCardsPanel);
        centerPanel.add(dealerCardsPanel);

        gamePanel.add(centerPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.add(playerMoneyLabel);
        infoPanel.add(playerBetLabel);
        gamePanel.add(infoPanel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        hitButton = new JButton("Hit");
        hitButton.addActionListener(e -> {
            Player player = game.getPlayer();
            player.addCard(game.getDeck().nextCard());
            updateCards(player, playerCardsPanel, playerTotalLabel);
            if (player.getTotal() > 21) {
                JOptionPane.showMessageDialog(frame, player.getName() + " busts!");
                player.bust();
                updatePlayerInfoLabels();
                endRound();
            }
        });

        standButton = new JButton("Stand");
        standButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Dealer plays...");
            game.getDealer().dealerPlay(game.getDeck());
            updateCards(game.getDealer(), dealerCardsPanel, dealerTotalLabel);
            determineWinner();
        });

        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);

        return gamePanel;
    }

    private JPanel createShopPanel() {
        JPanel shopPanel = new JPanel(new GridLayout(4, 1));

        JLabel shopTitle = new JLabel("Welcome to the Shop!", SwingConstants.CENTER);
        shopTitle.setFont(new Font("Serif", Font.BOLD, 24));
        shopPanel.add(shopTitle);

        initializeShopItems();

        JButton backgroundButton = new JButton("Purchase Background ($500.00 each)");
        backgroundButton.addActionListener(e -> {
            String[] options = backgrounds.keySet().toArray(new String[0]);
            String selected = (String) JOptionPane.showInputDialog(
                    frame,
                    "Choose a background:",
                    "Background Shop",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            if (selected != null) {
                selectedBackground = selected;
                applyBackgroundTheme(selectedBackground);
                JOptionPane.showMessageDialog(frame, "Applied " + selectedBackground + " theme!");
            }
        });
        shopPanel.add(backgroundButton);

        JButton returnButton = new JButton("Return to Game");
        returnButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Game");
            resetGamePanel();
        });
        shopPanel.add(returnButton);

        return shopPanel;
    }

    private JPanel createEndPanel() {
        JPanel panel = new JPanel();
        JLabel endLabel = new JLabel("Game Over. Thanks for playing!", SwingConstants.CENTER);
        panel.add(endLabel);
        return panel;
    }

    private void initializeShopItems() {
        backgrounds = new HashMap<>();
        backgrounds.put("Beach", 500);
        backgrounds.put("Casino", 500);
        backgrounds.put("Forest", 500);
    }

    private void applyBackgroundTheme(String theme) {
        Color bgColor = switch (theme) {
            case "Beach" -> new Color(244, 164, 96);
            case "Casino" -> new Color(220, 20, 60);
            case "Forest" -> new Color(0, 100, 0);
            default -> Color.LIGHT_GRAY;
        };

        frame.getContentPane().setBackground(bgColor);
        setComponentBackground(mainPanel, bgColor);
        setComponentBackground(gamePanel, bgColor);
        frame.repaint();
    }

    private void setComponentBackground(Component component, Color bgColor) {
        if (component instanceof JPanel) {
            component.setBackground(bgColor);
            for (Component child : ((JPanel) component).getComponents()) {
                setComponentBackground(child, bgColor);
            }
        }
    }

    private void determineWinner() {
        Player player = game.getPlayer();
        Dealer dealer = game.getDealer();

        int playerTotal = player.getTotal();
        int dealerTotal = dealer.calculateTotal();

        String result;
        if (playerTotal > 21) {
            result = "Player busts! Dealer wins.";
            player.loss();
        } else if (dealerTotal > 21 || playerTotal > dealerTotal) {
            result = "Player wins!";
            player.win();
        } else if (playerTotal == dealerTotal) {
            result = "Push!";
            player.push();
        } else {
            result = "Dealer wins!";
            player.loss();
        }

        JOptionPane.showMessageDialog(frame, result);
        updatePlayerInfoLabels();
        endRound();
    }

    private void resetGamePanel() {
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
        game.clearHands();
        game.shuffle();
        game.dealCards();
        updateGamePanel();
    }

    private void updateCards(Player player, JPanel cardsPanel, JLabel totalLabel) {
        cardsPanel.removeAll();
        for (int i = 0; i < player.getHand().getNumberOfCards(); i++) {
            Card card = player.getHand().getCard(i);
            JLabel cardLabel = new JLabel(card.getValueName() + " (" + card.getValue() + ") of " + card.getSuitName());
            cardsPanel.add(cardLabel);
        }
        totalLabel.setText("Player Total: " + player.getTotal());
        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private void updateCards(Dealer dealer, JPanel cardsPanel, JLabel totalLabel) {
        cardsPanel.removeAll();
        for (int i = 0; i < dealer.getHand().getNumberOfCards(); i++) {
            Card card = dealer.getHand().getCard(i);
            JLabel cardLabel = new JLabel(card.getValueName() + " (" + card.getValue() + ") of " + card.getSuitName());
            cardsPanel.add(cardLabel);
        }
        totalLabel.setText("Dealer Total: " + dealer.calculateTotal());
        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private void updateGamePanel() {
        updateCards(game.getPlayer(), playerCardsPanel, playerTotalLabel);
        updateCards(game.getDealer(), dealerCardsPanel, dealerTotalLabel);
        updatePlayerInfoLabels();
    }

    private void updatePlayerInfoLabels() {
        Player player = game.getPlayer();
        playerMoneyLabel.setText("Money: $" + String.format("%.2f", player.getBank()));
        playerBetLabel.setText("Current Bet: $" + player.getBet());
    }

    private void endRound() {
        hitButton.setEnabled(false);
        standButton.setEnabled(false);

        String[] options = {"Play Again", "Shop", "Quit"};
        int choice = JOptionPane.showOptionDialog(
                frame,
                "Round over! What would you like to do?",
                "End of Round",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {
            resetGamePanel();
        } else if (choice == 1) {
            cardLayout.show(mainPanel, "Shop");
        } else {
            cardLayout.show(mainPanel, "End");
        }
    }
}
