import java.util.ArrayList;
import java.util.List;

public class InGameShop {
    private List<CardSkin> cardSkins;
    private List<MusicOption> musicOptions;
    Player player = new Player();
    private double playerBalance = player.getBank();


    public InGameShop() {// this class creates the in game shop where players can buy cosmetic skins or music packs
        this.cardSkins = new ArrayList<CardSkin>();
        this.musicOptions = new ArrayList<>();
        this.playerBalance = player.getBank();



        cardSkins.add(new CardSkin("B & W", " Black and White for that Noir feel", 150));
        cardSkins.add(new CardSkin("Vintage", " Bring it back to the Wild West", 200));
        cardSkins.add(new CardSkin("Gold", " the epitome of riches and class", 1000000));

        musicOptions.add(new MusicOption("Festive", " perfect for Christmas time", 125));
        musicOptions.add(new MusicOption("Rock"," for true metal gamblers", 666));
        musicOptions.add(new MusicOption("Jazz", " bring it back to the '40s", 1940));
    }

    public void displayCardSkins() { // this class displays the card skin options
        System.out.println("Here are your options:");
        for(CardSkin cardSkin : cardSkins) {
            System.out.println(cardSkin.toString());
        }
    }

    public void displayMusicOptions() { // displays music pack options
        System.out.println("Here are your options:");
        for(MusicOption musicOption : musicOptions) {
            System.out.println(musicOption.toString());
        }
    }

    public void buyCardSkin(Player player, String SkinName) {
        CardSkin selectedSkin = null;
        for(CardSkin cardSkin : cardSkins) {
            if(cardSkin.getSkinName().equalsIgnoreCase(SkinName)) {
                selectedSkin = cardSkin;
                break;
            }
        }
        if(selectedSkin != null) {
            if(this.player.getBank() >= selectedSkin.getSkinPrice()){
                this.player.purchaseItem(selectedSkin.getSkinPrice());
                System.out.println("You have purchased " + selectedSkin.getSkinName());
            }
            else {
                System.out.println("You don't have enough money to buy this skin");
            }
        } else {
            System.out.println("Skin not found, added to suggestion box");
        }
    }

    public void buyMusicOption(Player player, String musicName) {
        MusicOption selectedMusicOption = null;
        for(MusicOption musicOption : musicOptions) {
            if(musicOption.getOptionName().equalsIgnoreCase(musicName)) {
                selectedMusicOption = musicOption;
                break;
            }
        }

        if(selectedMusicOption != null) {
            if(player.getBank() >= selectedMusicOption.getOptionPrice()){
                player.purchaseItem(selectedMusicOption.getOptionPrice());
                System.out.println("You have purchased " + selectedMusicOption.getOptionName());
            } else {
                System.out.println("You don't have enough money to buy this music");
            }
        } else {
            System.out.println("Music not found, added to suggestion box");
        }
    }


}
