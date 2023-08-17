package class23;

public class C01_SplitSumClosed {

    /**
     * 分成两组，使得两组和的差距最小
     * @param arr 数组
     * @return 返回较小的和
     */
    public static int right(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return process(arr, 0, sum / 2);
    }

    public static int process(int[] arr, int index, int target) {
        if (index == arr.length) {
            return 0;
        }
        int res1 = process(arr, index + 1, target);
        int res2 = 0;
        if (arr[index] <= target) {
            res2 = arr[index] + process(arr, index + 1, target - arr[index]);
        }
        return Math.max(res1, res2);
    }


    public static int rightDP(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int[][] dp = new int[arr.length + 1][sum / 2 + 1];
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int target = 0; target < sum / 2 + 1; target++) {
                dp[index][target] = dp[index + 1][target];
                if (arr[index] <= target) {
                    dp[index][target] = Math.max(dp[index][target], arr[index] + dp[index + 1][target - arr[index]]);
                }
            }
        }
        return dp[0][sum / 2];
    }
}
