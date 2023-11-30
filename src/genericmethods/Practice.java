package genericmethods;

public class Practice {

    public static class SegmentTree {

        private int MAXN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private boolean[] update;
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
                sum[index] = arr[l];
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

        public void update(int L, int R, int C, int l, int r, int index) {
            if (L <= l && R >= r) {
                sum[index] = C * (l - r + 1);
                lazy[index] = 0;
                update[index] = true;
                change[index] = C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(index, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, index << 1);
            }
            if (R >= mid + 1) {
                update(L, R, C, mid + 1, r, index << 1 | 1);
            }
            pushUp(index);
        }

        private void pushDown(int index, int ln, int rn) {
            if (update[index]) {
                update[index << 1] = true;
                update[index << 1 | 1] = true;
                change[index << 1] = change[index];
                change[index << 1 | 1] = change[index];
                sum[index << 1] = change[index] * ln;
                sum[index << 1 | 1] = change[index] * rn;
                lazy[index << 1] = 0;
                lazy[index << 1 | 1] = 0;
                update[index] = false;
            }
            if (lazy[index] != 0) {
                lazy[index << 1] += lazy[index];
                sum[index << 1] += lazy[index] * ln;
                lazy[index << 1 | 1] += lazy[index];
                sum[index << 1 | 1] += lazy[index] * rn;
                lazy[index] = 0;
            }
        }

    }
}
