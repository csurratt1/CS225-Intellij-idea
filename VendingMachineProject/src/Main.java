import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Vending Machine Selection ===");
            System.out.println("C: Candy Vending Machine");
            System.out.println("S: Soda Vending Machine");
            System.out.println("M: Miscellaneous Vending Machine");
            System.out.println("Q: Quit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("Q")) {
                System.out.println("Thank you for using the vending machines!");
                break;
            }

            BaseVendingMachine<?> machine = null;
            switch (choice) {
                case "C":
                    machine = new CandyVendingMachine();
                    break;
                case "S":
                    machine = new SodaVendingMachine();
                    break;
                case "M":
                    machine = new GenericVendingMachine();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            useVendingMachine(machine, scanner);
        }

        scanner.close();
    }

    private static void useVendingMachine(BaseVendingMachine<?> machine, Scanner scanner) {
        System.out.println("\n" + machine.GetMachineInfo());
        System.out.println("Here are your options:");
        System.out.println(machine.DisplayContents());
        System.out.println("Enter slot letter (A/B/C) or 'X' to cancel: ");

        String slotChoice = scanner.nextLine().toUpperCase();

        if (slotChoice.equals("X")) {
            return;
        }

        if (!slotChoice.matches("[ABC]")) {
            System.out.println("Invalid slot selection.");
            return;
        }

        double itemPrice = machine.getItemPrice(slotChoice);
        if (itemPrice == -1) {
            System.out.println("Item not available.");
            return;
        }

        double totalInserted = 0;
        while (totalInserted < itemPrice) {
            System.out.printf("Item price: $%.2f\n", itemPrice);
            System.out.printf("Currently inserted: $%.2f\n", totalInserted);
            System.out.printf("Still needed: $%.2f\n", itemPrice - totalInserted);
            System.out.print("Insert money (or 'C' to cancel): $");

            String input = scanner.nextLine();
            if (input.toUpperCase().equals("C")) {
                if (totalInserted > 0) {
                    System.out.printf("Returning $%.2f\n", totalInserted);
                    machine.ReturnMoney(totalInserted);
                }
                return;
            }

            try {
                double payment = Double.parseDouble(input);
                if (payment <= 0) {
                    System.out.println("Please insert a valid amount.");
                    continue;
                }
                totalInserted += payment;
                machine.TakeMoney(payment);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a number.");
            }
        }

        IVendable item = machine.VendItem(slotChoice);
        if (item != null) {
            System.out.printf("Vended: %s for $%.2f\n", item.getName(), item.getPrice());
        } else {
            System.out.println("Error vending item. Returning money.");
            machine.ReturnMoney(totalInserted);
        }
    }
}
