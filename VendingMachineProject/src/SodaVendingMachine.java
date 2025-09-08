public class SodaVendingMachine extends BaseVendingMachine<Soda> {
    /**
     * Constructor for creating a new soda vending machine
     * Initializes the slots with default soda items
     */
    public SodaVendingMachine() {
        super();
        initializeSlots();
    }

    /**
     * Initialize the vending machine slots with soda
     * Slots can hold up to 5 items each
     */
    private void initializeSlots() {
        // Fill slot A with Coca Cola
        for (int i = 0; i < 3; i++) {
            slotA.enqueue(new Soda("Coca Cola", 2.50));
        }

        // Fill slot B with Mountain Dew
        for (int i = 0; i < 4; i++) {
            slotB.enqueue(new Soda("Mt. Dew", 1.42));
        }

        // Fill slot C with Dr Pepper
        slotC.enqueue(new Soda("Dr Pepper", 2.00));
        slotC.enqueue(new Soda("Dr Pepper", 2.00));
    }

    /**
     * Returns information about the type of vending machine
     * @return String describing the machine type
     */
    @Override
    public String GetMachineInfo() {
        return "Soda Vending Machine";
    }
}