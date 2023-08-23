package class26;

public class C02_FibonacciProblem {

    public static int fBF(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fBF(n - 1) + fBF(n - 2);
    }


    public static int fSR(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = product(res, t);
            }
            t = product(t, t);
        }
        return res;
    }

    public static int[][] product(int[][] a, int[][] b) {
        int n = a.length;
        int m = b[0].length;
        int k = a[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int index = 0; index < k; index++) {
                    res[i][j] += a[i][index] * b[index][j];
                }
            }
        }
        return res;
    }
}
