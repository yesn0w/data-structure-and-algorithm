package genericmethods;

public class Practice {

    public static class SegmentTree {

        private int MAXN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private int[] update;
        private int[] change;


        public SegmentTree(int[] origin) {
            int n = origin.length;
            MAXN = n + 1;
            arr = new int[MAXN];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
            sum = new int[MAXN << 2];
            lazy = new int[MAXN << 2];
        }

        public void build(int l, int r, int index) {
            if (l == r) {
                sum[index] = arr[index];
            }
            int child = (l + r) >> 1;
            build(l, child, index << 1);
            build(child + 1, r, index << 1 | 1);
            pushUp(index);
        }

        private void pushUp(int index) {
            sum[index] = sum[index << 1] + sum[index << 1 | 1];
        }

        public void add(int L, int R, int C, int l, int r, int index) {
            // 如果全包，直接懒
            if (L <= l && R >= r) {
                sum[index] += C * (l - r + 1);
                lazy[index] += C;
                return;
            }
            // 如果没有全包
            // (如果有懒，先下发)
            // 给左右add
        }

    }
}
