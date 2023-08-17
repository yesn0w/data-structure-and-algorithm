package class22;

public class C02_MinCoinsNoLimit {

    /**
     * arr是面值数组，张数自由选择，搞出aim元
     * @param arr 面值
     * @param aim 目标
     * @return 最少张数
     */
    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int aim) {
        if (index == arr.length) {
            return aim == 0 ? 0 : Integer.MAX_VALUE;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i * arr[index] <= aim; i++) {
            int tmp = process(arr, index + 1, aim - i * arr[index]);
            if (tmp != Integer.MAX_VALUE) {
                res = Math.min(res, tmp + i);
            }
        }
        return res;
    }


    public static int minCoinsDP(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int j = 1; j < aim + 1; j++) {
            dp[arr.length][j] = Integer.MAX_VALUE;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k * arr[i] <= j; k++) {
                    int tmp = dp[i + 1][j - k * arr[i]];
                    if (tmp != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], tmp + k);
                    }
                }
            }
        }
        return dp[0][aim];
    }


    public static int minCoinsDPCHT(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int j = 1; j < aim + 1; j++) {
            dp[arr.length][j] = Integer.MAX_VALUE;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = dp[i + 1][j];
                // 注意这里的边界条件 >= 0是怎么来的
                if (j - arr[i] >= 0 && dp[i][j - arr[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - arr[i]] + 1);
                }
            }
        }
        return dp[0][aim];
    }
}
