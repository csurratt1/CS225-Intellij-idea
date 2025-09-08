// Force class from Assignment 1
public class Force {
    private String alignment;
    private double power;

    public Force(String alignment) {
        this.alignment = alignment;
        this.power = alignment.equalsIgnoreCase("Dark") ? 100 : 50;
    }

    public String getAlignment() { return alignment; }
    public double getPower() { return power; }
}