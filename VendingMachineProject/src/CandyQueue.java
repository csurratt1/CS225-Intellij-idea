// Queue implementation for vending machine slots
class CandyQueue {
    private Candy[] items;
    private int front;
    private int rear;
    private int size;
    private static final int MAX_SIZE = 5;

    /**
     * Constructor for creating a new candy queue
     */
    public CandyQueue() {
        items = new Candy[MAX_SIZE];
        front = 0;
        rear = -1;
        size = 0;
    }

    /**
     * Add candy to the queue
     * @param candy The candy to add
     * @return true if successful, false if queue is full
     */
    public boolean enqueue(Candy candy) {
        if (size < MAX_SIZE) {
            rear = (rear + 1) % MAX_SIZE;
            items[rear] = candy;
            size++;
            return true;
        }
        return false;
    }

    /**
     * Remove and return candy from the queue
     * @return The removed candy item, or null if empty
     */
    public Candy dequeue() {
        if (size > 0) {
            Candy candy = items[front];
            items[front] = null;
            front = (front + 1) % MAX_SIZE;
            size--;
            return candy;
        }
        return null;
    }

    /**
     * Get the first item without removing it
     * @return The first candy item, or null if empty
     */
    public Candy peek() {
        if (size > 0) {
            return items[front];
        }
        return null;
    }

    /**
     * Get the current size of the queue
     * @return The number of items in the queue
     */
    public int getSize() {
        return size;
    }
}