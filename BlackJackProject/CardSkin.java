public class CardSkin { //this class creates the card skin item
    private String skinName;
    private String skinDescription;
    private double skinPrice;

    public CardSkin(String skinName, String skinDescription, double skinPrice) {
        this.skinName = skinName;
        this.skinDescription = skinDescription;
        this.skinPrice = skinPrice;
    }

    public String getSkinName() {
        return skinName;
    }

    public String getSkinDescription() {
        return skinDescription;
    }

    public double getSkinPrice() {
        return skinPrice;
    }

    @Override
    public String toString() {
        return "CardSkin [skinName=" + skinName + ", skinDescription=" + skinDescription + ", skinPrice=" + skinPrice + "]";
    }
}
