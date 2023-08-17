package class21;

public class C03_CoinsWayNoLimit {

    /**
     * arr是面值数组，面值不重复，每种不限张数
     * @param arr 面值
     * @param aim 总金额
     * @return 方法数
     */
    public static int coinsWayBF(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int aim) {
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }
        int res = 0;
        for (int i = 0; i * arr[index] <= aim; i++) {
            res += process(arr, index + 1, aim - (i * arr[index]));
        }
        return res;
    }


    public static int coinsWayDP(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < aim + 1; j++) {
                int res = 0;
                for (int k = 0; k * arr[i] <= j; k++) {
                    res += dp[i + 1][j - (k * arr[i])];
                }
                dp[i][j] = res;
            }
        }
        return dp[0][aim];
    }


    public static int coinsWayDPCHT(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = dp[i + 1][j] + (j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0);
            }
        }
        return dp[0][aim];
    }
}
