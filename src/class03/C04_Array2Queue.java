package class03;

public class C04_Array2Queue {

    public static class MyQueue {
        private int[] arr;
        private int head;
        private int tail;
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            head = 0;
            tail = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("满了，不能再放了！");
            }
            size++;
            // 这样不行，不要忘记坐标越界
            // arr[tail++] = value;
            arr[tail] = value;
            tail = nextIndex(tail);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("空了，不能再吐了！");
            }
            size--;
            int res = arr[head];
            // 可省略，因为坐标过去之后这个值也没有意义了
            // arr[head] = 0;
            head = nextIndex(head);
            return res;
        }

        public int nextIndex(int index) {
            if (index == limit - 1) {
                return 0;
            }
            return index + 1;
        }
    }
}
