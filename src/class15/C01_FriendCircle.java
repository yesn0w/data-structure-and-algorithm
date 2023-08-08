package class15;

public class C01_FriendCircle {

    public static int findCircleNum(int[][] isConnected) {
        if (isConnected == null) {
            return 0;
        }
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.num;
    }

    public static class UnionFind {
        private int[] father;
        private int[] size;
        // 用于findFather过程中的定位父节点的优化
        private int[] helper;
        private int num;

        public UnionFind(int n) {
            father = new int[n];
            size = new int[n];
            helper = new int[n];
            num = n;
            for (int i = 0; i < n; i++) {
                father[i] = i;
                size[i] = 1;
            }
        }

        public int findFather(int i) {
            int cur = i;
            int index = 0;
            while (cur != father[cur] ) {
                helper[index++] = cur;
                cur = father[cur];
            }
            while (index >= 0) {
                father[helper[--index]] = cur;
            }
            return cur;
        }

        public void union(int i, int j) {
            int iFather = father[i];
            int jFather = father[j];
            if (iFather != jFather) {
                int iFatherSize = size[iFather];
                int jFatherSize = size[jFather];
                if (iFatherSize < jFatherSize) {
                    father[iFather] = jFather;
                    size[jFather] = iFatherSize + jFatherSize;
                }
                else {
                    father[jFather] = iFather;
                    size[iFather] = iFatherSize + jFatherSize;
                }
                num--;
            }
        }


    }

}
