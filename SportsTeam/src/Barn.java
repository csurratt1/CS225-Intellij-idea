public class Barn {
    public boolean isHome;
    public String barnName;
    public long capacity;

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
    }

    public String getBarnName() {
        return barnName;
    }

    public void setBarnName(String barnName) {
        this.barnName = barnName;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    Barn(String barnName, long capacity, boolean isHome){
        this.barnName = barnName;
        this.capacity = capacity;
        this.isHome = isHome;
    }
}
