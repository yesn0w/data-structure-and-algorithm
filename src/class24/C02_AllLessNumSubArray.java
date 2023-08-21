package class24;

import java.util.LinkedList;

public class C02_AllLessNumSubArray {

    /**
     * subArray中subMax - subMin <= num的个数
     * @param arr 数组
     * @param sum 待比较的
     * @return subArray个数
     */
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int r = 0;
        int res = 0;
        LinkedList<Integer> maxList = new LinkedList<>();
        LinkedList<Integer> minList = new LinkedList<>();
        for (int l = 0; l < arr.length; l++) {
            while (r < arr.length) {
                while (!maxList.isEmpty() && arr[maxList.peekLast()] <= arr[l]) {
                    maxList.pollLast();
                }
                while (!minList.isEmpty() && arr[minList.peekLast()] >= arr[l]) {
                    minList.pollLast();
                }
                maxList.addLast(l);
                minList.addLast(l);
                if (arr[maxList.peekFirst()] - arr[minList.peekFirst()] > sum) {
                    break;
                }
                else {
                    r++;
                }
            }
            res += r - l;
            if (maxList.peekFirst() == l) {
                maxList.pollFirst();
            }
            if (minList.peekFirst() == l) {
                minList.pollFirst();
            }
        }
        return res;
    }
}
