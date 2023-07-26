package class04;

import java.util.Arrays;

public class MergeSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r) {
        int[] tmp = new int[r - l + 1];
        int indexTmp = 0;
        int indexL = l;
        int indexR = mid + 1;
        while (indexL <= mid && indexR <= r) {
            tmp[indexTmp++] = arr[indexL] < arr[indexR] ? arr[indexL++] : arr[indexR++];
        }
        while (indexL <= mid) {
            tmp[indexTmp++] = arr[indexL++];
        }
        while (indexR <= r) {
            tmp[indexTmp++] = arr[indexR++];
        }
        for (int i = 0; i < (r - l + 1); i++) {
            arr[l + i] = tmp[i];
        }
    }

    public static void toCompare(int[] arr) {
        Arrays.sort(arr);
    }



    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 4, 6, 7, 1, 200, 67, 23, 44};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
