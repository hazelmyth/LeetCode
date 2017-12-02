import javax.security.sasl.SaslServer;
import java.util.*;

public class Q371ToQ380 {
  public static void main(String[] args) {
    int[][] matrx = new int[][]{
            {1,  5,  9},
            {10, 11, 13},
            {12, 13, 15}
    };
    System.out.print("test count " + kthSmallest(matrx,8));
  }

  /**
   * 371. Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
   *
   * @param a
   * @param b
   * @return
   */
  public static int getSum(int a, int b) {
    while (b != 0) {
      int c = a ^ b;
      b = (a & b) << 1;
      a = c;
    }
    return a;
  }

  /**
   * 372. Your task is to calculate ab mod 1337
   * where a is a positive integer and b is an extremely large positive integer given in the form of an array.
   * a = 2
   * b = [3]
   * Result: 8
   *
   * @param a
   * @param b
   * @return
   */
  public static int superPow(int a, int[] b) {
    a = a % 1337;
    int result = 1;
    for (int i = b.length - 1; i >= 0; i--) {
      result = (result * quickSuperPower(a, b[i])) % 1337;
      a = quickSuperPower(a, 10);
    }
    return result;
  }

  private static int quickSuperPower(int a, int b) {
    a = a % 1337;
    int result = 1;
    while (b > 0) {
      result = (b & 1) == 1 ? (result * a) % 1337 : result;
      b = b >> 1;
      a = (a * a) % 1337;
    }
    return result;
  }

