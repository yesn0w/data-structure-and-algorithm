package class06;

public class C02_Heap {

    /**
     * 堆结构的实现
     */

    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("堆已经满了，不能再放入元素了！");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
            // heapInsert
        }

        public int pop() {
            // 把[0]拿出来，然后把最后的放到第一位，然后heapify
            if (heapSize == 0) {
                throw new RuntimeException("堆已经空了，不能再弹出元素了！");
            }
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return heap[heapSize];
        }

        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) >> 1]) {
                swap(arr, index, (index - 1) >> 1);
                index = (index - 1) >> 1;
            }
        }

        private void heapify(int[] arr, int index, int heapSize) {
            int left = index << 1 + 1;
            while (left < heapSize) {
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                largest = arr[largest] > arr[index] ? largest : index;
                if (largest == index) {
                    break;
                }
                swap(arr, largest, index);
                index = largest;
                left = index << 1 + 1;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

}
