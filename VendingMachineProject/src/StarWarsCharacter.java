// Modified StarWarsCharacter class to be vendable
public class StarWarsCharacter implements IVendable {
    private String name;
    private double price;
    private String side; // Light or Dark
    private Force force;

    public StarWarsCharacter(String name, double price, String side) {
        this.name = name;
        this.price = price;
        this.side = side;
        this.force = new Force(side);
    }

    @Override
    public String getName() { return name; }

    @Override
    public void setName(String name) { this.name = name; }

    @Override
    public double getPrice() { return price; }

    @Override
    public void setPrice(double price) { this.price = price; }

    public String getSide() { return side; }
    public void setSide(String side) { this.side = side; }
}
