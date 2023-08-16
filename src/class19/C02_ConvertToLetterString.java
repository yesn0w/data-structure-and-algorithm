package class19;

public class C02_ConvertToLetterString {

    /**
     * “11”可以转化成AA或者K
     * @param str str只含有数字字符
     * @return 一共有多少种转化方案
     */
    public static int numberBF(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] s = str.toCharArray();
        return process(s, 0);
    }

    public static int process(char[] s, int index) {
        if (index == s.length) {
            return 1;
        }
        if (s[index] == '0') {
            return 0;
        }
        int res = 0;
        res += process(s, index + 1);
        // 注意下一位的坐标是index + 1不是index + 2
        if (index + 1 < s.length && ((s[index] - '0') * 10 + (s[index + 1] - '0')) < 27) {
            res += process(s, index + 2);
        }
        return res;
    }

    public static int numberDP(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] s = str.toCharArray();
        int[] dp = new int[s.length + 1];
        dp[s.length] = 1;
        for (int i = s.length - 1; i >= 0; i--) {
            // 注意!= 0时，这个值才有意义
            if (s[i] != 0) {
                int res = 0;
                res += dp[i + 1];
                if (i + 1 < s.length && ((s[i] - '0') * 10 + (s[i + 1] - '0')) < 27) {
                    res += dp[i + 2];
                }
                dp[i] = res;
            }
        }
        return dp[0];
    }
}
