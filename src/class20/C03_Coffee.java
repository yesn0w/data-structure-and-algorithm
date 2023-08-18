package class20;

import java.util.Comparator;
import java.util.PriorityQueue;

public class C03_Coffee {

    /**
     * 从0时间点开始，让n个人喝咖啡，做好就能喝，瞬间喝完，杯子空了，可以直接做下一杯
     * @param arr 每个咖啡机冲一杯咖啡的时间
     * @param n 有n个人喝咖啡
     * @return 最少时间
     */
    public static int[] drinkPQ(int[] arr, int n) {
        PriorityQueue<CoffeeMaker> pq = new PriorityQueue<>(new DrinkCoffeeComparator());
        for (int i = 0; i < arr.length; i++) {
            pq.add(new CoffeeMaker(0, arr[i]));
        }
        int[] res = new int[arr.length];
        while (n > 0) {
            CoffeeMaker cur = pq.remove();
            int start = cur.start;
            int end = start + cur.brew;
            res[arr.length - n--] = end;
            pq.add(new CoffeeMaker(end, cur.brew));
        }
        return res;
    }

    /**
     * 对于当前这个人来说，要么杯子刷干净，要么杯子自己晾干净
     * 需要一个数组表示所有杯子，可用的时间点，最开始的时候是int[] available = arr
     * 然后把available排序(暴力方法不排序)，第一个就是可用的时间，然后把这个杯子洗完，或者挥发之后，再加上他做好一杯咖啡的时间，放回available数组里
     * 需要当前这个人喝完的时间点t
     * 如果杯子刷干净，那么是取t1 = Math.max(t, 刷杯机空余出来) + a
     * 如果杯子自然挥发，那么是t2 = t + b
     * 取最小Math.min(t1, t2)
     */
    public static int wash(int[] drink, int index, int a, int b, int available) {
        if (index == drink.length) {
            return 0;
        }

        int tWash = Math.max(available, drink[index]) + a;
        int tWashRest = wash(drink, index + 1, a, b, tWash);
        int res1 = Math.max(tWash, tWashRest);

        int tDry = drink[index] + b;
        int tDryRest = wash(drink, index + 1, a, b, available);
        int res2 = Math.max(tDry, tDryRest);

        return Math.min(res1, res2);
    }

    /**
     * 做咖啡，喝咖啡，承接上一题，杯子有无数个，不是说一个人喝完之后就把这个杯子洗完之后重复用，
     * 而是每次都用一个新的杯子，然后再把所有杯子洗完，所花的最短的时间
     * @param arr 每个咖啡机冲一杯咖啡的时间
     * @param n n个人要喝咖啡
     * @param a 洗杯机洗一个杯子需要a时间
     * @param b 一个杯子自然挥发干净需要b时间
     * @return 所有人喝到咖啡的最少时间
     */
    public static int drinkNWash(int[] arr, int n, int a, int b) {
        int[] drink = drinkPQ(arr, n);
        int res = wash(drink, 0, a, b, 0);
        return res;
    }

    public static int drinkNWashDP(int[] arr, int n, int a, int b) {
        int[] drink = drinkPQ(arr, n);
        int res = washDP(drink, 0, a, b, 0);
        return res;
    }

    // 重点在于边界值怎么划分
    public static int washDP(int[] drink, int index, int a, int b, int available) {
        int maxAvailable = 0;
        for (int i = 0; i < drink.length; i++) {
            maxAvailable = Math.max(maxAvailable, drink[i]) + a;
        }
        // i : index, j : available
        int[][] dp = new int[drink.length + 1][maxAvailable + 1];
        for (int i = drink.length - 1; i >= 0; i--) {
            for (int j = 0; j < maxAvailable + 1; j++) {
                int tWash = Math.max(j, drink[i]) + a;
                // 注意这个边界条件：来源 j <= maxAvailable,所以再+一个a，很可能超边界
                if (tWash > maxAvailable) {
                    break;
                }
                int tWashRest = dp[i + 1][tWash];
                int res1 = Math.max(tWash, tWashRest);

                int tDry = drink[i] + b;
                int tDryRest = dp[i + 1][j];
                int res2 = Math.max(tDry, tDryRest);

                dp[i][j] = Math.min(res1, res2);
            }
        }
        return dp[0][0];
    }

    public static class CoffeeMaker {
        private int start;
        private int brew;

        public CoffeeMaker(int start, int brew) {
            this.start = start;
            this.brew = brew;
        }
    }

    public static class DrinkCoffeeComparator implements Comparator<CoffeeMaker> {
        @Override
        public int compare(CoffeeMaker o1, CoffeeMaker o2) {
            return (o1.start + o1.brew) - (o2.start + o2.brew);
        }
    }
}
