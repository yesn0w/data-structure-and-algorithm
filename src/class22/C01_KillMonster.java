package class22;

public class C01_KillMonster {

    /**
     * 怪兽hp点血，每次砍0~dmg点血，砍times次
     * @param hp
     * @param dmg
     * @param times
     * @return 把怪兽砍死的概率
     */
    public static double right(int hp, int dmg, int times) {
        long all = (long) Math.pow(dmg + 1, times);
        long die = process(hp, dmg, times);
        return (double) ((double) die / (double) all);
    }

    public static long process(int hp, int dmg, int times) {
        if (times == 0) {
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0) {
            return (long) Math.pow(dmg + 1, times);
        }
        long res = 0;
        for (int i = 0; i <= dmg; i++) {
            res += process(hp - i, dmg, times - 1);
        }
        return res;
    }


    public static double rightDP(int hp, int dmg, int times) {
        long all = (long) Math.pow(dmg + 1, times);
        long[][] dp = new long[times + 1][hp + 1];
        dp[0][0] = 1;
        for (int i = 0; i < times + 1; i++) {
            dp[i][0] = (long) Math.pow(dmg + 1, i);
        }
        for (int i = 1; i < times + 1; i++) {
            for (int j = 1; j < hp + 1; j++) {
                int res = 0;
                for (int k = 0; k <= dmg; k++) {
                    if (k <= hp) {
                        res += dp[i - 1][j - k];
                    }
                    else {
                        res += (long) Math.pow(dmg + 1, times - 1);
                    }
                }
                // 这里这样是不对的，因为小于0的部分也要加上去
                // for (int k = 0; k <= dmg && k <= hp; k++) {
                //     res += dp[i - 1][j - k];
                // }
                // dp[i][j] = res;
            }
        }
        double die = (double) dp[times][hp];
        return (double) (die / (double) all);
    }


    public static double rightDPCHT(int hp, int dmg, int times) {
        long all = (long) Math.pow(dmg + 1, times);
        long[][] dp = new long[times + 1][hp + 1];
        dp[0][0] = 1;
        for (int i = 0; i < times + 1; i++) {
            dp[i][0] = (long) Math.pow(dmg + 1, i);
        }
        for (int i = 1; i < times + 1; i++) {
            for (int j = 1; j < hp + 1; j++) {
                dp[i][j] = dp[i][j - 1];
                dp[i][j] += dp[i + 1][j];
                if (hp - 1 >= dmg) {
                    dp[i][j] -= dp[i - 1][hp - 1 - dmg];
                }
                else {
                    dp[i][j] -= (long) Math.pow(dmg + 1, i - 1);
                }
            }
        }
        double die = (double) dp[times][hp];
        return (double) (die / (double) all);
    }
}
