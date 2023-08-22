package class26;

import java.util.Stack;

public class C01_SumOfSubarrayMinimum {

    /**
     * 返回所有subArray中最小值的累加和
     * @param arr array
     * @return 累加和
     */
    public static int subArrayMinSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] leftLess = getLeftLessEqual(arr);
        int[] rightLessEqual = getRightLess(arr);
        long res = 0;
        for (int i = 0; i < arr.length; i++) {
            long left = (i - leftLess[i]);
            long right = (rightLessEqual[i] - i);
            res += left * right * arr[i];
            res %= 1000000007;
        }
        return (int)res;
    }

    // 以下两个方法为自己写的方法，答案也是正确的
    public static int[] getLeftLess(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int cur = stack.pop();
                res[cur] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            res[stack.pop()] = stack.isEmpty() ? -1 : stack.peek();
        }
        return res;
    }

    public static int[] getRightLessEqual(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int cur = stack.pop();
                res[cur] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            res[stack.pop()] = arr.length;
        }
        return res;
    }

    // 课上的写法
    public static int[] getLeftLessEqual(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                res[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }
        return res;
    }

    public static int[] getRightLess(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                res[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            res[stack.pop()] = arr.length;
        }
        return res;
    }
}
