package class25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class C01_MonotonousStack {

    /**
     * arr = [ 3, 1, 2, 4]
     * 	       0  1  2  3
     * [
     * 	   0 : [-1,  1]
     * 	   1 : [-1, -1]
     * 	   2 : [ 1, -1]
     * 	   3 : [ 2, -1]
     * ]
     * 求左右两侧最近的比他小的数（每个数没有重复）-->最小栈
     * @param arr
     * @return
     */
    public static int[][] getNearestLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int cur = stack.pop();
                int leftLess = stack.isEmpty() ? -1 : stack.peek();
                res[cur][0] = leftLess;
                res[cur][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int leftLess = stack.isEmpty() ? -1 : stack.peek();
            res[cur][0] = leftLess;
            res[cur][1] = -1;
        }
        return res;
    }

    public static int[][] getNearestLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> list = stack.pop();
                int leftLess = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                // 这个是错的，要注意，因为要取list当中最后一个数，才是左边小的离得最近的
                // int leftLess = stack.isEmpty() ? -1 : stack.peek().get(0);
                for (int j = 0; j < list.size(); j++) {
                    res[list.get(j)][0] = leftLess;
                    res[list.get(j)][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            }
            else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.add(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            int leftLess = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            // 这里同理
            // int leftLess = stack.isEmpty() ? -1 : stack.peek().get(0);
            for (int j = 0; j < list.size(); j++) {
                res[list.get(j)][0] = leftLess;
                res[list.get(j)][1] = -1;
            }
        }
        return res;
    }
}
