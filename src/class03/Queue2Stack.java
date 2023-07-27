package class03;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Queue;

public class Queue2Stack {

    public static class MyStack {
        public Queue<Integer> queue;
        public Queue<Integer> helper;

        public MyStack() {
            queue = new LinkedList<>();
            helper = new LinkedList<>();
        }

        public void push(int value) {
            queue.offer(value);
        }

        public int pop() {
            if (queue.isEmpty()) {
                throw new RuntimeException("空了，吐不出来了！");
            }
            while (queue.size() > 1) {
                helper.offer(queue.remove());
            }
            int res = queue.poll();
            Queue<Integer> tmp = new LinkedList<>();
            tmp = queue;
            queue = helper;
            helper = tmp;
            return res;
        }

        public int peek() {
            if (queue.isEmpty()) {
                throw new RuntimeException("空的，获取不了栈顶值！");
            }
            while (queue.size() > 1) {
                helper.offer(queue.remove());
            }
            int res = queue.poll();
            helper.offer(res);
            Queue<Integer> tmp = new LinkedList<>();
            tmp = queue;
            queue = helper;
            helper = tmp;
            return res;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }


}
