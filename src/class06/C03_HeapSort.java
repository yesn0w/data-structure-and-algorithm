package class06;


import genericmethods.Swap;

public class C03_HeapSort {

    /**
     * 堆排序的实现
     */

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        Swap.arraySwap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            Swap.arraySwap(arr, 0, --heapSize);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            Swap.arraySwap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 和左右孩子进行比较
            // 左右孩子都有，哪个大哪个上去
            // 右孩子没有，左的大左上去
            int largest = (left + 1 < heapSize && arr[left + 1] > arr[left]) ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            Swap.arraySwap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }
}
