public class ArrayDeque<T> {

    private int size;
    private int start;
    private int stop;
    private T[] items;
    private final int INI_SIZE = 8;
    private final double USE_RATIO = 0.25;
    private final int INC_FACTOR = 2;
    private final double REC_FACTOR = 0.5;

    public ArrayDeque() {
        this.size = 0;
        this.items = (T[]) new Object[INI_SIZE];
        this.start = 0;
        this.stop = 0;
    }

    // private ArrayDeque(T ele) {
    //     this.size = 1;
    //     this.items = (T[]) new Object[INI_SIZE];
    //     this.items[0] = ele;
    //     this.start = 0;
    //     this.stop = 0;
    // }

    // private ArrayDeque(T[] arr) {
    //     int arrSize = arr.length;
    //     if (arrSize > INI_SIZE) {
    //         this.size = arrSize * FACTOR;
    //     } else {
    //         this.size = INI_SIZE;
    //     }
    //     this.items = (T[]) new Object[this.size];
    //     for (int i = 0; i < arr.length; i += 1) {
    //         this.items[i] = arr[i];
    //     }
    //     this.start = 0;
    //     this.stop = this.size - 1;
    // }

    private void reSize() {
        int newSize;
        if (this.size == this.items.length) {
            newSize = this.items.length * INC_FACTOR;
        } else if (items.length > INI_SIZE && size / items.length <= USE_RATIO) {
            int tempSize = (int) Math.ceil(this.items.length * REC_FACTOR);
            newSize = Math.max(tempSize, INI_SIZE);
        } else {
            return;
        }
        T[] newArray = (T[]) new Object[newSize];
        // System.arraycopy(this.items, 0, newArray, 0, this.size);
        for (int i = 0; i < this.size; i += 1) {
            newArray[i] = this.get(i);
        }
        this.start = 0;
        this.stop = this.size - 1;
        this.items = newArray;
    }

    public void addFirst(T item) {
        if (this.size == this.items.length) {
            this.reSize();
        }
        int index;
        if (isEmpty()) {
            index = convertIndex(this.start);
        } else {
            index = convertIndex(this.start - 1);
        }
        this.items[index] = item;
        if (!isEmpty()) {
            index = convertIndex(index);
            this.start = index;
        }
        this.size += 1;
    }

    // private void addFirst(T[] arr) {
    //     for (int i = arr.length - 1; i >= 0; i -= 1) {
    //         this.addFirst(arr[i]);
    //     }
    // }

    public void addLast(T item) {
        if (this.size == this.items.length) {
            this.reSize();
        }
        int index;
        if (isEmpty()) {
            index = this.stop;
        } else {
            index = convertIndex(this.stop + 1);
        }
        this.items[index] = item;
        if (!isEmpty()) {
            index = convertIndex(index);
            this.stop = index;
        }
        this.size += 1;
    }

    // private void addLast(T[] arr) {
    //     for (T element : arr) {
    //         this.addLast(element);
    //     }
    // }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        String out = "";
        if (this.size == 0) {
            System.out.println(out);
        }
        for (int i = 0; i < this.size; i += 1) {
            out += this.get(i).toString() + " ";
        }
        out = out.replaceAll("\\s+$", "");
        System.out.println(out);
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int trueIndex = convertIndex(this.start);
        T first = this.items[trueIndex];
        this.items[trueIndex] = null;
        this.size -= 1;
        if (!isEmpty()) {
            // this.start += 1;
            this.start = convertIndex(this.start + 1);
        }
        this.reSize();
        return first;
    }

    public T removeLast() {
        if (this.size < 1) {
            return null;
        }
        int trueIndex = convertIndex(this.stop);
        T last = this.items[trueIndex];
        this.items[trueIndex] = null;
        this.size -= 1;
        if (!isEmpty()) {
            // this.stop -= 1;
            this.stop = convertIndex(this.stop - 1);
        }
        this.reSize();
        return last;
    }

    private int convertIndex(int index) {
        int i = (index + this.items.length) % this.items.length;
        return i;
    }

    private int getTrueIndex(int index) {
        int trueIndex = (this.start + index + this.items.length) % this.items.length;
        return trueIndex;
    }

    public T get(int index) {
        if (index > this.size - 1) {
            return null;
        }
        int trueIndex = getTrueIndex(index);
        return this.items[trueIndex];
    }

}
