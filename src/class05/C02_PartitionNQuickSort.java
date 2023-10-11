package class05;

import genericmethods.Swap;

public class C02_PartitionNQuickSort {

    /**
     * 快速排序从1.0到3.0的实现
     */

    public static void quickSortV1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        processV1(arr, 0, arr.length - 1);
    }

    public static void processV1(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = partition(arr, l, r);
        processV1(arr, l, mid - 1);
        processV1(arr, mid + 1, r);
    }

    // 直接以r位置的数做划分
    public static int partition(int[] arr, int l, int r) {
        if (l > r) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int less = l - 1;
        int index = l;
        while (index < r) {
            if (arr[index] <= arr[r]) {
                Swap.arraySwap(arr, index, ++less);
            }
            index++;
        }
        Swap.arraySwap(arr, ++less, r);
        return less;
    }

    public static void quickSortV2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        processV2(arr, 0, arr.length - 1);
    }

    public static void processV2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] equalArea = netherlandsFlag(arr, l, r);
        processV2(arr, l, equalArea[0] - 1);
        processV2(arr, equalArea[1] + 1, r);
    }


    public static void quickSortV3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        processV3(arr, 0, arr.length - 1);
    }

    public static void processV3(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        Swap.arraySwap(arr, l + (int)(Math.random() * (r - l + 1)), r);
        int[] equalArea = netherlandsFlag(arr, l, r);
        processV3(arr, l, equalArea[0] - 1);
        processV3(arr, equalArea[1] + 1, r);
    }

    public static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (arr[l] == arr[r]) {
                index++;
            }
            else if (arr[index] < arr[r]) {
                Swap.arraySwap(arr, index++, ++less);
            }
            else {
                Swap.arraySwap(arr, index, --more);
            }
        }
        Swap.arraySwap(arr, more, r);
        return new int[] {less + 1, more};
    }
}
