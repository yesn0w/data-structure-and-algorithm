package class31;

import genericmethods.Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class C02_FallingSquares {

    public static class SegmentTree {
        private int[] max;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int size) {
            int N = size + 1;
            max = new int[N << 2];
            change = new int[N << 2];
            update = new boolean[N << 2];
        }

        private void pushUp(int index) {
            max[index] = Math.max(max[index << 1], max[index << 1 | 1]);
        }

        public void update(int L, int R, int C, int l, int r, int index) {
            if (L <= l && R >= r) {
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

        private void pushDown(int index, int i, int i1) {
            if (update[index]) {
                update[index << 1] = true;
                update[index << 1 | 1] = true;
                change[index << 1] = change[index];
                change[index << 1 | 1] = change[index];
                max[index << 1] = change[index];
                max[index << 1 | 1] = change[index];
                update[index] = false;
            }
        }

        public int query(int L, int R, int l, int r, int index) {
            if (L <= l && R >= r) {
                return max[index];
            }
            int mid = (l + r) >> 1;
            pushDown(index, mid - l + 1, r - mid);
            int left = 0;
            int right = 0;
            if (L <= mid) {
                left = query(L, R, l, mid, index << 1);
            }
            if (R >= mid + 1) {
                right = query(L, R, mid + 1, r, index << 1 | 1);
            }
            return Math.max(left, right);
        }
    }

    // 这个函数的作用是编号，与原型贴近，坐标从小到大排序
    public HashMap<Integer, Integer> index(int[][] positions) {
        TreeSet<Integer> pos = new TreeSet<>();
        for (int[] arr : positions) {
            pos.add(arr[0]);
            pos.add(arr[0] + arr[1] - 1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (Integer index : pos) {
            map.put(index, ++count);
        }
        return map;
    }

    public List<Integer> fallingSquares(int[][] positions) {
        HashMap<Integer, Integer> map = index(positions);
        SegmentTree st = new SegmentTree(map.size());
        int max = 0;
        List<Integer> res = new ArrayList<>();
        for (int[] arr : positions) {
            int L = map.get(arr[0]);
            int R = map.get(arr[0] + arr[1] - 1);
            int height = st.query(L, R, 1, map.size(), 1) + arr[1];
            max = Math.max(max, height);
            res.add(max);
            st.update(L, R, max, 1, map.size(), 1);
        }
        return res;
    }
}
