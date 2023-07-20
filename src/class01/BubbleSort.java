package class01;

import java.util.Arrays;

public class BubbleSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int n = arr.length;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8, 7, 6, 5, 4, 3, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
