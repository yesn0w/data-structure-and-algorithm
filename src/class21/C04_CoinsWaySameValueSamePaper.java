package class21;

import java.util.HashMap;
import java.util.Map;

public class C04_CoinsWaySameValueSamePaper {

    /**
     * arr是货币数组，可重复，相当于一张面值为多少钱的纸币（也就是有数量限制）
     * @param arr
     * @param aim
     * @return
     */
    public static int coinsWayBF(int[] arr, int aim) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            }
            else {
                map.put(i, 1);
            }
        }
        int n = map.size();
        int[] coins = new int[n];
        int[] num = new int[n];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            coins[index] = entry.getKey();
            num[index++] = entry.getValue();
        }
        return process(coins, num, index, aim);
    }

    public static int process(int[] coins, int[] num, int index, int aim) {
        if (index == coins.length) {
            return aim == 0 ? 1 : 0;
        }
        int res = 0;
        for (int i = 0; i < num[index] && i * coins[index] <= aim; i++) {
            res += process(coins, num, index + 1, aim - i * coins[index]);
        }
        return res;
    }


    public static int coinsWayDP(int[] arr, int aim) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            }
            else {
                map.put(i, 1);
            }
        }
        int n = map.size();
        int[] coins = new int[n];
        int[] num = new int[n];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            coins[index] = entry.getKey();
            num[index++] = entry.getValue();
        }

        int[][] dp = new int[coins.length + 1][aim + 1];
        dp[coins.length][0] = 1;
        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = 0; j < aim + 1; j++) {
                int res = 0;
                for (int k = 0; k < num[i] && k * coins[i] <= j; k++) {
                    res += dp[i + 1][j - k * coins[i]];
                }
                dp[i][j] = res;
            }
        }
        return dp[0][aim];
    }


    public static int coinsWayDPCHT(int[] arr, int aim) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            }
            else {
                map.put(i, 1);
            }
        }
        int n = map.size();
        int[] coins = new int[n];
        int[] num = new int[n];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            coins[index] = entry.getKey();
            num[index++] = entry.getValue();
        }

        int[][] dp = new int[coins.length + 1][aim + 1];
        dp[coins.length][0] = 1;
        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = dp[i + 1][j];
                // 要注意这里的+1，是因为考虑到当前j比上一个j多了一个面值的宽度
                if (coins[i] * (num[i] + 1) <= j) {
                    dp[i][j] -= dp[i + 1][j - coins[i] * (num[i] + 1)];
                }
                if (j - coins[i] >= 0) {
                    dp[i][j] += dp[i][j - coins[i]];
                }
            }
        }
        return dp[0][aim];
    }
}
