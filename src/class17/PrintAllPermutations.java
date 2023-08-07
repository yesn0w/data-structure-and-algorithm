package class17;

import java.util.ArrayList;
import java.util.List;

public class PrintAllPermutations {

    public static List<String> printAllPermutations(String str) {
        if (str == null) {
            return new ArrayList<>();
        }
        char[] s = str.toCharArray();
        List<String> res = new ArrayList<>();
        boolean[] visited = new boolean[s.length];
        f1(s, visited, res, "");
        return res;
    }

    public static void f1(char[] s, boolean[] visited, List<String> res, String pre) {
        if (pre.length() == s.length) {
            res.add(pre);
            return;
        }
        for (int i = 0; i < s.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                f1(s, visited, res, pre + String.valueOf(s[i]));
                visited[i] = false;
            }
        }
    }

    public static List<String> printAllPermutationsNoRepeat(String str) {
        if (str == null) {
            return new ArrayList<>();
        }
        char[] s = str.toCharArray();
        List<String> res = new ArrayList<>();
        boolean[] visited = new boolean[s.length];
        f2(s, visited, res, "");
        return res;
    }

    public static void f2(char[] s, boolean[] visited, List<String> res, String pre) {
        if (pre.length() == s.length) {
            res.add(pre);
            return;
        }
        for (int i = 0; i < s.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                f1(s, visited, res, pre + String.valueOf(s[i]));
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        String test = "abc";
        List<String> res = printAllPermutations(test);
        for (String str : res) {
            System.out.println(str);
        }
    }
}
