package class15;

public class NumberOfIslands {

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
        
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1', '0','0'}, {'0','1','0'},{'0','0','1'}};
        System.out.println(numOfIslands4Direction(grid));
    }
}
