package class25;

import java.util.Stack;

public class C03_LargestRectangleInHistogram {

    /**
     * 非负arr，代表直方图
     * @param heights 非负
     * @return 返回直方图最大长方形面积
     */
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int cur = stack.pop();
                // 要注意这里是-1不是0，可根据面积计算公式，长度试出来
                int leftLess = stack.isEmpty() ? -1 : stack.peek();
                res = Math.max(res, heights[cur] * (i - 1 - leftLess));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            // 此处同理
            int leftLess = stack.isEmpty() ? -1 : stack.peek();
            res = Math.max(res, heights[cur] * (heights.length - 1 - leftLess));
        }
        return res;
    }

    public static int largestRectangleAreaO1OPT(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int res = 0;
        int[] stack = new int[heights.length + 1];
        int index = 0;
        for (int i = 0; i < heights.length; i++) {
            while (index > 0 && heights[stack[index - 1]] >= heights[i]) {
                int cur = stack[--index];
                int leftLess = index == 0 ? -1 : stack[index - 1];
                res = Math.max(res, heights[cur] * (i - 1 - leftLess));
            }
            stack[index++] = i;
        }
        while (index > 0) {
            int cur = stack[--index];
            int leftLess = index == 0 ? -1 : stack[index - 1];
            res = Math.max(res, heights[cur] * (heights.length - 1 - leftLess));
        }
        return res;
    }
}
