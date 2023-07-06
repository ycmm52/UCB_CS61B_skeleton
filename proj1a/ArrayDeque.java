public class ArrayDeque<T> {

    private int size;
    private int start;
    private int stop;
    private T[] items;
    private final int INI_SIZE = 8;
    private final double USE_RATIO = 0.25;
    private final int FACTOR = 2;

    public ArrayDeque() {
        this.size = 0;
        this.items = (T[]) new Object[INI_SIZE];
        this.start = 0;
        this.stop = 0;
    }

    public ArrayDeque(T ele) {
        this.size = 1;
        this.items = (T[]) new Object[INI_SIZE];
        this.items[0] = ele;
        this.start = 0;
        this.stop = 0;
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
        this.stop = this.size - 1;
    }

    private void reSize() {
        int newSize = this.items.length * FACTOR;
        T[] newArray = (T[]) new Object[newSize];
        // System.arraycopy(this.items, 0, newArray, 0, this.size);
        for (i=0; i < this.size ; i += 1;) {
            newArray[i] = this.items.get(i);
        }
        this.start = 0;
        this.stop = this.size - 1;
        this.size = newSize;
        this.items = newArray;
    }

    public void addFirst(T item) {
        T[] arr = (T[]) new Object[1];
        arr[0] = item;
        this.addFirst(arr);
    }

    public void addFirst(T[] arr) {
        if (this.size == this.items.length) {
            this.reSize();
        }
        for (i = 0; i < arr.length; i += 1;) {
            int trueIndex = getTrueIndex(self.start - arr.length + i);
            this.items[trueIndex] = arr[i];
        }
        self.start -= arr.length;
    }

    public void addLast(T item) {
        T[] arr = (T[]) new Object[1];
        arr[0] = item;
        this.addLast(arr);
    }

    public void addLast(T[] arr) {
        if (this.size == this.items.length) {
            this.reSize();
        }
        for (i = 0; i < arr.length; i += 1;) {
            int trueIndex = getTrueIndex(self.stop + i + 1);
            this.items[trueIndex] = arr[i];
        }
        self.stop += arr.length;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public String printDeque() {
        String out = "";
        for (i = 0; i < this.size; i+= 1;) {
            out += this.items.get(i).toString() + " ";
        }
        out = out.replaceAll("\\s+$", "");
        System.out.println(out);
        return out
    }

    public T removeFirst() {
        if (this.size < 1) {
            return null;
        }
        int trueIndex = getTrueIndex(this.start);
        T first = this.items[trueIndex];
        this.items[trueIndex] = null;
        this.size -= 1;
        this.start += 1;
        return first;
    }

    public T removeLast() {
        if (this.size < 1) {
            return null;
        }
        int trueIndex = getTrueIndex(this.stop);
        T last = this.items[trueIndex];
        this.items[trueIndex] = null;
        this.size -= 1;
        this.stop -= 1;
        return last;
    }

    private int getTrueIndex(int index) {
        int trueIndex = ( this.start + index ) % this.items.length;
        return trueIndex;
    }

    public T get(int index) {
        int trueIndex = getTrueIndex(index);
        return this.items[trueIndex];
    }

}
