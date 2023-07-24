package class01;

public class FindOddTimes {

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
