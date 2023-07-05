public class ArrayDeque<T> {

    private int size;
    private int start;
    private int stop;
    private T[] items;
    private final int INI_SIZE = 8;
    private final double USE_RATIO = 0.25;
    private final int FACTOR = 2;

    public ArrayDeque() {
        this.size = INI_SIZE;
        this.items = (T[]) new Object[this.size];
        this.start = 0;
        this.stop = 0;
    }

    public ArrayDeque(T ele) {
        this.size = INI_SIZE;
        this.items = (T[]) new Object[this.size];
        this.items[0] = ele;
        this.start = 0;
        this.stop = 1;
    }

    public ArrayDeque(T[] arr) {
        int arrSize = arr.length;
        if (arrSize > INI_SIZE) {
            this.size = arrSize * FACTOR;
        } else {
            this.size = INI_SIZE;
        }
        this.items = (T[]) new Object[this.size];
        for (int i = 0; i < arr.length; i += 1) {
            this.items[i] = arr[i];
        }
        this.start = 0;
        this.stop = this.size;
    }

    public void addFirst(T item) {
    }

    public void addFirst(T[] arr) {
    }

    public void addLast(T item) {
    }

    public void addLast(T[] arr) {
    }

    public boolean isEmpty() {
    }

    public int size() {
    }

    public void printDeque() {
    }

    public T removeFirst() {
    }

    public T removeLast() {
    }

    public T get(int index) {
    }

    public T getRecursive(int index) {
    }

    public T getRecursiveHelper() {
    }

}
