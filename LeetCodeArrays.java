import java.util.*;

public class LeetCodeArrays {

    public static void main(String[] args) {
        // findMaxConsecutiveOnes(new int[]{-4,-1,0,3,10});
        // findNumbers(new int[]{-4,-1,0,3,10});
        // sortedSquares(new int[]{-4,-1,0,3,10});
        // duplicateZeros(new int[]{0,4,1,0,0,8,0,0,3});
        // merge(new int[]{2, 0}, 1, new int[]{1}, 1);
        // removeElement(new int[]{0,0,1,1,1,2,2,3,3,4}, 1);
        // removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});
        // checkIfExist(new int[]{0,0});
        // validMountainArray(new int[]{2,0,2});
        // replaceElements(new int[]{17,18,5,4,6,1});
        // moveZeroes(new int[]{0,1});
        // sortArrayByParity(new int[]{3,1,2,4});
        // heightChecker(new int[]{5,1,2,3,4});
        // thirdMax(new int[]{1,2,2,5,3,5});
        findDisappearedNumbers(new int[] { 1, 1, 2, 2 });
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        System.out.println("Original " + Arrays.toString(nums));
        List<Integer> list = new ArrayList<Integer>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = nums[index] * -1;
            }
        }
        System.out.println("Negative " + Arrays.toString(nums));
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                int missing = i + 1;
                list.add(missing);
            }
        }
        System.out.println("Missing " + Arrays.toString(list.toArray()));
        return list;
    }

    public static int thirdMax(int[] array) {
        System.out.println("Original " + Arrays.toString(array));
        int n = array.length;
        if (n < 3) {
            return (array[0] > array[1]) ? array[0] : array[1];
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println("Sorted " + Arrays.toString(array));
        boolean found = false;
        int firstMax = array[n - 1];
        int secondMax = Integer.MIN_VALUE;
        int thirdMax = Integer.MIN_VALUE;
        for (int index = n - 1; index >= 0; index--) {
            if (array[index] < firstMax) {
                if (array[index] < secondMax && secondMax != Integer.MIN_VALUE) {
                    thirdMax = array[index];
                    found = true;
                    break;
                }
                secondMax = array[index];
            }
        }
        return found ? thirdMax : firstMax;
    }

    public static int heightChecker(int[] heights) {
        System.out.println("Original " + Arrays.toString(heights));
        int N = heights.length;
        int count = 0;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = heights[i];
        }
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("Sorted " + Arrays.toString(arr));
        for (int i = 0; i < N; i++) {
            if (arr[i] != heights[i]) {
                count++;
            }
        }
        System.out.println("Count " + count);
        return count;
    }

    public static int[] sortArrayByParity(int[] A) {
        System.out.println(Arrays.toString(A) + "\n");
        int writePointer = 0;
        for (int readPointer = 0; readPointer < A.length; readPointer++) {
            if (A[readPointer] % 2 == 0) {
                int temp = A[writePointer];
                A[writePointer] = A[readPointer];
                A[readPointer] = temp;
                writePointer++;
            }
        }
        System.out.println(Arrays.toString(A));
        return A;
    }

    public static void moveZeroes(int[] nums) {
        System.out.println(Arrays.toString(nums) + "\n");
        int writePointer = 0;
        int zero = 0;
        for (int readPointer = 0; readPointer < nums.length; readPointer++) {
            if (nums[readPointer] != 0) {
                nums[writePointer] = nums[readPointer];
                writePointer++;
            } else {
                zero++;
            }
        }
        System.out.println(Arrays.toString(nums));
        while (zero > 0) {
            nums[nums.length - zero] = 0;
            zero--;
        }
        System.out.println(Arrays.toString(nums));
    }

    public static int[] replaceElements(int[] arr) {
        System.out.println(Arrays.toString(arr) + "\n");
        for (int i = 0; i < arr.length - 1; i++) {
            int max = arr[i + 1];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            arr[i] = max;
        }
        System.out.println(Arrays.toString(arr));
        arr[arr.length - 1] = -1;
        return arr;
    }

    public static boolean validMountainArray(int[] A) {
        int N = A.length;
        int i = 0;

        // walk up
        while (i + 1 < N && A[i] < A[i + 1])
            i++;

        // peak can't be first or last
        if (i == 0 || i == N - 1)
            return false;

        // walk down
        while (i + 1 < N && A[i] > A[i + 1])
            i++;

        return i == N - 1;
    }

    public static boolean checkIfExist(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == 0) {
                if (map.containsKey(arr[i] * 2)) {
                    if (map.get(arr[i] * 2) != i) {
                        return true;
                    }
                } else if (map.containsKey(arr[i] / 2)) {
                    if (map.get(arr[i] / 2) != i) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int removeDuplicates(int[] nums) {
        System.out.println(Arrays.toString(nums) + "\n");
        if (nums.length == 0)
            return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        System.out.println(Arrays.toString(nums));
        return i + 1;
    }

    public static int removeElement(int[] nums, int val) {
        System.out.println(Arrays.toString(nums));
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return i;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        System.out.println("nums1 " + Arrays.toString(nums1));
        System.out.println("nums2 " + Arrays.toString(nums2));
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] == 0) {
                outerloop: for (int j = 0; j < nums2.length; j++) {
                    if (nums2[j] != 0) {
                        nums1[i] = nums2[j];
                        nums2[j] = 0;
                        break outerloop;
                    }
                }
            }
        }
        int temp = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = i + 1; j < nums1.length; j++) {
                if (nums1[i] > nums1[j]) {
                    temp = nums1[i];
                    nums1[i] = nums1[j];
                    nums1[j] = temp;
                }
            }
        }
        System.out.println("Final " + Arrays.toString(nums1));
    }

    public static void duplicateZeros(int[] arr) {
        int length = arr.length;
        int zeroCount = 0;
        int nextPosition = 0;
        System.out.println("Original " + Arrays.toString(arr));
        outerloop: for (int i = 0; i < length; i++) {
            nextPosition = i + 1;
            if (length == nextPosition) {
                break outerloop;
            }
            if (arr[i] == 0) {
                zeroCount++;
            }
            if (arr[i] == 0 && arr[nextPosition] != 0) {
                for (int j = length - 1; j >= nextPosition; j--) {
                    arr[j] = arr[j - zeroCount];
                }
                i = i + zeroCount;
                zeroCount = 0;
            }
        }
        System.out.println("Final " + Arrays.toString(arr));
    }

    public static void sortedSquares(int[] A) {
        System.out.println("Original " + Arrays.toString(A));
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i] * A[i];
        }
        System.out.println("Square " + Arrays.toString(A));
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
        }
        System.out.println("Sorted " + Arrays.toString(A));
    }

    public static void findNumbers(int[] nums) {
        int evenCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            int n = nums[i];
            while (n != 0) {
                n = n / 10;
                ++count;
            }
            if ((count % 2) == 0) {
                evenCount++;
            }
        }
        System.out.println("Even Count " + evenCount);
    }

    public static void findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int lastConsecutive = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                maxCount++;
            } else {
                if (lastConsecutive < maxCount) {
                    lastConsecutive = maxCount;
                }
                maxCount = 0;
            }
        }
        if (lastConsecutive < maxCount) {
            lastConsecutive = maxCount;
        }
        System.out.println("Consecutive Count " + lastConsecutive);
    }
}