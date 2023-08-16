package class18;

public class C02_TakingCards {

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
        int[][] fTable = new int[arr.length][arr.length];
        int[][] sTable = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                fTable[i][j] = -1;
                sTable[i][j] = -1;
            }
        }
        return Math.max(fMEM(arr, 0, arr.length - 1, fTable, sTable), sMEM(arr, 0, arr.length - 1, fTable, sTable));
    }

    public static int fMEM(int[] arr, int l, int r, int[][] fTable, int[][] sTable) {
        if (l == r) {
            fTable[l][r] = arr[l];
            return arr[l];
        }
        if (fTable[l][r] != -1) {
            return fTable[l][r];
        }
        int res1 = arr[l] + sMEM(arr, l + 1, r, fTable, sTable);
        int res2 = arr[r] + sMEM(arr, l, r - 1, fTable, sTable);
        int res = Math.max(res1, res2);
        fTable[l][r] = res;
        return res;
    }

    public static int sMEM(int[] arr, int l, int r, int[][] fTable, int[][] sTable) {
        if (l == r) {
            sTable[l][r] = 0;
            return 0;
        }
        if (sTable[l][r] != -1) {
            return sTable[l][r];
        }
        int res1 = fMEM(arr, l + 1, r, fTable, sTable);
        int res2 = fMEM(arr, l, r - 1, fTable, sTable);
        int res = Math.max(res1, res2);
        sTable[l][r] = res;
        return res;
    }


    // 严格表依赖动态规划
    public static int maxPointsDP(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int[][] fTable = new int[arr.length - 1][arr.length - 1];
        int[][] sTable = new int[arr.length - 1][arr.length - 1];
        for (int i = 0; i < arr.length; i++) {
            fTable[i][i] = arr[i];
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < arr.length; j++) {
                fTable[i][j] = Math.max(arr[i] + sTable[i + 1][j], arr[j] + sTable[i][j - 1]);
                sTable[i][j] = Math.max(fTable[i + 1][j], fTable[i][j - 1]);
            }
        }
        return Math.max(fTable[0][arr.length - 1], sTable[0][arr.length - 1]);
    }
}
