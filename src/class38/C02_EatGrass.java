package class38;

public class C02_EatGrass {

    /**
     * 吃草，只能吃4^n份
     * @param n 草的数量
     * @return 先手还是后手赢
     */
    public static String whoWin(int n) {
        if (n < 5) {
            return n == 0 || n == 2 ? "后手" : "先手";
        }
        int want = 1;
        while (want <= n) {
            if (whoWin(n - want).equals("后手")) {
                return "先手";
            }
            // want <<= 2;
            // 避免溢出，进行优化
            if (want <= (n >> 2)) {
                want <<= 2;
            }
            else {
                break;
            }
        }
        return "后手";
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + ": " + whoWin(i));
        }
    }

    public static String whoWinPrint(int n) {
        return (n % 5 == 0 || n % 5 == 2) ? "后手" : "先手";
    }
}
