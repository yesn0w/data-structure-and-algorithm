package class25;

import java.util.Stack;

public class C04_MaximalRectangle {

    /**
     * 由'1'组成的矩形的最大面积
     * @param map '1'代表有点，'0'代表没有点
     * @return 最大面积
     */
    public static int maximalRectangle(char[][] map) {
        int[][] mat = new int[map.length][map[0].length];
        for (int j = 0; j < map[0].length; j++) {
            mat[0][j] = map[0][j] == '0' ? 0 : 1;
        }
        int res = 0;
        for (int i = 1; i < map.length; i++) {
            for (int j = 0; j < map[0].length; i++) {
                mat[i][j] = map[i][j] == '0' ? 0 : mat[i - 1][j] + 1;
            }
            res = Math.max(res, maxArea(mat[i]));
        }
        return res;
    }

    public static int maxArea(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int cur = stack.pop();
                int leftLess = stack.isEmpty() ? -1 : stack.peek();
                res = Math.max(res, arr[cur] * (i - 1 - leftLess));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int leftLess = stack.isEmpty() ? -1 : stack.peek();
            res = Math.max(res, arr[cur] * (arr.length - 1 - leftLess));
        }
        return res;
    }
}
