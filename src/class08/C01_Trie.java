package class08;

public class C01_Trie {

    /**
     * 前缀树实现
     */

    public static class Trie {

        class Node {
            public int pass;
            public int end;
            public Node[] nexts;

            public Node() {
                pass = 0;
                end = 0;
                nexts = new Node[26];
            }
        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] wordArray = word.toCharArray();
            Node cur = root;
            cur.pass++;
            int index = 0;
            for (int i = 0; i < wordArray.length; i++) {
                index = wordArray[i] - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
                cur.pass++;
            }
            cur.end++;
        }

        public void erase(String word) {
            if (countWordEqualTo(word) != 0) {
                char[] wordArray = word.toCharArray();
                Node cur = root;
                cur.pass--;
                int index = 0;
                for (int i = 0; i < wordArray.length; i++) {
                    index = wordArray[i] - 'a';
                    // 如果存在，减掉之后又为0，那么说明只有他一个来到过这儿，直接删除整个分支并返回
                    if (--cur.nexts[index].pass == 0) {
                        cur.nexts[index] = null;
                        return;
                    }
                    cur = cur.nexts[index];
                }
                cur.end--;
            }
        }

        public int countWordEqualTo(String word) {
            if (word == null) {
                return 0;
            }
            char[] wordArray = word.toCharArray();
            Node cur = root;
            int index = 0;
            for (int i = 0; i < wordArray.length; i++) {
                index = wordArray[i] - 'a';
                if (cur.nexts[index] == null) {
                    return 0;
                }
                cur = cur.nexts[index];
            }
            return cur.end;
        }

        // 这样写是不对的，因为这有可能是一条路径，并不是单词的结尾，必须要达到结尾才可以，所以要检查node.end
        /**
         * public void int countWordEqualTo(String word) {
         *             char[] charArray = word.toCharArray();
         *             Node cur = root;
         *             int i = 0;
         *             while (i < charArray.length) {
         *                 if (cur.nexts[charArray[i] - 'a'] != null) {
         *                     cur = cur.nexts[charArray[i] - 'a'];
         *                     i++;
         *                 }
         *                 else {
         *                     break;
         *                 }
         *             }
         *             return i == charArray.length ? 1 : 0;
         *         }
         */


        public int countWordsStartingWith(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] preArray = pre.toCharArray();
            Node cur = root;
            int index = 0;
            for (int i = 0; i < preArray.length; i++) {
                index = preArray[i] - 'a';
                if (cur.nexts[index] == null) {
                    return 0;
                }
                cur = cur.nexts[index];
            }
            return cur.pass;
        }
    }

}
