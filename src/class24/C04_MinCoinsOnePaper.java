package class24;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class C04_MinCoinsOnePaper {

    /**
     * 基本款，arr是货币数组，数组中有重复，返回最小张数
     * @param arr 货币数组，每个值代表一张面值为arr[i]的钱
     * @param aim 目标
     * @return 最小张数
     */
    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int res1 = process(arr, index + 1, rest);
        int res2 = process(arr, index + 1, rest - arr[index]);
        if (res2 != Integer.MAX_VALUE) {
            res2++;
        }
        return Math.min(res1, res2);
    }


    /**
     * 基本DP款，arr是货币数组，数组中有重复，返回最小张数
     * @param arr 货币数组，每个值代表一张面值为arr[i]的钱
     * @param aim 目标
     * @return 最小张数
     */
    public static int minCoinsDP(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i < arr.length + 1; i++) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[arr.length][0] = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - arr[i]] + 1);
                }
            }
        }
        return dp[0][aim];
    }


    /**
     * 升级DP款，arr是货币数组，数组中有重复，返回最小张数
     * 但是在使用之前，对每种面值做一个统计
     * @param arr 货币数组，每个值代表一张面值为arr[i]的钱
     * @param aim 目标
     * @return 最小张数
     */
    public static int minCoinsDPMap(int[] arr, int aim) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            }
            else {
                map.put(i, 1);
            }
        }
        int[] denomination = new int[map.size()];
        int[] piece = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            denomination[index] = entry.getKey();
            piece[index++] = entry.getValue();
        }
        int[][] dp = new int[denomination.length + 1][aim + 1];
        for (int i = 0; i < denomination.length + 1; i++) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[denomination.length][0] = 0;
        for (int i = denomination.length - 1; i >= 0; i--) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = dp[i + 1][j];
                for (int k = 1; k * denomination[i] <= aim && k <= piece[i]; k++) {
                    if (j - k * denomination[i] >= 0 && dp[i + 1][j - k * denomination[i]] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], k + dp[i + 1][j - k * denomination[i]]);
                    }
                }
            }
        }
        return dp[0][aim];
    }


    public static int minCoinsDPCHT(int[] arr, int aim) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            }
            else {
                map.put(i, 1);
            }
        }
        int[] denomination = new int[map.size()];
        int[] piece = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            denomination[index] = entry.getKey();
            piece[index++] = entry.getValue();
        }
        int[][] dp = new int[denomination.length + 1][aim + 1];
        for (int i = 0; i < denomination.length + 1; i++) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[denomination.length][0] = 0;
        for (int i = denomination.length - 1; i >= 0; i--) {
            for (int mod = 0; mod < Math.min(aim, denomination[i]); mod++) {
                LinkedList<Integer> list = new LinkedList<>();
                for (int cur = mod; cur < aim + 1; cur += denomination[i]) {
                    while (!list.isEmpty() && (dp[i + 1][list.peekLast()] == Integer.MAX_VALUE || dp[i + 1][list.peekLast()] + compensate(list.peekLast(), cur, denomination[i]) >= dp[i + 1][cur])) {
                        list.pollLast();
                    }
                    list.addLast(cur);
                    int overdue = cur - denomination[i] * (piece[i] + 1);
                    if (list.peekFirst() == overdue) {
                        list.pollFirst();
                    }
                    if (dp[i + 1][list.peekFirst()] == Integer.MAX_VALUE) {
                        dp[i][cur] = Integer.MAX_VALUE;
                    }
                    else {
                        dp[i][cur] = dp[i][list.peekFirst()] + compensate(list.peekFirst(), cur, denomination[i]);
                    }
                }
            }
        }
        return dp[0][aim];
    }

    public static int compensate(int first, int cur, int denomination) {
        return (cur - first) / denomination;
    }
}
