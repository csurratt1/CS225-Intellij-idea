public class Coach {

    public int headCoachRating;
    public String headCoachName;
    public int assistCoachRating;
    public String assistCoachName;


    public void setAssistCoachName(String assistCoachName) {
        this.assistCoachName = assistCoachName;
    }

    public String getAssistCoachName() {
        return assistCoachName;
    }

    public void setHeadCoachName(String headCoachName) {
        this.headCoachName = headCoachName;
    }

    public String getHeadCoachName() {
        return headCoachName;
    }

    public int getHeadCoachRating() {
        return headCoachRating;
    }

    public void setHeadCoachRating(int headCoachRating) {
        this.headCoachRating = headCoachRating;
    }

    public int getAssistCoachRating() {
        return assistCoachRating;
    }

    public void setAssistCoachRating(int assistCoachRating) {
        this.assistCoachRating = assistCoachRating;
    }

    Coach(int headCoachRating, String headCoachName, int assistCoachRating, String assistCoachName) {
        this.headCoachRating = headCoachRating;
        this.headCoachName = headCoachName;
        this.assistCoachRating = assistCoachRating;
        this.assistCoachName = assistCoachName;
    }

}


