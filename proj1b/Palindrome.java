public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque();
        for (int i = 0; i < word.length(); i += 1) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        if (word.length() <= 1) {
            return true;
        }

        Deque<Character> d1 = wordToDeque(word);
        Deque<Character> d2 = wordToDeque(word);
        for (int i = 0; i < word.length(); i += 1) {
            if (d1.removeFirst() != d2.removeLast()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        }

        Deque<Character> d1 = wordToDeque(word);
        Deque<Character> d2 = wordToDeque(word);
        int l = (int) word.length() / 2;
        for (int i = 0; i < l; i += 1) {
            if (!cc.equalChars(d1.removeFirst(), d2.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
