package class06;

import java.util.PriorityQueue;

public class C04_SortArrayDistanceLessThanK {

    /**
     * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k
     * k相对于数组长度来说是比较小的。请选择一个合适的排序策略，对这个数组进行排序。
     */

    public static void sortArrayDistanceLessThanK(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            pq.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            pq.add(arr[index]);
            arr[i] = pq.poll();
        }
        while (!pq.isEmpty()) {
            arr[i++] = pq.poll();
        }
    }
}
