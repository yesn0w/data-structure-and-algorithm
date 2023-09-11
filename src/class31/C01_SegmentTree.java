package class31;

public class C01_SegmentTree {

    public static class SegmentTree {

        private int maxN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int[] origin) {
            maxN = origin.length + 1;
            arr = new int[maxN];
            for (int i = 1; i < maxN; i++) {
                arr[i] = origin[i - 1];
            }
            sum = new int[maxN << 2];
            lazy = new int[maxN << 2];
            change = new int[maxN << 2];
            update = new boolean[maxN << 2];
        }

        /**
         * 初始化阶段，填好sum[]
         * @param l 起点坐标
         * @param r 终点坐标
         * @param rt sum[]中的index
         */
        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 + 1);
            pushUp(rt);
        }

        public long query(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return sum[rt];
            }
            int mid = (l + r) << 1;
            // 要记住，在求孩子的结果之前，要把自己现有的lazy任务分发掉才行
            pushDown(rt, mid - l + 1, r - mid);
            long res = 0;
            // 不是单纯的拿sum[rt<<1]，因为你也不能保证全包，所以要接着query
            if (L <= mid) {
                res += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                res += query(L, R, mid + 1, r, rt << 1 + 1);
            }
            return res;
        }

        public void add(int L, int R, int C, int l, int r, int rt) {
            /**
             * 如果任务全包，直接懒
             * 1. 更新sum
             * 2. 更新lazy
             */
            if (L <= l && R >= r) {
                sum[rt] += C * (l - r + 1);
                lazy[rt] += C;
                return;
            }
            /**
             * 如果任务没有全包
             * 1. 先把我之前的lazy下发一层（让下一层接住sum和lazy）
             * 2. 再把当前的任务转交给下一层处理
             * 3. 再把自己的sum更新
             */
            int mid = (l + r) >> 1;
            // 先把原有的lazy下发
            pushDown(rt, mid - l + 1, r - mid);
            // 再把当前的任务转交给下一层处理
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                // 为什么左神写的是 rt << 1 | 1 ?
                add(L, R, C, mid + 1, r, rt << 1 + 1);
            }
            // 最后再把自己的sum更新
            pushUp(rt);
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                // 自己的update, change也要更新
                update[rt] = true;
                change[rt] = C;
                sum[rt] = C * (r - l + 1);
                lazy[rt] = 0;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 + 1);
            }
            pushUp(rt);
        }

        public void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 + 1];
        }

        /**
         * 任务的下发实际包含两个部分
         * 1. add部分的任务下发
         * 2. update部分的任务下发
         * 要先做update部分，后做add部分任务的下放，
         * 因为按照任务进来的先后顺序，如果后update，那么必然导致add为0，
         * 所以先处理update任务，后处理add任务
         * @param rt 父坐标，要将其更新到左右两个孩子 rt<<1 rt<<1+1 中
         * @param ln 左边孩子接收的个数
         * @param rn 右边孩子接收的个数
         */
        public void pushDown(int rt, int ln, int rn) {
            if (update[rt] == true) {
                update[rt << 1] = true;
                change[rt << 1] = change[rt];
                update[rt << 1 + 1] = true;
                change[rt << 1 + 1] = change[rt];
                lazy[rt << 1] = 0;
                sum[rt << 1] = ln * change[rt];
                lazy[rt << 1 + 1] = 0;
                sum[rt << 1 + 1] = rn * change[rt];
                update[rt] = false;
            }
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += ln * lazy[rt];
                lazy[rt << 1 + 1] += lazy[rt];
                sum[rt << 1 + 1] += rn * lazy[rt];
                lazy[rt] = 0;
            }
        }

    }

}
