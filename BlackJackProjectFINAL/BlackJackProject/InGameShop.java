import java.util.ArrayList;
import java.util.List;

public class InGameShop {
    private List<CardSkin> cardSkins;
    private List<MusicOption> musicOptions;

    public InGameShop() {
        this.cardSkins = new ArrayList<>();
        this.musicOptions = new ArrayList<>();

        // Initialize card skins
        cardSkins.add(new CardSkin("B & W", "Black and White for that Noir feel", 150));
        cardSkins.add(new CardSkin("Vintage", "Bring it back to the Wild West", 200));
        cardSkins.add(new CardSkin("Gold", "The epitome of riches and class", 1000));

        // Initialize music options
        musicOptions.add(new MusicOption("Festive", "Perfect for Christmas time", 125));
        musicOptions.add(new MusicOption("Rock", "For true metal gamblers", 666));
        musicOptions.add(new MusicOption("Jazz", "Bring it back to the '40s", 1940));
    }

    public void displayCardSkins() {
        System.out.println("Here are your options:");
        for (CardSkin cardSkin : cardSkins) {
            System.out.println(cardSkin.getSkinName() + ": " + cardSkin.getSkinDescription() +
                    " - $" + cardSkin.getSkinPrice());
        }
    }

    public void displayMusicOptions() {
        System.out.println("Here are your options:");
        for (MusicOption musicOption : musicOptions) {
            System.out.println(musicOption.getOptionName() + ": " + musicOption.getOptionDescription() +
                    " - $" + musicOption.getOptionPrice());
        }
    }

    public void buyCardSkin(Player player, String skinName) {
        CardSkin selectedSkin = null;
        for (CardSkin cardSkin : cardSkins) {
            if (cardSkin.getSkinName().equalsIgnoreCase(skinName)) {
                selectedSkin = cardSkin;
                break;
            }
        }

        if (selectedSkin != null) {
            try {
                player.purchaseItem(selectedSkin.getSkinPrice());
                System.out.println(player.getName() + " purchased the " + selectedSkin.getSkinName() + " skin!");
            } catch (IllegalStateException e) {
                System.out.println(player.getName() + " does not have enough money to purchase this skin.");
            }
        } else {
            System.out.println("Skin not found. Please try again.");
        }
    }

    public void buyMusicOption(Player player, String musicName) {
        MusicOption selectedMusicOption = null;
        for (MusicOption musicOption : musicOptions) {
            if (musicOption.getOptionName().equalsIgnoreCase(musicName)) {
                selectedMusicOption = musicOption;
                break;
            }
        }

        if (selectedMusicOption != null) {
            try {
                player.purchaseItem(selectedMusicOption.getOptionPrice());
                System.out.println(player.getName() + " purchased the " + selectedMusicOption.getOptionName() + " music pack!");
            } catch (IllegalStateException e) {
                System.out.println(player.getName() + " does not have enough money to purchase this music pack.");
            }
        } else {
            System.out.println("Music pack not found. Please try again.");
        }
    }
}