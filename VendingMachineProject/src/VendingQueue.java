public class VendingQueue<T extends IVendable> {
    private T[] items;
    private int front;
    private int rear;
    private int size;
    private static final int MAX_SIZE = 5;

    @SuppressWarnings("unchecked")
    public VendingQueue() {
        items = (T[]) new IVendable[MAX_SIZE];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean enqueue(T item) {
        if (size < MAX_SIZE) {
            rear = (rear + 1) % MAX_SIZE;
            items[rear] = item;
            size++;
            return true;
        }
        return false;
    }

    public T dequeue() {
        if (size > 0) {
            T item = items[front];
            items[front] = null;
            front = (front + 1) % MAX_SIZE;
            size--;
            return item;
        }
        return null;
    }

    public T peek() {
        if (size > 0) {
            return items[front];
        }
        return null;
    }

    public int getSize() {
        return size;
    }
}
