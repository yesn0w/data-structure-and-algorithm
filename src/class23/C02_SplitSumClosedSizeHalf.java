package class23;

public class C02_SplitSumClosedSizeHalf {

    // 总数偶数：两个数组必须一样大小
    // 总数奇数：两个数组大小必须相差1
    public static int right(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;
        int size = arr.length;
        if ((arr.length & 1) == 0) {
            return process(arr, 0, size / 2, sum);
        }
        else {
            return Math.max(process(arr, 0, size / 2, sum), process(arr, 0, size / 2 + 1, sum));
        }
    }

    public static int process(int[] arr, int index, int size, int target) {
        if (index == arr.length) {
            return size == 0 ? 0 : -1;
        }
        int res1 = process(arr, index + 1, size, target);
        int res2 = -1;
        if (arr[index] <= target) {
            int tmp = process(arr, index + 1, size - 1, target - arr[index]);
            if (tmp != -1) {
                res2 = arr[index] + tmp;
            }
        }
        return Math.max(res1, res2);
    }


    public static int rightDP(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;
        int size = arr.length;
        int n = arr.length;
        int[][][] dp = new int[n + 1][size / 2][sum + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < size / 2; j++) {
                for (int k = 0; k < sum + 1; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        for (int k = 0; k < sum + 1; k++) {
            dp[arr.length][0][k] = 0;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < size / 2; j++) {
                for (int k = 0; k < sum + 1; k++) {
                    int res1 = dp[i + 1][j][k];
                    int res2 = -1;
                    // 注意不能越界: j - 1 >= 0
                    if (j - 1 >= 0 && arr[i] < k) {
                        int tmp = dp[i + 1][j - 1][k - arr[i]];
                        if (tmp != -1) {
                            res2 = arr[i] + tmp;
                        }
                    }
                    dp[i][j][j] = Math.max(res1, res2);
                }
            }
        }
        if ((arr.length & 1) == 0) {
            return dp[0][size / 2][sum];
        }
        else {
            return Math.max(dp[0][size / 2][sum], dp[0][size / 2 + 1][sum]);
        }
    }
}
