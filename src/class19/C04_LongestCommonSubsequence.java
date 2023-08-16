package class19;

public class C04_LongestCommonSubsequence {

    // 最长公共子序列（不是子串）
    public static int longestCommonSubsequenceBF(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        return process(s1, s2, str1.length() - 1, str2.length() - 1);
    }

    public static int process(char[] s1, char[] s2, int i, int j) {
        if (i == 0 && j == 0) {
            return s1[i] == s2[j] ? 1 : 0;
        }
        else if (i == 0) {
            if (s1[i] == s2[j]) {
                return 1;
            }
            else {
                return process(s1, s2, i, j - 1);
            }
        }
        else if (j == 0) {
            if (s1[i] == s2[j]) {
                return 1;
            }
            else {
                return process(s1, s2, i - 1, j);
            }
        }
        else {
            int res1 = process(s1, s2, i - 1, j);
            int res2 = process(s1, s2, i, j - 1);
            int res3 = s1[i] == s2[j] ? (1 + process(s1, s2, i - 1, j - 1)) : 0;
            return Math.max(res1, Math.max(res2, res3));
        }
    }


    public static int longestCommonSubsequenceDP(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[][] dp = new int[str1.length()][str2.length()];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int j = 1; j < s2.length; j++) {
            dp[0][j] = s2[j] == s1[0] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < s1.length; i++) {
            dp[i][0] = s1[i] == s2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < s1.length; i++) {
            for (int j = 1; j < s2.length; j++) {
                int res1 = dp[i - 1][j];
                int res2 = dp[i][j - 1];
                int res3 = s1[i] == s2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(res1, Math.max(res2, res3));
            }
        }
        return dp[s1.length - 1][s2.length - 1];
    }

}
