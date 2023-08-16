package class19;

public class C01_Knapsack {

    /**
     * 三个参数
     * @param w w数组，表示所有货物重量分别是多少
     * @param v v数组，表示所有货物价值分别是多少
     * @param bag 背包总共可承受的重量
     * @return 背包所有货物的最大价值
     */
    public static int maxValueBF(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        return process1(w, v, 0, bag);
    }

    public static int process1(int[] w, int[] v, int index, int rest) {
        // 由于边界值的意义，要考虑返回值是什么才合理
        if (rest < 0) {
            return -1;
        }
        // 边界条件要考虑周全
        if (index == w.length) {
            return 0;
        }
        // 递归式子的意义要明确
        int res1 = process1(w, v, index + 1, rest);
        int res2 = 0;
        int tmp = process1(w, v, index + 1, rest - w[index]);
        if (tmp != -1) {
            res2 = v[index] + tmp;
        }
        return Math.max(res1, res2);
    }

    public static int maxValueMEM(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int[][] res = new int[w.length + 1][bag + 1];
        for (int i = 0; i < w.length + 1; i++) {
            for (int j = 0; j < bag + 1; j++) {
                res[i][j] = -1;
            }
        }
        return process2(w, v, 0, bag, res);
    }

    public static int process2(int[] w, int[] v, int index, int rest, int[][] res) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            res[index][rest] = 0;
            return 0;
        }
        if (res[index][rest] != -1) {
            return res[index][rest];
        }
        int res1 = process2(w, v, index + 1, rest, res);
        int res2 = 0;
        int tmp = process2(w, v, index + 1, rest - w[index], res);
        if (tmp != -1) {
            res2 = v[index] + tmp;
        }
        res[index][rest] = Math.max(res1, res2);
        return res[index][rest];
    }


    public static int maxValueDP(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int[][] dp = new int[w.length + 1][bag + 1];
        for (int i = w.length - 1; i >= 0; i--) {
            for (int j = 0; j < bag + 1; j++) {
                int res1 = dp[i + 1][j];
                int res2 = 0;
                int tmp = j - w[i] < 0 ? -1 : dp[i + 1][j - w[i]];
                if (tmp != -1) {
                    res2 = v[i] + tmp;
                }
                dp[i][j] = Math.max(res1, res2);
            }
        }
        return dp[0][bag];
    }


    public static void main(String[] args) {
        int[] w = {3, 2, 4, 7, 3, 1, 7};
        int[] v = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValueBF(w, v, bag));
        System.out.println(maxValueMEM(w, v, bag));
        System.out.println(maxValueDP(w, v, bag));
    }
}
