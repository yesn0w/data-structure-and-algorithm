package genericmethods;

public class BitwiseOperation {

    public static void bitwiseOperation() {
        int a = 0;

        // 取得最右边的1
        int mostRightOne = a & (~a + 1);

        // ×2 (也是二进制左移)
        int b = a << 1;

        // ÷2 (也是二进制右移)
        int c = a >> 1;
        int d = a >>> 1;

        // 取反
        int e = ~a;
        // &, |
        int f = 0;
        int g = a & f;
        int h = a | f;
    }
}
