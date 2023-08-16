package class19;

import java.util.HashMap;
import java.util.Map;

public class C03_StickersToSpellWord {

    /**
     * 用所给的贴纸，可以剪裁，拼成目的字符串，最少的贴纸数
     * @param stickers 所有贴纸，可以重复使用，不限制数量
     * @param target 目标字符串
     * @return 使用的最少贴纸张数
     */
    public static int minStickersBF(String[] stickers, String target) {
        int res = process1(stickers, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static int process1(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        // 如果是for循环就没必要考虑选或者不选的问题了，因为所有一定是会走到的
        for (String str : stickers) {
            String rest = minus(target, str);
            if (rest.length() != target.length()) {
                res = Math.min(res, process1(stickers, target));
            }
        }
        return res + (res == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String s1, String s2) {
        int[] count = new int[26];
        for (char c : s1.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : s2.toCharArray()) {
            count[c - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    sb.append(count[i] + 'a');
                }
            }
        }
        return sb.toString();
    }


    public static int minStickersBFStr2CharCountArray(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int[][] sCount = new int[stickers.length][26];
        int[] tCount = new int[26];
        for (int i = 0; i < stickers.length; i++) {
            for (char c : stickers[i].toCharArray()) {
                sCount[i][c - 'a']++;
            }
        }
        for (char c : target.toCharArray()) {
            tCount[c - 'a']++;
        }
        int res = process2(sCount, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static int process2(int[][] sCount, String target) {
        if (target.length() == 0) {
            return 0;
        }
        char[] tCharArray = target.toCharArray();
        int[] tCount = new int[26];
        for (char c : tCharArray) {
            tCount[c - 'a']++;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < sCount.length; i++) {
            int[] cur = sCount[i];
            if (cur[tCharArray[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; i < 26; j++) {
                    if (tCount[j] > 0) {
                        int count = tCount[j] - cur[j];
                        for (int k = 0; k < count; k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = sb.toString();
                res = Math.min(res, process2(sCount, rest));
            }
        }
        return res + (res == Integer.MAX_VALUE ? 0 :  1);
    }


    // 记忆化搜索是最终归宿
    public static int minStickersMEM(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int[][] sCount = new int[stickers.length][26];
        int[] tCount = new int[26];
        for (int i = 0; i < stickers.length; i++) {
            for (char c : stickers[i].toCharArray()) {
                sCount[i][c - 'a']++;
            }
        }
        for (char c : target.toCharArray()) {
            tCount[c - 'a']++;
        }
        HashMap<String, Integer> mem = new HashMap<>();
        mem.put("", 0);
        int res = process(sCount, target, mem);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static int process(int[][] sCount, String target, Map<String, Integer> mem) {
        if (mem.containsKey(target)) {
            return mem.get(target);
        }
        char[] tCharArray = target.toCharArray();
        int[] tCount = new int[26];
        for (char c : tCharArray) {
            tCount[c - 'a']++;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < sCount.length; i++) {
            int[] cur = sCount[i];
            if (cur[tCharArray[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; i < 26; j++) {
                    if (tCount[j] > 0) {
                        int count = tCount[j] - cur[j];
                        for (int k = 0; k < count; k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = sb.toString();
                res = Math.min(res, process2(sCount, rest));
            }
        }
        int ret = res + (res == Integer.MAX_VALUE ? 0 : 1);
        mem.put(target, ret);
        return ret;
    }

}
