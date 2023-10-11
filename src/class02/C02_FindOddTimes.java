package class02;

public class C02_FindOddTimes {

    /**
     * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
     */

    public static void findOddTimes(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    public static void perm1(int[] arr) {
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        System.out.println(eor);
    }
}
