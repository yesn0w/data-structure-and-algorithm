package class41;

public class C03_StoneMerge {

    /**
     * 摆放着n堆石子。现要将石子有次序地合并成一堆，规定每次只能选相邻的2堆石子合并成新的一堆
     * 并将新的一堆石子数记为该次合并的得分，求出将n堆石子合并成一堆的最小得分（或最大得分）合并方案
     */


    public static int min(int[] arr) {
        int n = arr.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        preSum[1] = arr[0];
        for (int i = 2 ; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i - 1];
        }
        int[][] res = new int[n][n];
        int[][] best = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            res[i][i + 1] = arr[i];
            best[i][i + 1] = i;
        }
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                int cur = Integer.MAX_VALUE;
                int position = -1;
                for (int index = best[i][j - 1]; index <= best[i + 1][j]; index++) {
                    int tmp = res[i][index] + res[index + 1][j];
                    if (tmp <= cur) {
                        cur = tmp;
                        position = index;
                    }
                }
                best[i][j] = position;
                res[i][j] = cur + getSum(preSum, i, j);
            }
        }
        return res[0][n - 1];
    }
    public static int getSum(int[] preSum, int l, int r) {
        return preSum[r + 1] - preSum[l];
    }


}
