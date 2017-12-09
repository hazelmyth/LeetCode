import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class Q381To390 {
  public static void main(String[] args) {
    int[][] matrx = new int[][]{
            {1, 5, 9},
            {10, 11, 13},
            {12, 13, 15}
    };

    System.out.println("last remaining char " + lastRemaining(24) );
  }

  /**
   * 382. Linked List Random Node
   * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
   * What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?
   */
  class Q382 {

    /**
     * @param head The linked list's head.
     * Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    Random rand = new Random();
    ListNode head;

    public Q382(ListNode head) {
      this.head = head;
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
      ListNode start = this.head;
      int result = 0;
      int count = 1;
      while (start != null) {
        if (rand.nextInt(count) == 0) {
          result = start.val;
        }
        start = start.next;
        count++;
      }
      return result;
    }
  }

  /**
   * 383. Ransom Note
   * Given an arbitrary ransom note string and another string containing letters from all the magazines,
   * write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.
   * Each letter in the magazine string can only be used once in your ransom note.
   * You may assume that both strings contain only lowercase letters.
   *
   * @param ransomNote
   * @param magazine
   * @return
   */
  public static boolean canConstruct(String ransomNote, String magazine) {
    if (ransomNote == null || ransomNote.length() == 0) {
      return true;
    }
    if (magazine == null || magazine.length() == 0) {
      return false;
    }
    int[] charCount = new int[26];
    for (char c : magazine.toCharArray()) {
      charCount[c - 'a']++;
    }
    for (char c : ransomNote.toCharArray()) {
      if (charCount[c - 'a'] == 0) {
        return false;
      }
      charCount[c - 'a']--;
    }
    return true;
  }

  /**
   * 384. Shuffle an Array
   * Shuffle a set of numbers without duplicates.
   */
  class Q384 {
    private Random rand;
    private int[] original;
    private int[] shuffled;

    public Q384(int[] nums) {
      rand = new Random();
      original = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
      shuffled = Arrays.copyOf(original, original.length);
      return shuffled;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
      shuffled = Arrays.copyOf(original, original.length);
      int len = original.length;
      for (int i = 0; i < len; i++) {
        int index = rand.nextInt(len - i);
        int temp = shuffled[i];
        shuffled[i] = shuffled[index];
        shuffled[index] = temp;
      }
      return shuffled;
    }
  }

  /**
   * Given a nested list of integers represented as a string, implement a parser to deserialize it.
   * <p>
   * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
   *
   * @return
   */
  public NestedInteger deserialize() {
    return deserialize();
  }

  /**
   * Given a nested list of integers represented as a string, implement a parser to deserialize it.
   * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
   * Note: You may assume that the string is well-formed:
   * String is non-empty.
   * String does not contain white spaces.
   * String contains only digits 0-9, [, - ,, ].
   * Given s = "[123,[456,[789]]]",
   * Return a NestedInteger object containing a nested list with 2 elements:
   * 1. An integer containing value 123.
   * 2. A nested list containing two elements:
   * i.  An integer containing value 456.
   * ii. A nested list with one element:
   * a. An integer containing value 789.
   *
   * @param s
   * @return
   */
  public NestedInteger deserialize(String s) {
    Stack<NestedInteger> stack = new Stack<NestedInteger>();
    String temp = "";
    for (char ch : s.toCharArray()) {
      switch (ch) {
        case '[':
          stack.add(new NestedInteger());
          break;
        case ',':
          if (temp.length() > 0) {
            NestedInteger outer = stack.peek();
            outer.add(new NestedInteger(Integer.parseInt(temp)));
            temp = "";
          }
          break;
        case ']':
          if (temp.length() > 0) {
            NestedInteger outer = stack.peek();
            outer.add(new NestedInteger(Integer.parseInt(temp)));
            temp = "";
          }
          NestedInteger current = stack.pop();
          if (!stack.isEmpty()) {
            stack.peek().add(current);
          } else {
            return current;
          }

          break;
        default:
          temp += ch;
      }
    }
    if (temp.length() > 0) {
      return new NestedInteger(Integer.parseInt(temp));
    } else {
      return null;
    }
  }

  // This is the interface that allows for creating nested lists.
  // You should not implement it, or speculate about its implementation
  class NestedInteger {

    public NestedInteger() {

    }

    public NestedInteger(int value) {

    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
      throw new NotImplementedException();
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
      throw new NotImplementedException();
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {
      throw new NotImplementedException();
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
      throw new NotImplementedException();
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
      throw new NotImplementedException();
    }
  }

  /**
   * 386. Lexicographical Numbers
   * Given an integer n, return 1 - n in lexicographical order.
   * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
   * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
   *
   * @param n
   * @return
   */
  public static List<Integer> lexicalOrder(int n) {
    List<Integer> list = new ArrayList<>(n);
    int curr = 1;
    for (int i = 1; i <= n; i++) {
      list.add(curr);
      if (curr * 10 <= n) {
        curr *= 10;
      } else if (curr % 10 != 9 && curr + 1 <= n) {
        curr++;
      } else {
        while ((curr / 10) % 10 == 9) {
          curr /= 10;
        }
        curr = curr / 10 + 1;
      }
    }
    return list;
  }

  /**
   * 387. First Unique Character in a String
   * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
   *
   * @param s
   * @return
   */
  public static int firstUniqChar(String s) {
    if (s == null || s.length() == 0) {
      return -1;
    }
    int[] count = new int[26];
    for (char ch : s.toCharArray()) {
      count[ch - 'a']++;
    }
    for (int i = 0; i < s.length(); i++) {
      if (count[s.charAt(i) - 'a'] == 1) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 388. Longest Absolute File Path
   * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
   * We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).
   * Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.
   * Note:
   * The name of a file contains at least a . and an extension.
   * The name of a directory or sub-directory will not contain a ..
   * Time complexity required: O(n) where n is the size of the input string.
   * Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
   *
   * @param input
   * @return
   */
  public static int lengthLongestPath(String input) {
    if (input == null || input.length() == 0) {
      return 0;
    }
    input = input + '\n';
    int depth = 0;
    int maxLen = 0;
    String cur = "";
    boolean isFile = false;
    List<Integer> dirs = new ArrayList<>();
    for (char ch : input.toCharArray()) {
      switch (ch) {
        case '\n':
          if (isFile) {
            int len = 0;
            for (int i = 0; i < depth; i++) {
              len += (dirs.get(i) + 1);
            }
            len += cur.length();
            maxLen = len > maxLen ? len : maxLen;
            isFile = false;
          } else {
            dirs.add(depth, cur.length());
          }
          depth = 0;
          cur = "";
          break;
        case '\t':
          depth += 1;
          break;
        case '.':
          isFile = true;
        default:
          cur += ch;
      }
    }
    return maxLen;
  }

  /**389. Find the Difference
   * Given two strings s and t which consist of only lowercase letters.
   * String t is generated by random shuffling string s and then add one more letter at a random position.
   * Find the letter that was added in t.
   * @param s
   * @param t
   * @return
   */
  public static char findTheDifference(String s, String t) {
    int[] letters = new int[26];
    for(int i = 0; i < s.length();i++)
    {
      letters[s.charAt(i) -'a']++;
      letters[t.charAt(i) -'a']--;
    }
    letters[t.charAt(s.length()) -'a']--;
    for(int i = 0; i < letters.length; i++)
    {
      if(letters[i] == -1)
      {
        return (char) (i + 'a');
      }
    }
    return '\n';
  }

  /**
   * 390. Elimination Game
   * There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.
   * Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.
   *We keep repeating the steps again, alternating left to right and right to left, until a single number remains.
   * Find the last number that remains starting with a list of length n.
   * @param n
   * @return
   */
  public static int lastRemaining(int n) {
    int head = 1;
    int step = 1;
    int remaining = n;
    boolean left = true;
    while (remaining > 1)
    {
      if(left || remaining % 2 == 1)
      {
        head += step;
      }
      step *= 2;
      remaining = remaining / 2;
      left = !left;
    }
    return head;
  }
}
