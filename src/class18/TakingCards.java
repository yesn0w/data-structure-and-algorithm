package class18;

public class TakingCards {

    // 暴力递归
    // 返回获胜者的最大分数
    public static int maxPointsBF(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return Math.max(fMaxBF(arr, 0, arr.length - 1), sMaxBF(arr, 0, arr.length - 1));
    }

    public static int fMaxBF(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int res1 = arr[l] + sMaxBF(arr, l + 1, r);
        int res2 = arr[r] + sMaxBF(arr, l, r - 1);
        return Math.max(res1, res2);
    }

    public static int sMaxBF(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int res1 = fMaxBF(arr, l + 1, r);
        int res2 = fMaxBF(arr, l, r - 1);
        return Math.max(res1, res2);
    }

    // 自顶向下的动态规划（傻缓存）
    public static int maxPointsMEM(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int[][] fMaxMEMs = new int[arr.length][arr.length];
        int[][] sMaxMEMs = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                fMaxMEMs[i][j] = -1;
                sMaxMEMs[i][j] = -1;
            }
        }
        return Math.max(fMaxMEMs[0][arr.length - 1], fMaxMEMs[0][arr.length - 1]);
    }

    public static int fMaxMEM(int[] arr, int l, int r, int[][] fMaxMEMs, int[][] sMaxMEMs) {
        if (l == r) {
            fMaxMEMs[l][r] = arr[l];
            return arr[l];
        }

    }

    public static int sMaxMEM(int[] arr, int l, int r, int[][] fMaxMEMs, int[][] sMaxMEMs) {
        if (l == r) {
            sMaxMEMs[l][r] = 0;
            return 0;
        }
    }
    // 严格动态规划

}
