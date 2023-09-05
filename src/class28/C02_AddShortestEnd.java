package class28;

public class C02_AddShortestEnd {

    /**
     * 添加最少的字符，使得形成回文串 ==>
     * @param str 字符串
     * @return
     */
    public static String shortestEnd(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] s = manacherString(str);
        int[] pRadius = new int[s.length];
        int r = -1;
        int c = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i < s.length; i++) {
            pRadius[i] = i < r ? Math.min(pRadius[2 * c - i], r - i) : 1;
            while (i + pRadius[i] < s.length && i - pRadius[i] >= 0) {
                if (s[i + pRadius[i]] == s[i - pRadius[i]]) {
                    pRadius[i]++;
                }
                else {
                    break;
                }
            }
            if (i + pRadius[i] > r) {
                r = i + pRadius[i];
                c = i;
            }
            if (r == s.length) {
                maxContainsEnd = pRadius[i];
                break;
            }
        }
        char[] res = new char[str.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = s[i * 2 + 1];
        }
        return String.valueOf(res);
    }

    public static char[] manacherString(String str) {
        char[] s = str.toCharArray();
        char[] res = new char[s.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : s[index++];
        }
        return res;
    }
}