  /**
   * 373. Find K Pairs with Smallest Sums
   * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
   * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
   * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
   *
   * @param nums1
   * @param nums2
   * @param k
   * @return
   */
  public static List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    int n1 = nums1.length;
    int n2 = nums2.length;
    List<int[]> result = new ArrayList<>();
    if (n1 == 0 || n2 == 0) {
      return result;
    }
    k = Math.min(n1*n2,k);
    // keep the paired up index of array 2 with each element in array 1
    int[] index = new int[n1];
    while (k > 0) {
      int minPairSum = Integer.MAX_VALUE;
      int minPairIndex = 0;
      for (int i = 0; i < n1; i++) {
        if (index[i] < n2 && nums1[i] + nums2[index[i]] < minPairSum) {
          minPairSum = nums1[i] + nums2[index[i]];
          minPairIndex = i;
        }
      }
      result.add(new int[]{nums1[minPairIndex], nums2[index[minPairIndex]]});
      index[minPairIndex]++;
      k--;
    }
    return result;
  }

  /**
   * 374. Guess Number Higher or Lower
   * We are playing the Guess Game. The game is as follows:
   *I pick a number from 1 to n. You have to guess which number I picked.
   *Every time you guess wrong, I'll tell you whether the number is higher or lower.
   *You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
   * @param n
   * @return
   */
  public static int guessNumber(int n) {
    int mid = (1 + n) / 2;
    int result = guess(mid);
    while(result != 0)
    {
      if(result == 1)
      {
        mid = (mid + 1 + n) /2;
      }else {
        mid = (1 + mid) /2;
      }
      result = guess(mid);
    }
    return mid;
  }

  private static int guess(int num){
    if(num > 27)
    {
      return -1;
    }else if(num < 27)
    {
      return 1;
    }else {
      return 0;
    }
  }

  /**
   * 375. Guess Number Higher or Lower II
   * We are playing the Guess Game. The game is as follows:
   * I pick a number from 1 to n. You have to guess which number I picked.
   * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
   * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
   * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
   * @param n
   * @return
   */
  public static int getMoneyAmount(int n) {
    int[][] dp = new int[n+2][n+2];
    for(int i = 2; i <= n; i++)
    {
      int lt = 1;
      int rt = i;
      while(rt <= n)
      {
        dp[lt][rt] = Integer.MAX_VALUE;
        for(int k = lt; k <= rt; k++)
        {
          dp[lt][rt] = Math.min(dp[lt][rt], Math.max(dp[lt][k-1],dp[k+1][rt]) + k);
        }
        lt++;
        rt++;
      }
    }
    return dp[1][n];
  }

  public static int getMoneyAmount2(int n) {
    int[][] dp = new int[n+1][n+1];
    return solve(dp, 1, n);
  }
  static int solve(int[][] dp, int L, int R) {
    if (L >= R) return 0;
    if (dp[L][R] != 0) return dp[L][R];
    dp[L][R] = 0x7FFFFFFF;
    for (int i = L; i <= R; i++) {
      dp[L][R] = Math.min(dp[L][R], i + Math.max(solve(dp,L,i-1),solve(dp,i+1,R)));
    }
    return dp[L][R];
  }

  /**
   * 376. Wiggle Subsequence
   * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.
   * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.
   * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.
   * @param nums
   * @return
   */
  public static int wiggleMaxLength(int[] nums) {
    if(nums.length < 2)
    {
      return nums.length;
    }
    int count = 1;
    for(int i = 1, j = 0; i < nums.length; j = i, i++)
    {
      if(nums[j] < nums[i])
      {
        count++;
        while (i < nums.length -1 && nums[i] <= nums[i+1])
        {
          i++;
        }
      }
      if(nums[j] > nums[i])
      {
        count++;
        while (i < nums.length - 1 && nums[i] >= nums[i+1])
        {
          i++;
        }
      }
    }
    return count;
  }

  /**
   * 377. Combination Sum IV
   * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
   * Note that different sequences are counted as different combinations.
   * @param nums
   * @param target
   * @return
   */
  public static int combinationSum4(int[] nums, int target) {
    if(nums == null || nums.length == 0)
    {
      return 0;
    }
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for(int i = 0; i <= target; i++)
    {
      for(int num: nums)
      {
        if(i + num <= target)
        {
          dp[i+num] += dp[i];
        }
      }
    }
    return dp[target];
  }

  /**
   * 378. Kth Smallest Element in a Sorted Matrix
   * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
   * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
   * matrix = [
   * [ 1,  5,  9],
   * [10, 11, 13],
   * [12, 13, 15]
   * ],
   * k = 8,
   * return 13.
   * @param matrix
   * @param k
   * @return
   * You may assume k is always valid, 1 ≤ k ≤ n2.
   */
  public static int kthSmallest(int[][] matrix, int k) {
    // keep the index in column
    int n = matrix.length;
    int[] index = new int[n];
    int min = 0;
    while (k > 0)
    {
      int currentMin = Integer.MAX_VALUE;
      int minIndex = 0;
      for(int i = 0; i < n; i++)
      {
        if(index[i] < n && matrix[i][index[i]] < currentMin)
        {
          currentMin = matrix[i][index[i]];
          minIndex = i;
        }
      }
      index[minIndex]++;
      k--;
      min = currentMin;
    }
    return min;
  }

  /**
   * 379. Design Phone Directory
   * Design a Phone Directory which supports the following operations:
   * get: Provide a number which is not assigned to anyone.
   * check: Check if a number is available or not.
   * release: Recycle or release a number.
   */
  private class PhoneDirectory {
    int max;
    HashSet<Integer> set;
    LinkedList<Integer> queue;

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
      set = new HashSet<Integer>();
      queue = new LinkedList<Integer>();
      for(int i=0; i<maxNumbers; i++){
        queue.offer(i);
      }
      max=maxNumbers-1;
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
      if(queue.isEmpty())
        return -1;

      int e = queue.poll();
      set.add(e);
      return e;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
      return !set.contains(number) && number<=max;
    }

    /** Recycle or release a number. */
    public void release(int number) {
      if(set.contains(number)){
        set.remove(number);
        queue.offer(number);
      }
    }
  }

  /**
   * Design a data structure that supports all following operations in average O(1) time.
   * insert(val): Inserts an item val to the set if not already present.
   * remove(val): Removes an item val from the set if present.
   * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
   */
  class RandomizedSet {
    HashMap<Integer, Integer> map1;
    HashMap<Integer, Integer> map2;
    Random rand;
    /** Initialize your data structure here. */
    public RandomizedSet() {
      map1  = new HashMap<Integer, Integer>();
      map2  = new HashMap<Integer, Integer>();
      rand = new Random(System.currentTimeMillis());
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
      if(map1.containsKey(val)){
        return false;
      }else{
        map1.put(val, map1.size());
        map2.put(map2.size(), val);
      }
      return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
      if(map1.containsKey(val)){
        int index = map1.get(val);

        //remove the entry from both maps
        map1.remove(val);
        map2.remove(index);

        if(map1.size()==0){
          return true;
        }

        //if last is deleted, do nothing
        if(index==map1.size()){
          return true;
        }

        //update the last element's index
        int key1 = map2.get(map2.size());

        map1.put(key1, index);
        map2.remove(map2.size());
        map2.put(index, key1);

      }else{
        return false;
      }

      return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
      if(map1.size()==0){
        return -1;
      }

      if(map1.size()==1){
        return map2.get(0);
      }

      return map2.get(new Random().nextInt(map1.size()));
    }
  }


}
