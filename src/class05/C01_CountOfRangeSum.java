package class05;

public class C01_CountOfRangeSum {

    /**
     * 给定一个数组arr，两个整数lower和upper，
     * 返回arr中有多少个子数组的累加和在[lower,upper]范围上
     * Leetcode题目：https://leetcode.com/problems/count-of-range-sum/
     */

    public static int countOfRangeSum(int[] arr, int lower, int upper) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] += sum[i - 1] + arr[i];
        }
        return process(sum, 0, arr.length - 1, lower, upper);
    }

    public static int process(int[] arr, int l, int r, int lower, int upper) {
        if (l == r) {
            return arr[l] >= lower && arr[l] <= upper ? 1 : 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid, lower, upper)
                + process(arr, mid + 1, r, lower, upper)
                + merge(arr, l, mid, r, lower, upper);
    }

    public static int merge(int[] arr, int l, int mid, int r, int lower, int upper) {
        // 这是求解的过程
        int res = 0;
        int windowL = l;
        int windowR = l;
        for (int i = mid + 1; i <= r; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (windowR <= mid && arr[windowR] <= max) {
                windowR++;
            }
            while (windowL <= mid && arr[windowL] < min) {
                windowL++;
            }
            res += (windowR - windowL);
        }

        int[] tmp = new int[r - l + 1];
        int tmpIndex = 0;
        int lIndex = l;
        int rIndex = mid + 1;
        while (lIndex <= mid && rIndex <= r) {
            tmp[tmpIndex++] = arr[lIndex] < arr[rIndex] ? arr[lIndex++] : arr[rIndex++];
        }
        while (lIndex <= mid) {
            tmp[tmpIndex++] = arr[lIndex++];
        }
        while (rIndex <= r) {
            tmp[tmpIndex++] = arr[rIndex++];
        }
        for (int i = 0; i < tmp.length; i++) {
            arr[l + i] = tmp[i];
        }
        return res;
    }

}
