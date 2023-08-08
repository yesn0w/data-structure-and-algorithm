package class17;

import java.util.ArrayList;
import java.util.List;

public class C03_PrintAllPermutations {

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
//            System.out.println(pre);
            return;
        }
        boolean[] charVisited = new boolean[256];
        for (int i = 0; i < s.length; i++) {
            if (!visited[i] && !charVisited[s[i]]) {
                visited[i] = true;
                charVisited[s[i]] = true;
//                System.out.println(i + "," + pre);
                f2(s, visited, res, pre + String.valueOf(s[i]));
                visited[i] = false;
            }
        }
    }

    public static List<String> printAllPermutationsNoRepeatWithSwap(String str) {
        if (str == null) {
            return new ArrayList<>();
        }
        char[] s = str.toCharArray();
        List<String> res = new ArrayList<>();
        f3(s, 0, res);
        return res;
    }

    public static void f3(char[] s, int index, List<String> res) {
        if (index == s.length) {
            res.add(String.valueOf(s));
        }
        boolean[] visited = new boolean[256];
        for (int i = index; i < s.length; i++) {
            if (!visited[s[i]]) {
                visited[s[i]] = true;
                swap(s, i, index);
                f3(s, index + 1, res);
                swap(s, i, index);
            }
        }
    }

    public static void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    public static void main(String[] args) {
        String test = "acc";
        List<String> res = printAllPermutations(test);
        for (String str : res) {
            System.out.println(str);
        }
        System.out.println("----------------");
        List<String> res2 = printAllPermutationsNoRepeat(test);
        // 注意是res2不是res，要注意打印正确的对象！！！
        for (String str : res2) {
            System.out.println(str);
        }
        System.out.println("---------------");
        List<String> res3 = printAllPermutationsNoRepeatWithSwap(test);
        for (String str : res3) {
            System.out.println(str);
        }
    }
}
