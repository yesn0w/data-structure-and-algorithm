package class23;

public class C03_NQueens {

    /**
     * n皇后问题，每行，每列，每斜线只有一个皇后
     * @param n n个皇后放到n行n列上
     * @return 总的方法数
     */
    public static int numBF(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process1(0, record, n);
    }

    public static int process1(int index, int[] record, int n) {
        if (index == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, index, j)) {
                record[index] = j;
                res += process1(index + 1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int index, int j) {
        for (int i = 0; i < index; i++) {
            if (record[i] == j || Math.abs(i - index) == Math.abs(record[i] - j)) {
                return false;
            }
        }
        return true;
    }


    public static int numBit(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process(limit, 0, 0, 0);
    }

    /**
     *
     * @param limit 所有位全是1，相当于所有位都放了皇后，作为一个边界条件
     * @param colLim 所有皇后造成的列影响
     * @param leftDiagLim 所有皇后造成的左对角线影响
     * @param rightDiagLim 所有皇后造成的右对角线影响
     * @return 总方法数
     */
    public static int process(int limit, int colLim, int leftDiagLim, int rightDiagLim) {
        // 所有皇后在所有列都造成影响了，说明填满了
        if (colLim == limit) {
            return 1;
        }
        int pos = limit & (~(colLim | leftDiagLim | rightDiagLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process(limit, colLim | mostRightOne, (leftDiagLim | mostRightOne) << 1, (rightDiagLim | mostRightOne) >>> 1);
        }
        return res;
    }


    public static void main(String[] args) {
        int n = 15;

//        long start1 = System.currentTimeMillis();
//        System.out.println(numBF(n));
//        long end1 = System.currentTimeMillis();
//        System.out.println("time: " + (end1 - start1) + " ms");

        long start2 = System.currentTimeMillis();
        System.out.println(numBit(n));
        long end2 = System.currentTimeMillis();
        System.out.println("time: " + (end2 - start2) + " ms");
    }
}
