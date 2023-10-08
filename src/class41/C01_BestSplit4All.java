package class41;

public class C01_BestSplit4All {

    /**
     * 给定一个非负数组arr，长度为N，
     * 那么有N-1种方案可以把arr切成左右两部分
     * 每一种方案都有，min{左部分累加和，右部分累加和}
     * 求这么多方案中，min{左部分累加和，右部分累加和}的最大值是多少？
     * 整个过程要求时间复杂度O(N)
     */
    public static int bestSplit(int[] arr) {
        // 等于1时没有划分的意义，所以要考虑1到corner case
        if (arr == null || arr.length == 0 || arr.length == 1) {

        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int pre = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            pre += arr[i];
            res = Math.max(res, Math.min(pre, sum - pre));
        }
        return res;
    }
}
