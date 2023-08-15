package class18;

public class C01_RobotWalk {

    // 暴力递归
    // 1~N个位置，起始点start，目标点aim，恰好K步到
    public static int robotWalkBF(int N, int start, int aim, int K) {
        if ((N < 2 && K > 0) || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return processBF(N, start, aim, K);
    }

    public static int processBF(int N, int cur, int aim, int rest) {
        if (rest == 0) {
            return aim == cur ? 1 : 0;
        }
        if (cur == 1) {
            return processBF(N, 2, aim, rest - 1);
        }
        else if (cur == N) {
            return processBF(N, N - 1, aim, rest - 1);
        }
        else {
            return processBF(N, cur - 1, aim, rest - 1) + processBF(N, cur + 1, aim, rest - 1);
        }
    }
    // 自顶向下的动态规划（傻缓存）
    public static int robotWalkMEM(int N, int start, int aim, int K) {
        if ((N < 2 && K > 0) || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] res = new int[N + 1][K + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < K + 1; j++) {
                res[i][j] = -1;
            }
        }
        processMEM(N, start, aim, K, res);
        return res[aim][K];
    }
    public static int processMEM(int N, int cur, int aim, int rest, int[][] res) {
        if (rest == 0) {
            return aim == cur ? 1 : 0;
        }
        if (res[cur][rest] != -1) {
            return res[cur][rest];
        }
        int ans = 0;
        if (cur == 1) {
            ans =  processMEM(N, 2, aim, rest - 1, res);
        }
        else if (cur == N) {
            ans = processMEM(N, N - 1, aim, rest - 1, res);
        }
        else {
            ans = processMEM(N, cur - 1, aim, rest - 1, res) + processMEM(N, cur + 1, aim, rest - 1, res);
        }
        res[cur][rest] = ans;
        return ans;
    }
    // 严格动态规划
    public static int robotWalkDP(int N, int start, int aim, int K) {
        if ((N < 2 && K > 0) || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] res = new int[N + 1][K + 1];
        res[0][aim] = 0;
        for (int j = 1; j < K + 1; j++) {
            res[1][j] = res[2][j - 1];
            for (int i = 2; j < N; i++) {
                res[i][j] = res[i - 1][j - 1] + res[i + 1][j - 1];
            }
            res[N][j] = res[N - 1][j - 1];
        }
        return res[aim][K];
    }
}
