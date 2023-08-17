package class20;

public class C01_LongestPalindromeSubsequence {

    public static int longestPalindromeSubsequenceBF(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] s = str.toCharArray();
        return process1(s, 0, s.length - 1);
    }

    public static int process1(char[] s, int l, int r) {
        if (l == r) {
            return 1;
        }
        if (l + 1 == r) {
            return s[l] == s[r] ? 2 : 1;
        }
        int res1 = process1(s, l + 1, r);
        int res2 = process1(s, l, r - 1);
        int res3 = process1(s, l + 1, r - 1);
        int res4 = s[l] == s[r] ? 2 + process1(s, l + 1, r - 1) : 0;
        return Math.max(Math.max(res1, res2), Math.max(res3, res4));
    }


    public static int longestPalindromeSubsequenceDP(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] s = str.toCharArray();
        int[][] dp = new int[s.length - 1][s.length - 1];
        for (int i = 0; i < s.length; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < s.length - 1; i++) {
            dp[i][i + 1] = s[i] == s[i + 1] ? 2 : 1;
        }
        for (int i = s.length - 3; i >= 0; i--) {
            for (int j = i + 2; j < s.length; j++) {
                int res1 = dp[i + 1][j];
                int res2 = dp[i][j - 1];
                int res3 = s[i] == s[j] ? 2 + dp[i + 1][j - 1] : 0;
                dp[i][j] = Math.max(res1, Math.max(res2, res3));
            }
        }
        return dp[0][s.length - 1];
    }
}
