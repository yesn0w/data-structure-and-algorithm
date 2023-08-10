package class18;

public class RobotWalk {

    // 暴力递归
    // 1~N个位置，起始点start，目标点aim，恰好K步到
    public static int robotWalk1(int N, int start, int aim, int K) {
        if ((N < 2 && K > 0) || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process1(N, start, aim, K);
    }

    public static int process1(int N, int cur, int aim, int res) {
        if (res == 0) {
            return aim == cur ? 1 : 0;
        }
        if (cur == 1) {
            return process1(N, 2, aim, res - 1);
        }
        else if (cur == N) {
            return process1(N, N - 1, aim, res - 1);
        }
        else {
            return process1(N, cur - 1, aim, res - 1) + process1(N, cur + 1, aim, res - 1);
        }
    }
    // 自顶向下的动态规划（傻缓存）
    public static int robotWalk2(int N, int start, int aim, int K) {
        if ((N < 2 && K > 0) || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] res = new int[N + 1][K + 1];
        process2(N, start, aim, K, res);
        return res[N][K];
    }
    public static int process2(int N, int cur, int aim, int rest, int[][] res) {
        if (res == 0) {
            return aim == cur ? 1 : 0;
        }
        if (cur == 1) {
            return process1(N, 2, aim, res - 1);
        }
        else if (cur == N) {
            return process1(N, N - 1, aim, res - 1);
        }
        else {
            return process1(N, cur - 1, aim, res - 1) + process1(N, cur + 1, aim, res - 1);
        }
    }
    // 严格动态规划

}
