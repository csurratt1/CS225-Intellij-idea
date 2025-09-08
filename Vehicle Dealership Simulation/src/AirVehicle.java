// Vehicle implementations
class AirVehicle extends Vehicle implements IAir {
    public AirVehicle(String name, double price) {
        super(name, price);
    }

    @Override
    public String getVehicleType() {
        return "";
    }

    @Override
    public String fly() {
        return "Soaring through the clouds at high altitude!";
    }

    @Override
    public String testDrive() {
        return fly();
    }
}