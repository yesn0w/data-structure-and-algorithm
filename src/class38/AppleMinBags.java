package class38;

public class AppleMinBags {

    /**
     * 要不一袋装8个，要不一袋装6个，
     * @param n 苹果数量
     * @return 使用的最少袋子数
     */
    public static int minBagsBF(int n) {
        if (n < 6) {
            return -1;
        }
        int bag8 = n >> 3;
        int rest = n - (bag8 << 3);
        while (bag8 >= 0) {
            if (rest % 6 == 0) {
                return bag8 + (rest / 6);
            }
            rest += 8;
            bag8--;
        }
        return -1;
    }

    public static int minBagsPatternWatch(int n) {
        for (int i = 0; i <= 100; i++) {
            System.out.println(i + ": " + minBagsBF(i));
        }
        return 0;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            System.out.println(i + ": " + minBagsBF(i));
        }
    }
}
