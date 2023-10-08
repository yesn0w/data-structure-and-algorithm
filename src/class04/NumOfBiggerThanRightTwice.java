package class04;

import genericmethods.MatchingMachine;

import java.util.Arrays;

public class NumOfBiggerThanRightTwice {

    public static int numOfBiggerThanRightTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int mid, int r) {
        int res = 0;
        int windowR = mid + 1;
        for (int i = l; i <= mid; i++) {
            while (windowR <= r && (long) arr[i] > (long) (arr[windowR] << 1)) {
                windowR++;
            }
            res += (windowR - mid - 1);
        }
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
        for (int i = 0; i < tmp.length; i++) {
            arr[l + i] = tmp[i];
        }
        return res;
    }

    public static int toCompare(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int times = 20;
        for (int i = 0; i < times; i++) {
            int[] arr = MatchingMachine.generateRandomArray(20, 100);
            int[] arr1 = MatchingMachine.deepCopyArray(arr);
            int[] arr2 = MatchingMachine.deepCopyArray(arr);
            int a1 = numOfBiggerThanRightTwice(arr1);
            int a2 = toCompare(arr2);
            if (a1 != a2) {
                System.out.println("a1:" + a1 + "," + Arrays.toString(arr1));
                System.out.println("a2:" + a2 + "," + Arrays.toString(arr2));
                System.out.println("错了！");
            }
        }
    }

}
