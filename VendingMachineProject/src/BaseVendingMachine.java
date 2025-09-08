public abstract class BaseVendingMachine<T extends IVendable> implements IVendingMachine<T> {
    protected VendingQueue<T> slotA;
    protected VendingQueue<T> slotB;
    protected VendingQueue<T> slotC;
    protected double currentMoney;

    public BaseVendingMachine() {
        slotA = new VendingQueue<>();
        slotB = new VendingQueue<>();
        slotC = new VendingQueue<>();
        currentMoney = 0.0;
    }

    @Override
    public void TakeMoney(double amount) {
        currentMoney += amount;
    }

    @Override
    public void ReturnMoney(double amount) {
        currentMoney -= amount;
        System.out.printf("Returning $%.2f\n", amount);
    }

    @Override
    public abstract String GetMachineInfo();

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

    @Override
    public T VendItem(String slotCode) {
        VendingQueue<T> selectedSlot = null;
        switch (slotCode.toUpperCase()) {
            case "A": selectedSlot = slotA; break;
            case "B": selectedSlot = slotB; break;
            case "C": selectedSlot = slotC; break;
        }

        if (selectedSlot != null && selectedSlot.peek() != null) {
            T item = selectedSlot.peek();
            if (currentMoney >= item.getPrice()) {
                double change = currentMoney - item.getPrice();
                if (change > 0) {
                    ReturnMoney(change);
                }
                currentMoney = 0;
                return selectedSlot.dequeue();
            }
            System.out.printf("Insufficient funds. Need $%.2f more.\n",
                    item.getPrice() - currentMoney);
        }
        return null;
    }

    public double getItemPrice(String slotCode) {
        VendingQueue<T> selectedSlot = null;
        switch (slotCode.toUpperCase()) {
            case "A": selectedSlot = slotA; break;
            case "B": selectedSlot = slotB; break;
            case "C": selectedSlot = slotC; break;
        }

        if (selectedSlot != null && selectedSlot.peek() != null) {
            return selectedSlot.peek().getPrice();
        }
        return -1;
    }
}