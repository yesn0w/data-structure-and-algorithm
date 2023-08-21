package class24;

import java.util.LinkedList;

public class C03_GasStation {

    public static boolean[] canCompleteCircuit(int[] gas, int[] cost) {
        int[] minus = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            minus[i] = gas[i] - cost[i];
        }
        int n = gas.length * 2 - 1;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = minus[i%gas.length];
        }
        for (int i = 1; i < n; i++) {
            arr[i] += arr[i - 1];
        }
        LinkedList<Integer> window = new LinkedList<>();
        int r = 0;
        for (; r < gas.length; r++) {
            while (!window.isEmpty() && arr[window.peekLast()] >= arr[r]) {
                window.pollLast();
            }
            window.addLast(r);
        }
        int l = 0;
        boolean[] res = new boolean[gas.length];
        int index = 0;
        if (window.peekFirst() >= 0) {
            res[index++] = true;
        }
        for (; r < n; r++) {
            while (!window.isEmpty() && arr[window.peekLast()] >= arr[r]) {
                window.pollLast();
            }
            window.addLast(r);
            if (window.peekFirst() == r - gas.length) {
                window.pollFirst();
            }
            res[index++] = window.peekFirst() >= arr[l++] ? true : false;
        }
        return res;
    }
}
