public class Player {

    public int jerseyNumber;
    public int playerOverallRating;
    public String playerFirstName;
    public String playerLastName;
    public boolean isCaptain;

    public int getJerseyNumber() {
        return jerseyNumber;
    }
    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }
    public int getPlayerOverallRating() {
        return playerOverallRating;
    }
    public void setPlayerOverallRating(int playerOverallRating) {
        this.playerOverallRating = playerOverallRating;
    }
    public String getPlayerFirstName() {
        return playerFirstName;
    }
    public void setPlayerFirstName(String playerName) {
        this.playerFirstName = playerName;
    }
    public boolean isCaptain() {
        return isCaptain;
    }

    Player(int jerseyNumber, int playerOverallRating, String playerFirstName, String playerLastName, boolean isCaptain) {
        this.jerseyNumber = jerseyNumber;
        this.playerOverallRating = playerOverallRating;
        this.playerFirstName = playerFirstName;
        this.playerLastName = playerLastName;
        this.isCaptain = isCaptain;
    }

}


