package class25;

import java.util.Stack;

public class C05_CountSubmatricesWithAllOnes {

    public static int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0) {
            return 0;
        }
        int res = 0;
        int[] height = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            res += count(height);
        }
        return res;
    }

    public static int count(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int cur = stack.pop();
                int leftLess = stack.isEmpty() ? -1 : stack.peek();
                int num = (i - leftLess) * (i - leftLess - 1) / 2;
                int times = arr[cur] - Math.max(arr[i], leftLess == -1 ? 0 : arr[stack.peek()]);
                res += num * times;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int leftLess = stack.isEmpty() ? -1 : stack.peek();
            int num = (arr.length - leftLess) * (arr.length - leftLess - 1) / 2;
            int times = arr[cur] - leftLess == -1 ? 0 : arr[stack.peek()];
            res += num * times;
        }
        return res;
    }
}
