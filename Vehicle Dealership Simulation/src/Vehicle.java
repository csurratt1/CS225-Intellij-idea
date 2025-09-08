// Base Vehicle class
public abstract class Vehicle {
    protected String name;
    protected double price;

    public Vehicle(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    public abstract String getVehicleType();
    public abstract String testDrive();
}
