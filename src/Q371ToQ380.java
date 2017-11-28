import java.util.ArrayList;
import java.util.List;

public class Q371ToQ380 {
  public static void main(String[] args) {
    int[] nums1 = new int[]{1, 2};
    int[] nums2 = new int[]{3};
    List<int[]> kPair = kSmallestPairs(nums1, nums2, 3);
    for (int[] pair : kPair) {
      System.out.println("[" + pair[0] + ", " + pair[1] + "]");
    }
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

}
