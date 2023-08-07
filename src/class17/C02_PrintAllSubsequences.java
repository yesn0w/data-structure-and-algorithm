package class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class C02_PrintAllSubsequences {

    public static List<String> printAllSubsequences(String str) {
        if (str == null) {
            return new ArrayList<>();
        }
        char[] s = str.toCharArray();
        List<String> res = new ArrayList<>();
        f1(s, 0, res, "");
        return res;
    }

    public static void f1(char[] s, int index, List<String> res, String pre) {
        if (index == s.length) {
            res.add(pre);
            return;
        }
        String no = pre;
        f1(s, index + 1, res, pre);
        String yes = pre + String.valueOf(s[index]);
        f1(s, index + 1, res, yes);
    }

    public static List<String> printAllSubsequencesNoRepeat(String str) {
        if (str == null) {
            return new ArrayList<>();
        }
        char[] s = str.toCharArray();
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        f2(s, 0, set, "");
        for (String cur : set) {
            res.add(cur);
        }
        return res;
    }

    public static void f2(char[] s, int index, Set<String> set, String pre) {
        if (index == s.length) {
            set.add(pre);
            return;
        }
        f2(s, index + 1, set, pre);
        f2(s, index + 1, set, pre + String.valueOf(s[index]));
    }

    public static void main(String[] args) {
        String test = "abccc";
        List<String> res1 = printAllSubsequences(test);
        List<String> res2 = printAllSubsequencesNoRepeat(test);

        for (String str : res1) {
            System.out.println(str);
        }
        System.out.println("--------------------------");
        for (String str : res2) {
            System.out.println(str);
        }
    }
}
