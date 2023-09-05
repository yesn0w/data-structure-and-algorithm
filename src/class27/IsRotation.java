package class27;

public class IsRotation {

    /**
     * 看a是不是b的旋转
     * @param a
     * @param b
     * @return
     */
    public static boolean isRotation(String a, String b) {
        if (a == null || b == null || a.length() == 0 || a.length() != b.length()) {
            return false;
        }
        String b2 = b + b;
        return getIndexOf(b2, a) != -1;
    }

    public static int getIndexOf(String str1, String str2) {
        if (str1.length() < str2.length()) {
            return -1;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[] next = getNext(str2.toCharArray());
        int i1 = 0;
        int i2 = 0;
        while (i2 < s2.length && i1 < s1.length) {
            if (s1[i1] == s2[i2]) {
                i1++;
                i2++;
            }
            else if (next[i2] == -1) {
                i1++;
            }
            else {
                i2 = next[i2];
            }
        }
        return i2 == s2.length ? i1 - i2 : -1;
    }

    public static int[] getNext(char[] s) {
        if (s.length == 1) {
            return new int[] {-1};
        }
        if (s.length == 2) {
            return new int[] {-1, 0};
        }
        int[] next = new int[s.length];
        int index = 2;
        int i = 0;
        while (index < s.length) {
            if (s[index - 1] == s[i]) {
                next[index++] = ++i;
            }
            else if (i > 0) {
                i = next[i];
            }
            else {
                next[index++] = 0;
            }
        }
        return next;
    }
}
