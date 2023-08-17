package class22;

public class C03_SplitNumber {

    /**
     * 分割一个数，有多少种分割的方法，后分出来的数要比前面的数小
     * @param n 要分割的数
     * @return 方法总数
     */
    public static int ways(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);
    }

    public static int process(int pre, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < pre) {
            return 0;
        }
        int res = 0;
        for (int i = pre; i <= n; i++) {
            res += process(i, n - i);
        }
        return res;
    }


    public static int waysDP(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        // 如果这里不知道怎么写，用具体例子分析
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int i = n; i >= 1; i--) {
            for (int j = i + 1; j < n + 1; j++) {
                int res = 0;
                for (int k = i; k <= j; k++) {
                    res += dp[k][j - k];
                }
                dp[i][j] = res;
            }
        }
        return dp[1][n];
    }


    public static int waysDPCHT(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        // 如果这里不知道怎么写，用具体例子分析
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int i = n; i >= 1; i--) {
            for (int j = i + 1; j < n + 1; j++) {
                dp[i][j] = dp[i + 1][j];
                dp[i][j] += dp[i][j - i];
            }
        }
        return dp[1][n];
    }
}
