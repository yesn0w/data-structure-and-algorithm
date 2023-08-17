package class21;

public class C02_CoinsWayEveryPaperDifferent {

    /**
     * arr是货币数组，每个值代表一个钱，就算面值相同，也认为不同
     * @param arr 这张钱
     * @param aim 目标钱数
     * @return 总共的方法数
     */
    public static int coinsWayBF(int[] arr, int aim) {
        return process1(arr, 0, aim);
    }

    // 从index开始到最后，组成aim，有多少种方法
    public static int process1(int[] arr, int index, int aim) {
        if (aim < 0) {
            return 0;
        }
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }
        else {
            return process1(arr, index + 1, aim)
                    + process1(arr, index + 1, aim - arr[index]);
        }
    }


    public static int coinsWayDP(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][aim] = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < aim + 1; j++) {
                int res1 = dp[i + 1][j];
                int res2 = j - arr[j] >= 0 ? dp[i + 1][j - arr[i]] : 0;
                dp[i][j] = res1 + res2;
            }
        }
        return dp[0][aim];
    }
}
