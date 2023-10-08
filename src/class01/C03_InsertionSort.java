package class01;

import genericmethods.MatchingMachine;

import java.util.Arrays;

public class C03_InsertionSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; (j - 1) >= 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void toCompare(int[] arr) {
        Arrays.sort(arr);
    }

    public static void main(String[] args) {
        boolean result = true;
        for (int i = 0; i < 5; i++) {
            int[] arr = MatchingMachine.generateRandomArray(20, 20);
            int[] arr1 = MatchingMachine.deepCopyArray(arr);
            int[] arr2 = MatchingMachine.deepCopyArray(arr);
            sort(arr1);
            toCompare(arr2);
            MatchingMachine.printArray(arr1);
            MatchingMachine.printArray(arr2);
            result = MatchingMachine.isEqual(arr1, arr2) ? true : false;
            System.out.println();
        }
        System.out.println(result ? "congratulations!" : "wrong!");
    }
}
