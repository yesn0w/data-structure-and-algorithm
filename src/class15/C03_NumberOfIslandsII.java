package class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class C03_NumberOfIslandsII {

    public static List<Integer> numberOfIslandsII(int m, int n, int[][] positions) {
        if (positions == null || positions.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        UnionFind unionFind = new UnionFind(m, n);
        for (int i = 0; i < positions.length; i++) {
            res.add(unionFind.connect(positions[i][0], positions[i][1]));
        }
        return res;
    }

    public static class UnionFind {
        private int[] father;
        private int[] size;
        private int[] helper;
        private int num;
        private int row;
        private int col;

        public UnionFind(int m, int n) {
            row = m;
            col = n;
            num = 0;
            father = new int[m * n];
            size = new int[m * n];
            helper = new int[m * n];
        }

        public int connect(int i, int j) {
            int index = convert(i, j);
            // 这里判断是否为0是保证positions没有重复置1
            if (size[index] == 0) {
                size[index] = 1;
                father[index] = index;
                num++;
                union(i, j, i - 1, j);
                union(i, j, i + 1, j);
                union(i, j, i, j - 1);
                union(i, j, i, j + 1);
            }
            return num;
        }

        public void union(int i, int j, int x, int y) {
            if (i < 0 || i == row || j < 0 || j == col || x < 0 || x == row || y < 0 || y == col) {
                return;
            }
            // 如果要合并的这两个点（其实是上下左右四个点）没有记录过
            int iIndex = convert(i, j);
            int xIndex = convert(x, y);
            if (size[convert(i, j)] == 0 || size[convert(x, y)] == 0) {
                return;
            }
            int iFather = findFather(iIndex);
            int xFather = findFather(xIndex);
            if (iFather != xFather) {
                if (size[iFather] < size[xFather]) {
                    size[xFather] = size[iFather] + size[xFather];
                    father[iFather] = xFather;
                }
                else {
                    size[iFather] = size[iFather] + size[xFather];
                    father[xFather] = iFather;
                }
                num--;
            }
        }

        public int findFather(int i) {
            int cur = i;
            int index = 0;
            // 注意直接是father[cur], 不是findFather[cur]
            while (cur != father[cur]) {
                helper[index++] = cur;
                cur = father[cur];
            }
            // 要把--index放在外面，不是里面
            while (--index >= 0) {
                father[helper[index]] = cur;
            }
            return cur;
        }

        public int convert(int i, int j) {
            // 是*col, 不是row
            return i * col + j;
        }
    }


    public static List<Integer> numberOfIslandsIIBig(int m, int n, int[][] positions) {
        if (positions == null || positions.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        UnionFindBig unionFindBig = new UnionFindBig();
        for (int i = 0; i < positions.length; i++) {
            res.add(unionFindBig.connect(positions[i][0], positions[i][1]));
        }
        return res;
    }

    public static class UnionFindBig {
        private HashMap<String, String> father;
        private HashMap<String, Integer> size;
        private List<String> helper;
        private int num;

        public UnionFindBig() {
            father = new HashMap<>();
            size = new HashMap<>();
            helper = new ArrayList<>();
            num = 0;
        }

        public String findFather(String str) {
            String cur = str;
            if (helper.size() != 0) {
                helper.clear();
            }
            while (!cur.equals(father.get(cur))) {
                helper.add(cur);
                cur = father.get(cur);
            }
            for (int i = 0; i < helper.size(); i++) {
                father.put(helper.get(i), cur);
            }
            return cur;
        }

        public void union(String str1, String str2) {
            String str1Father = father.get(str1);
            String str2Father = father.get(str2);
            // 必须得保证这两个str1我都保存过（存在），才有意义union
            if (str1Father == null || str2Father == null) {
                return;
            }
            if (!str1Father.equals(str2Father)) {
                if (size.get(str1Father) < size.get(str2Father)) {
                    size.put(str2Father, size.get(str1Father) + size.get(str2Father));
                    father.put(str1Father, str2Father);
                    size.remove(str1Father);
                }
                else {
                    size.put(str1Father, size.get(str1Father) + size.get(str2Father));
                    father.put(str2Father, str1Father);
                    size.remove(str2Father);
                }
                num--;
            }
        }

        public int connect(int i, int j) {
            String keyStr = i + "_" + j;
            if (!size.containsKey(keyStr)) {
                father.put(keyStr, keyStr);
                size.put(keyStr, 1);
                num++;
                union(keyStr, (i + 1) + "_" + j);
                union(keyStr, (i - 1) + "_" + j);
                union(keyStr, i + "_" + (j - 1));
                union(keyStr, i + "_" + (j + 1));
            }
            return num;
        }

    }

    public static void main(String[] args) {
        int[][] positions = new int[][]{{0, 0},{1, 1}, {2, 2}};
        List<Integer> res = numberOfIslandsII(3, 3, positions);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
        System.out.println("------------------");
        List<Integer> res2 = numberOfIslandsIIBig(3, 3, positions);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

}
