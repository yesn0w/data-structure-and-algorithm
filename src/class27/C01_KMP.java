package class27;

public class C01_KMP {

    /**
     * KMP算法：解决字符串匹配的问题
     * 用于在str1中寻找str2的位置
     * @param str1 str1
     * @param str2 str2
     * @return str2首字母在str1的位置
     */
    public static int getIndexOf(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0 || str1.length() < str2.length()) {
            return -1;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = new int[s2.length];
        next[0] = -1;
        next[1] = 0;
        next = getNext(s2);
        while (i1 < s1.length && i2 < s2.length) {
            if (s1[i1] == s2[i2]) {
                i1++;
                i2++;
            }
            // 这个分支要有，代表全都没匹配上，i1得换下个位置了
            else if (next[i2] == -1) {
                i1++;
            }
            else {
                i2 = next[i2];
            }
        }

        return i2 == s2.length ? i1 - s2.length : -1;
    }

    public static int[] getNext(char[] s) {
        if (s.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[s.length];
        next[0] = -1;
        next[1] = 0;
        int i1 = 0;
        int cur = 2;
        while (cur < next.length) {
            if (s[cur - 1] == s[i1]) {
                next[cur++] = ++i1;
            }
            else if (i1 > 0) {
                i1 = next[i1];
            }
            else {
                next[cur++] = 0;
            }
        }
        return next;
    }
}
