package class32;

public class C02_IndexTree2D {

    private int[][] tree;
    private int[][] nums;
    private int N;
    private int M;

    public C02_IndexTree2D(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        tree = new int[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int x, int y, int value) {
        if (N == 0 || M == 0) {
            return;
        }
        int dif = value - nums[x][y];
        nums[x][y] = value;
        for (int i = x + 1; i <= N; i += (i & (-i))) {
            for (int j = y + 1; j <= M; j += (j & (-j))) {
                tree[i][j] += dif;
            }
        }
    }

    public int sum(int x, int y) {
        int res = 0;
        for (int i = x + 1; i > 0; i -= (i & (-i))) {
            for (int j = y + 1; j > 0; j -= (j & (-j))) {
                res += tree[i][j];
            }
        }
        return res;
    }

    public int sumRegion(int x1, int y1, int x2, int y2) {
        if (N == 0 || M == 0) {
            return 0;
        }
        return sum(x2, y2) + sum(x1- 1, y1 - 1) - sum(x2, y1 - 1) - sum(x1 - 1, y2);
    }
}
