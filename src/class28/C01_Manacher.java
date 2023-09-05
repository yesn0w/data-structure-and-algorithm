package class28;

public class C01_Manacher {

    /**
     * Finding all sub-palindromes in O(N)
     * 找到回文子串
     * @param str 字符串
     * @return 最长回文子串的长度
     */
    public static int manacher(String str) {
        // 边界条件
        if (str == null || str.length() == 0) {
            return 0;
        }
        // 预处理
        char[] s = manacherString(str);
        // 回文半径大小数组
        int[] pRadius = new int[s.length];
        // 讲述中：R代表最右的扩成功的位置
        // coding：最右的扩成功位置的，再下一个位置
        int r = -1;
        int c = -1;
        int res = Integer.MIN_VALUE;
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
            res = Math.max(res, pRadius[i]);
        }
        return res - 1;
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
