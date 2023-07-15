public class LinkedListDeque<T> implements Deque<T> {

    private Node sentinel;
    private int size;

    // private LinkedListDeque(T[] arr) {
    //     this.sentinel = new Node(null);
    //     this.size = 0;
    //     Node currentNode;
    //     Node prevNode = new Node(null);
    //     for (T element : arr) {
    //         if (this.sentinel.next == this.sentinel) {
    //             currentNode = new Node(element, sentinel, null);
    //             sentinel.next = currentNode;
    //         } else {
    //             currentNode = new Node(element, prevNode, null);
    //             prevNode.next = currentNode;
    //         }
    //         this.sentinel.prev = currentNode;
    //         prevNode = currentNode;
    //         size += 1;
    //     }
    // }

    // private LinkedListDeque(T ele) {
    //     this.size = 1;
    //     this.sentinel = new Node(null);
    //     Node node = new Node(ele, sentinel, sentinel);
    //     this.sentinel.prev = node;
    //     this.sentinel.next = node;
    // }

    public LinkedListDeque() {
        this.size = 0;
        this.sentinel = new Node(null);
    }

    public void addFirst(T item) {
        Node node = new Node(item);
        Node prevFirst;
        if (this.size > 0) {
            prevFirst = this.sentinel.next;
        } else {
            prevFirst = this.sentinel;
        }
        prevFirst.prev = node;
        node.next = prevFirst;
        node.prev = this.sentinel;
        this.sentinel.next = node;
        this.size += 1;
    }

    public void addLast(T item) {
        Node node = new Node(item);
        Node prevLast;
        if (this.size > 0) {
            prevLast = this.sentinel.prev;
        } else {
            prevLast = this.sentinel;
        }
        node.next = this.sentinel;
        node.prev = prevLast;
        this.sentinel.prev = node;
        prevLast.next = node;
        this.size += 1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        Node current = this.sentinel.next;
        String out = "";
        while (current != this.sentinel) {
            out += current.item.toString() + " ";
            current = current.next;
        }
        out = out.replaceAll("\\s+$", "");
        System.out.println(out);
        // return out;
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        Node oriFirst = this.sentinel.next;
        this.sentinel.next = oriFirst.next;
        this.sentinel.next.prev = this.sentinel;
        this.size -= 1;
        return oriFirst.item;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        Node oriLast = this.sentinel.prev;
        this.sentinel.prev = oriLast.prev;
        this.sentinel.prev.next = this.sentinel;
        this.size -= 1;
        return oriLast.item;
    }

    public T get(int index) {
        if (index > this.size - 1) {
            return null;
        }
        int i = 0;
        Node node = this.sentinel;
        while (i <= index) {
            node = node.next;
            i += 1;
        }
        return node.item;
    }

    public T getRecursive(int index) {
        return this.getRecursiveHelper(index, this.sentinel.next);
    }

    private T getRecursiveHelper(int index, Node node) {
        if (node == this.sentinel) {
            return null;
        }
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(index - 1, node.next);
    }

    private class Node {
        private T item;
        private Node next;
        private Node prev;

        private Node(T i) {
            // For sentinel initialize
            this.item = i;
            this.next = this;
            this.prev = this;
        }

        private Node(T i, Node p, Node n) {
            this.item = i;
            this.prev = p;
            this.next = n;
        }
    }

}
