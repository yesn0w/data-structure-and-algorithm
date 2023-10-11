package class03;

import java.util.Stack;

public class C06_TwoStack2Queue {

    public static class MyQueue {
        public Stack<Integer> stack;
        public Stack<Integer> helper;

        public MyQueue() {
            stack = new Stack<Integer>();
            helper = new Stack<Integer>();
        }

        public void add(int pushInt) {
            stack.push(pushInt);
        }

        public int poll() {
            if (helper.isEmpty() && stack.isEmpty()) {
                throw new RuntimeException("队列是空的，没有数！");
            }
            if (helper.isEmpty()) {
                pour2helper();
            }
            return helper.pop();
        }

        public int peek() {
            if (helper.isEmpty() && stack.isEmpty()) {
                throw new RuntimeException("队列是空的，没有数！");
            }
            if (helper.isEmpty()) {
                pour2helper();
            }
            return helper.peek();
        }

        public void pour2helper() {
            while (!stack.isEmpty()) {
                helper.push(stack.pop());
            }
        }
    }

    public static void main(String[] args) {
        MyQueue test = new MyQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
