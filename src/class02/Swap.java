package class02;

public class Swap {

    // a 和 b 不能是同一个位置，不然会变成0
    public static void swap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }
}
