package class08;

public class c01_Trie {

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

        }

        public void erase(String word) {

        }

        public int countWordsStartingWith(String pre) {

        }
    }

}
