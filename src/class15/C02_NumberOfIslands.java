package class15;

public class C02_NumberOfIslands {

    public static int numOfIslands4Direction(char[][] grid) {
        if (grid == null) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    f1(grid, i, j);
                }
            }
        }
        return res;
    }

    public static void f1(char[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        f1(grid, i - 1, j);
        f1(grid, i + 1, j);
        f1(grid, i, j - 1);
        f1(grid, i, j + 1);
    }

    public static int numOfIslandsUnionFind(char[][] grid) {
        if (grid == null) {
            return 0;
        }
        UnionFind unionFind = new UnionFind(grid);
        for (int i = 1; i < grid.length; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                unionFind.union(i, 0, i - 1, 0);
            }
        }
        for (int j = 1; j < grid[0].length; j++) {
            if (grid[0][j] == '1' && grid[0][j - 1] == '1') {
                unionFind.union(0, j, 0, j - 1);
            }
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    if (grid[i - 1][j] == '1') {
                        unionFind.union(i, j, i - 1, j);
                    }
                    if (grid[i][j - 1] == '1') {
                        unionFind.union(i, j, i, j - 1);
                    }
                }
            }
        }
        return unionFind.num;
    }

    public static class UnionFind {
        private int[] father;
        private int[] size;
        private int[] helper;
        private int num;
        private int row;
        private int col;

        public UnionFind(char[][] grid) {
            row = grid.length;
            col = grid[0].length;
            father = new int[row * col];
            size = new int[row * col];
            helper = new int[row * col];
            num = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        int index = convert(i, j);
                        father[index] = index;
                        size[index] = 1;
                        num++;
                    }
                }
            }
        }

        public void union(int i, int j, int x, int y) {
            int iFather = findFather(convert(i, j));
            int jFather = findFather(convert(x, y));
            if (iFather != jFather) {
                if (size[iFather] < size[jFather]) {
                    father[iFather] = jFather;
                    size[jFather] = size[iFather] + size[jFather];
                }
                else {
                    father[jFather] = iFather;
                    size[iFather] = size[iFather] + size[jFather];
                }
                num--;
            }
        }

        public int findFather(int i) {
            int cur = i;
            int index = 0;
            while (cur != father[cur]) {
                helper[index++] = cur;
                cur = father[cur];
            }
            while (--index >= 0) {
                father[helper[index]] = cur;
            }
            return cur;
        }

        public int convert(int i, int j) {
            return i * col + j;
        }


    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1', '0','0'}, {'0','1','0'},{'0','0','1'}};
//        System.out.println(numOfIslands4Direction(grid));
//        System.out.println("-------------");
        System.out.println(numOfIslandsUnionFind(grid));
    }
}
