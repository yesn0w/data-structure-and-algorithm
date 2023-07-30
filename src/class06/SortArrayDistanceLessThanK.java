package class06;

import java.util.PriorityQueue;

public class SortArrayDistanceLessThanK {

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
