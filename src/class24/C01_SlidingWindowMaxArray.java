package class24;

import java.util.LinkedList;

public class C01_SlidingWindowMaxArray {

    /**
     * 返回每个窗口内的最大值所组成的数组
     * @param arr 数组
     * @param w 窗口长度
     * @return 窗口内最大值所组成的数组
     */
    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> list = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int r = 0; r < arr.length; r++) {
            // todo: 试着分析等号
            /**
             * 1. 小于当前的（从尾）拿出去
             * 2. 把自己（从尾）插进去
             * 3. 越界的（从头）扔掉
             * 4. 获取窗口的头值
             */
            while (!list.isEmpty() && arr[list.peekLast()] <= arr[r]) {
                list.pollLast();
            }
            list.addLast(r);
            if (list.peekFirst() == (r - w)) {
                list.pollFirst();
            }
            if (r >= w - 1) {
                res[index++] = arr[list.peekFirst()];
            }
        }
        return res;
    }
}
