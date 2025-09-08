public class GenericVendingMachine extends BaseVendingMachine<IVendable> {
    /**
     * Constructor for creating a new generic vending machine
     * Initializes the slots with a mix of different items
     */
    public GenericVendingMachine() {
        super();
        initializeSlots();
    }

    /**
     * Initialize the vending machine slots with various items
     */
    private void initializeSlots() {
        // Fill slot A with candy
        slotA.enqueue(new Candy("Bottlecaps", 4.20));
        slotA.enqueue(new Candy("Bottlecaps", 4.20));

        // Fill slot B with soda
        slotB.enqueue(new Soda("Mt. Dew", 1.42));
        slotB.enqueue(new Soda("Mt. Dew", 1.42));

        // Fill slot C with Star Wars character
        slotC.enqueue(new StarWarsCharacter("Darth Vader", 42.42, "Dark"));
    }

    /**
     * Returns information about the type of vending machine
     * @return String describing the machine type
     */
    @Override
    public String GetMachineInfo() {
        return "Miscellaneous Vending Machine";
    }
}