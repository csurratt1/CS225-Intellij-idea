import java.util.ArrayList;
import java.util.Scanner;

// Main Dealership class
public class Dealership {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create inventory with example vehicles from the PDF
        ArrayList<Vehicle> inventory = new ArrayList<>();
        inventory.add(new AirVehicle("747", 375000000.0));
        inventory.add(new AirVehicle("F22", 150000000.0));
        //inventory.add(new SpaceVehicle("Space Shuttle", 1960000000.0));
        //inventory.add(new RoadVehicle("Sports Car", 85000.0));
        //inventory.add(new HybridOffRoadVehicle("4x4 Pickup", 65000.0));
        //inventory.add(new OffRoadVehicle("Rock Crawler", 45000.0));
        //inventory.add(new UnderwaterVehicle("Submarine", 2900000.0));
        //inventory.add(new WaterSurfaceVehicle("Speed Boat", 55000.0));

        // Shopping loop
        for (Vehicle vehicle : inventory) {
            System.out.println("\n=== Vehicle Information ===");
            System.out.println("Name: " + vehicle.getName());
            System.out.printf("Price: $%,.2f%n", vehicle.getPrice());

            System.out.println("\nTest driving " + vehicle.getName() + "...");
            System.out.println(vehicle.testDrive());

            System.out.print("\nWould you like to purchase this vehicle? (yes/no): ");
            String purchase = scanner.nextLine().toLowerCase();

            if (purchase.equals("yes")) {
                System.out.print("Please enter the amount you wish to pay: $");
                double payment = scanner.nextDouble();
                scanner.nextLine(); // Consume newline

                if (payment >= vehicle.getPrice()) {
                    System.out.println("Congratulations! You've purchased the " + vehicle.getName() + "!");
                    System.out.printf("Change: $%,.2f%n", (payment - vehicle.getPrice()));
                    break;
                } else {
                    System.out.println("Insufficient funds. Moving to next vehicle.");
                }
            }
        }

        scanner.close();
    }
}