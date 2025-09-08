public class MusicOption { // this class creates the music option item

    private String optionName;
    private String optionDescription;
    private double optionPrice;

    public MusicOption(String optionName, String optionDescription, double optionPrice) {
        this.optionName = optionName;
        this.optionDescription = optionDescription;
        this.optionPrice = optionPrice;
    }

    public String getOptionName() {
        return optionName;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public double getOptionPrice() {
        return optionPrice;
    }

}
