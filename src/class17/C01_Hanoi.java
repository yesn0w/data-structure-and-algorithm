package class17;

public class C01_Hanoi {

    public static void hanoi(int n) {
        if (n > 0) {
            f(n, "left", "right", "mid");
        }
    }

    public static void f(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        }
        else {
            f(n - 1, from, other, to);
            System.out.println("Move " + n + " from " + from + " to " + to);
            f(n - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        hanoi(3);
    }
}
