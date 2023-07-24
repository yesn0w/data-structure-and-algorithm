package class01;

public class EvenTimesOddTimes {

    public static void evenTimesOddTimes(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }
}
