package class38;

public class C03_MSumToN {

    /**
     * 给定一个数num，问是否能分解成连续正整数的和
     * @param num 待分解的数
     * @return 能还是不能
     */
    public static boolean isMSumBF(int num) {
        for (int i = 1; i <= num; i++) {
            int sum = i;
            for (int j = i + 1; j <= num; j++) {
                if (sum + j == num) {
                    return true;
                }
                else if (sum + j > num) {
                    break;
                }
                sum += j;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + ": " + isMSumBF(i) + ", " + isMSumBFPrint(i));
        }
    }

    public static boolean isMSumBFPrint(int num) {
        if (num <= 2) {
            return false;
        }
        // 去掉最右边的1之后，变成了0
        if (num - (num & -num) == 0) {
            return false;
        }
        // 或者：与减1之后的数取与
        // num & (num - 1) == 0
        return true;
    }
}
