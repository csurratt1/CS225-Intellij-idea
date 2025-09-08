public class Toilet {

    public boolean isFull;
    public String swirlDirection;
    public boolean flush;

    public boolean isFlush() {
        return flush;
    }

    public void setFlush(boolean flush) {
        this.flush = flush;
    }

    public boolean isFull() {
        return isFull;
    }
    public void setFull(boolean full) {
        isFull = full;
    }

    public String getSwirlDirection() {
        return swirlDirection;
    }

    public void setSwirlDirection(String swirlDirection) {
        this.swirlDirection = swirlDirection;
    }

    Toilet(){
        isFull = false;
        swirlDirection = "ClockWise";
        flush = false;
    }



}



