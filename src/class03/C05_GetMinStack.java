package class03;

import java.util.Stack;

public class C05_GetMinStack {

    public static class MinStack {
        private Stack<Integer> normStack;
        private Stack<Integer> minStack;

        public MinStack() {
            normStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int value) {
            normStack.push(value);
            minStack.push(minStack.isEmpty() ? value : (minStack.peek() < value ? minStack.peek() : value));
        }

        public int pop() {
            if (normStack.isEmpty()) {
                throw new RuntimeException("空了，不能再吐了！");
            }
            minStack.pop();
            return normStack.pop();
        }

        public int getMin() {
            if (normStack.isEmpty()) {
                throw new RuntimeException("空的，不能获得最小值！");
            }
            return minStack.peek();
        }
    }
}
