package class32;

public class C01_IndexTree {

    public static class IndexTree {
        private int[] tree;
        private int N;

        public IndexTree(int size) {
            N = size;
            tree = new int[N + 1];
        }

        public void add(int index, int d) {
            while (index <= N) {
                tree[index] += d;
                index += (index & -index);
            }
        }

        public void sum(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= (index & -index);
            }
        }
    }
}
