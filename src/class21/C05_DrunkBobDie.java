package class21;

public class C05_DrunkBobDie {

    /**
     * 从row, col出发，走k步之后，在N, M范围内活着的概率
     * @return double
     */
    public static double livePossibility(int row, int col, int k, int N, int M) {
        return (double) process1(row, col, k, N, M) / Math.pow(4, k);
    }

    public static long process1(int i, int j, int rest, int N, int M) {
        if (i < 0 || i >= N || j < 0 || j >= M) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        long res = 0;
        res += process1(i - 1, j - 1, rest - 1, N, M);
        res += process1(i - 1, j + 1, rest - 1, N, M);
        res += process1(i + 1, j - 1, rest - 1, N, M);
        res += process1(i + 1, j + 1, rest - 1, N, M);
        return res;
    }


    public static double livePossibilityDP(int row, int col, int k, int N, int M) {
        int[][][] dp = new int[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int step = 1; step < k + 1; step++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int res = 0;
                    res += process(i - 1, j - 1, step - 1, N, M, dp);
                    res += process(i - 1, j + 1, step - 1, N, M, dp);
                    res += process(i + 1, j - 1, step - 1, N, M, dp);
                    res += process(i + 1, j + 1, step - 1, N, M, dp);
                    dp[i][j][step] = res;
                }
            }
        }
        return (double)dp[row][col][k] / Math.pow(4, k);
    }

    public static long process(int i, int j, int rest, int N, int M, int[][][] dp) {
        if (i < 0 || i >= N || j < 0 || j >= M) {
            return 0;
        }
        return dp[i][j][rest];
    }
}
