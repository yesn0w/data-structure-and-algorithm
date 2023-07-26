package class04;

import genericmethods.Duishuqi;

import java.util.Arrays;

public class NumOfReversePair {

    public static int numOfReversePair(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0,arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int mid, int r) {
        int[] tmp = new int[r - l + 1];
        int indexTmp = 0;
        int indexL = l;
        int indexR = mid + 1;
        int res = 0;
        while (indexL <= mid && indexR <= r) {
            res += arr[indexL] <= arr[indexR] ? (indexR - mid - 1) : 0;
            tmp[indexTmp++] = arr[indexL] <= arr[indexR] ? arr[indexL++] : arr[indexR++];
        }
        while (indexL <= mid) {
            res += (indexR - mid - 1);
            tmp[indexTmp++] = arr[indexL++];
        }
        while (indexR <= r) {
            tmp[indexTmp++] = arr[indexR++];
        }
        for (int i = 0; i < (r - l + 1); i++) {
            arr[l + i] = tmp[i];
        }
        return res;
    }

    public static int mergePerm1(int[] arr, int l, int mid, int r) {
        int[] tmp = new int[r - l + 1];
        int indexTmp = tmp.length - 1;
        int indexL = mid;
        int indexR = r;
        int res = 0;
        while (indexL >= l && indexR >= (mid + 1)) {
            res += arr[indexL] > arr[indexR] ? (indexR - mid) : 0;
            tmp[indexTmp--] = arr[indexL] > arr[indexR] ? arr[indexL--] : arr[indexR--];
        }
        while (indexL >= l) {
            tmp[indexTmp--] = arr[indexL--];
        }
        while (indexR >= (mid + 1)) {
            tmp[indexTmp--] = arr[indexR--];
        }
        for (int i = 0; i < (r - l + 1); i++) {
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
                if (arr[i] > arr[j]) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int times = 20;
        for (int i = 0; i < times; i++) {
            int[] arr = Duishuqi.generateRandomArray(20, 100);
            int[] arr1 = Duishuqi.deepCopyArray(arr);
            int[] arr2 = Duishuqi.deepCopyArray(arr);
            int a = numOfReversePair(arr1);
            int b = toCompare(arr2);
            if (a != b) {
                System.out.println("a:" + a + "," + Arrays.toString(arr1));
                System.out.println("b:" + b + "," + Arrays.toString(arr2));
                System.out.println("错了！");
            }
        }
    }


}
