package class20;

public class C02_HorseJump {

    /**
     * 马走日，棋盘格范围10 * 9
     * @param a 目标位置
     * @param b 目标位置
     * @param k 总步数
     * @return 方法数
     */
    public static int jumpBF(int a, int b, int k) {
        return process1(0, 0, k, a, b);
    }

    public static int process1(int i, int j, int rest, int a, int b) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        if (rest == 0) {
            return (i == a && j == b) ? 1 : 0;
        }
        int res = 0;
        res += process1(i - 1, j - 2, rest - 1, a, b);
        res += process1(i - 1, j + 2, rest - 1, a, b);
        res += process1(i + 1, j - 2, rest - 1, a, b);
        res += process1(i + 1, j + 2, rest - 1, a, b);
        res += process1(i - 2, j - 1, rest - 1, a, b);
        res += process1(i - 2, j + 1, rest - 1, a, b);
        res += process1(i + 2, j - 1, rest - 1, a, b);
        res += process1(i + 2, j + 1, rest - 1, a, b);
        return res;
    }


    public static int jumpDP(int a, int b, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest < k + 1; rest++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    int res = 0;
                    res += process(i - 1, j - 2, rest - 1, dp);
                    res += process(i - 1, j + 2, rest - 1, dp);
                    res += process(i + 1, j - 2, rest - 1, dp);
                    res += process(i + 1, j + 2, rest - 1, dp);
                    res += process(i - 2, j - 1, rest - 1, dp);
                    res += process(i - 2, j + 1, rest - 1, dp);
                    res += process(i + 2, j - 1, rest - 1, dp);
                    res += process(i + 2, j + 1, rest - 1, dp);
                    dp[i][j][rest] = res;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int process(int i, int j, int rest, int[][][] dp) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        return dp[i][j][rest];
    }

    public static void main(String[] args) {
        int i = 7;
        int j = 7;
        int k = 10;
        System.out.println(jumpBF(i, j, k));
        System.out.println("--------------");
        System.out.println(jumpDP(i, j, k));
    }
}
