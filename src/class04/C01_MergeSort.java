package class04;

import java.util.Arrays;

public class C01_MergeSort {

    /**
     * 归并排序的递归和非递归实现
     */

    /**
     * 递归实现
     * @param arr 数组
     */
    public static void sortRecursive(int[] arr) {
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

    /**
     * 非递归实现
     * @param arr 数组
     */
    public static void sortNonRecursive(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int mergeSize = 1;
        while (mergeSize < arr.length) {
            // 起始点
            int l = 0;
            while (l < arr.length) {
                // 如果第二组的起点越界了，说明没有第二组了，那么代表着不需要merge了，直接break
                if (l + mergeSize >= arr.length) {
                    break;
                }
                int m = l + mergeSize - 1;
                int r = Math.min(m + mergeSize, arr.length - 1);
                merge(arr, l, m, r);
                l = r + 1;
            }
            // 为了防止mergeSize << 1之后溢出，提前检查
            if (mergeSize > arr.length / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void toCompare(int[] arr) {
        Arrays.sort(arr);
    }



    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 4, 6, 7, 1, 200, 67, 23, 44};
        sortRecursive(arr);
        System.out.println(Arrays.toString(arr));
    }

}
