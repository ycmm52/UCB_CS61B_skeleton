import static org.junit.Assert.*;
import org.junit.Test;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);
    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        String ss = "aa";
        lld1.addFirst(ss);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    @Test
    public void testLinkedListDeque() {
        LinkedListDeque<String> dq = new LinkedListDeque();
        // assertEquals("", dq.printDeque());
        assertEquals(true, dq.isEmpty());
        dq.addFirst("aa");
        dq.addLast("bb");
        dq.addFirst("cc");
        dq.addLast("dd");
        // assertEquals("cc aa bb dd", dq.printDeque());
        assertEquals("bb", dq.get(2));
        assertEquals("bb", dq.getRecursive(2));
        assertEquals(null, dq.get(5));
        assertEquals(null, dq.getRecursive(5));
        assertEquals(4, dq.size());
        assertEquals("cc", dq.removeFirst());
        assertEquals("dd", dq.removeLast());
        assertEquals("bb", dq.removeLast());
        assertEquals("aa", dq.removeFirst());
        assertEquals(null, dq.removeFirst());
        assertEquals(null, dq.removeLast());
        // assertEquals("aa bb", dq.printDeque());
    }

    @Test
    public void testArrayDeque() {
        ArrayDeque<Integer> dq = new ArrayDeque();
        assertEquals(true, dq.isEmpty());
        dq.addFirst(0);
        assertEquals(0, (int) dq.removeFirst());
        dq.addFirst(2);
        assertEquals(2, (int) dq.removeLast());
        dq.addLast(2);
        assertEquals(2, (int) dq.removeFirst());
        dq.addLast(0);
        assertEquals(0, (int) dq.removeFirst());
        dq.addFirst(2);
        dq.addLast(3);
        dq.addLast(4);
        dq.addLast(5);
        dq.addLast(6);
        dq.addLast(7);
        dq.addFirst(8);
        dq.addFirst(9);
        dq.addFirst(10);
        assertEquals(8, (int) dq.get(2));
        dq.removeFirst();
        dq.removeFirst();
        dq.removeFirst();
        dq.removeFirst();
        dq.removeFirst();
        dq.removeFirst();
        dq.removeFirst();

    }

    // public static void main(String[] args) {
    //     System.out.println("Running tests.\n");
    //     addIsEmptySizeTest();
    //     addRemoveTest();
    // }
}
