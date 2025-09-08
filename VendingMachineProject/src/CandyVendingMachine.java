public class CandyVendingMachine extends BaseVendingMachine<Candy> {
    /**
     * Constructor for creating a new candy vending machine
     * Initializes the slots with default candy items
     */
    public CandyVendingMachine() {
        super(); // Call the parent constructor to initialize the slots
        initializeSlots();
    }

    /**
     * Initialize the vending machine slots with candy
     * Slots can hold up to 5 items each
     */
    private void initializeSlots() {
        // Fill slot A with Skittles
        for (int i = 0; i < 2; i++) {
            slotA.enqueue(new Candy("Skittles", 2.20));
        }

        // Fill slot B with Snickers
        for (int i = 0; i < 5; i++) {
            slotB.enqueue(new Candy("Snickers", 1.42));
        }

        // Fill slot C with M&Ms
        slotC.enqueue(new Candy("M & M's", 2.42));
    }

    /**
     * Returns information about the type of vending machine
     * @return String describing the machine type
     */
    @Override
    public String GetMachineInfo() {
        return "Candy Vending Machine";
    }

    /**
     * Get the price of an item in a specific slot
     * @param slotCode The slot to check (A, B, or C)
     * @return The price of the item, or -1 if the slot is invalid or empty
     */
    @Override
    public double getItemPrice(String slotCode) {
        VendingQueue<Candy> selectedSlot = null;
        switch (slotCode.toUpperCase()) {
            case "A":
                selectedSlot = slotA;
                break;
            case "B":
                selectedSlot = slotB;
                break;
            case "C":
                selectedSlot = slotC;
                break;
        }

        if (selectedSlot != null && selectedSlot.peek() != null) {
            return selectedSlot.peek().getPrice();
        }
        return -1;  // Return -1 if slot is invalid or empty
    }

    /**
     * Vend an item from the specified slot
     * @param slotCode The slot to vend from (A, B, or C)
     * @return The candy item if successful, null if unsuccessful
     */
    @Override
    public Candy VendItem(String slotCode) {
        VendingQueue<Candy> selectedSlot = null;
        switch (slotCode.toUpperCase()) {
            case "A":
                selectedSlot = slotA;
                break;
            case "B":
                selectedSlot = slotB;
                break;
            case "C":
                selectedSlot = slotC;
                break;
        }

        if (selectedSlot != null && selectedSlot.peek() != null) {
            Candy candy = selectedSlot.peek();
            if (currentMoney >= candy.getPrice()) {
                double change = currentMoney - candy.getPrice();
                if (change > 0) {
                    ReturnMoney(change);
                }
                currentMoney = 0;
                return selectedSlot.dequeue();
            } else {
                System.out.printf("Insufficient funds. Need $%.2f more.\n",
                        candy.getPrice() - currentMoney);
            }
        }
        return null;
    }

    /**
     * Display the contents of the vending machine
     * @return A formatted string showing all available items and their prices
     */
    @Override
    public String DisplayContents() {
        StringBuilder contents = new StringBuilder();
        contents.append("Current balance: $").append(String.format("%.2f", currentMoney)).append("\n");

        if (slotA.peek() != null) {
            contents.append(String.format("A: %s (%d) - $%.2f\n",
                    slotA.peek().getName(), slotA.getSize(), slotA.peek().getPrice()));
        }

        if (slotB.peek() != null) {
            contents.append(String.format("B: %s (%d) - $%.2f\n",
                    slotB.peek().getName(), slotB.getSize(), slotB.peek().getPrice()));
        }

        if (slotC.peek() != null) {
            contents.append(String.format("C: %s (%d) - $%.2f\n",
                    slotC.peek().getName(), slotC.getSize(), slotC.peek().getPrice()));
        }

        return contents.toString();
    }
}