import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    static CharacterComparator offByN = new OffByN(2);

    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('z', 'y'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('a', 'z'));
        assertFalse(offByOne.equalChars('a', 'B'));
    }

    @Test
    public void testOffByN() {
        assertTrue(offByN.equalChars('a', 'c'));
        assertTrue(offByN.equalChars('z', 'x'));
        assertFalse(offByN.equalChars('a', 'a'));
        assertFalse(offByN.equalChars('b', 'z'));
        assertFalse(offByN.equalChars('a', 'C'));
    }

}
