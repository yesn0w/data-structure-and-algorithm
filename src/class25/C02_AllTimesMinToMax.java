package class25;

import java.util.Stack;

public class C02_AllTimesMinToMax {

    /**
     * 正数arr, 所有subArray中subSum * subMin的最大值
     * 转化-->所有以arr[i]为最小值的数组（加起来就是所有数组）
     * @param arr 正数arr
     * @return 最大值
     */
    public static int max(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // 前缀和数组
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }

        int res = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                int cur = stack.pop();
                res = Math.max(res, arr[cur] * (stack.isEmpty() ? preSum[i - 1] : preSum[i - 1] - stack.peek()));
                // int leftLess = stack.isEmpty() ? stack.peek() : -1;
                // res = Math.max(res, arr[cur] * (preSum[i - 1] - (leftLess == -1 ? 0 : stack.peek())));
            }
            // 别忘了弹完之后再放进去当前的
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            // 注意，这里其实是31，32行的另一种写法，同30
            res = Math.max(res, arr[cur] * (stack.isEmpty() ? preSum[arr.length - 1] : preSum[arr.length - 1] - stack.peek()));
        }
        return res;
    }
}
