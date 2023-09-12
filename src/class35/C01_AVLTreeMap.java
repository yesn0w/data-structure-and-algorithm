package class35;

public class C01_AVLTreeMap {

    public static class AVLNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        AVLNode<K, V> left;
        AVLNode<K, V> right;
        public int height;

        public AVLNode(K key, V value) {
            this.key = key;
            this.value = value;
            height = 1;
        }
    }

    public static class AVLTreeMap<K extends Comparable<K>, V> {
        private AVLNode<K, V> root;
        private int size;

        public AVLTreeMap() {
            root = null;
            size = 0;
        }

        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null ? 0 : cur.right.height) + 1;
            left.height = Math.max(left.left == null ? 0 : left.left.height, left.right == null ? 0 : left.right.height) + 1;
            return left;
        }

        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            cur.height = Math.max(cur.right == null ? 0 : cur.right.height, cur.left == null ? 0 : cur.left.height) + 1;
            right.height = Math.max(right.right == null ? 0 : right.right.height, right.left == null ? 0 : right.left.height) + 1;
            return right;
        }

        private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftHeight = cur.left == null ? 0 : cur.left.height;
            int rightHeight = cur.right == null ? 0 : cur.right.height;
            int leftLeftHeight = cur.left == null ? 0 : (cur.left.left == null ? 0 : cur.left.left.height);
            int leftRightHeight = cur.left == null ? 0 : (cur.left.right == null ? 0 : cur.left.right.height);
            int rightLeftHeight = cur.right == null ? 0 : (cur.right.left == null ? 0 : cur.right.left.height);
            int rightRightHeight = cur.right == null ? 0 : (cur.right.right == null ? 0 : cur.right.right.height);
            if (leftHeight - rightHeight > 1) {
                if (leftLeftHeight >= leftRightHeight) {
                    cur = rightRotate(cur);
                }
                else {
                    cur.left = leftRotate(cur.left);
                    cur = rightRotate(cur);
                }
            }
            else if (rightHeight - leftHeight > 1) {
                if (rightRightHeight >= rightLeftHeight) {
                    cur = leftRotate(cur);
                }
                else {
                    cur.right = rightRotate(cur.right);
                    cur = leftRotate(cur);
                }
            }
            return cur;
        }

        private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new AVLNode<K, V>(key, value);
            }
            if (key.compareTo(cur.key) < 0) {
                cur = add(cur.left, key, value);
            }
            else if (key.compareTo(cur.key) > 0) {
                cur = add(cur.right, key, value);
            }
            // 不要忘记调整高度
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null ? 0 : cur.right.height) + 1;
            return maintain(cur);
        }

        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {

        }

        private AVLNode<K, V> findLastIndex(K key) {

        }

        private AVLNode<K, V> findLastNoSmallIndex(K key) {

        }

        private AVLNode<K, V> findLastNoBigIndex(K key) {

        }


        public int size() {

        }

        public boolean containsKey(K key) {

        }

        public void put(K key, V value) {

        }

        public void remove(K key) {

        }

        public V get(K key) {

        }

        public K firstKey() {

        }

        public K lastKey() {

        }

        public K floorKey(K key) {

        }

        public K ceilingKey(K key) {

        }
    }
}
