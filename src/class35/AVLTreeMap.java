package class35;

public class AVLTreeMap {

    public static class AVLNode<K extends Comparable<K>, V> {

    }

    public static class AVLTreeMap<K extends Comparable<K>, V> {
        private AVLNode<K, V> root;
        private int size;

        public AVLTreeMap() {
            root = null;
            size = 0;
        }

        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {

        }

        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {

        }

        private AVLNode<K, V> maintain(AVLNode<K, V> cur) {

        }

        private AVLNode<K, V> findLastIndex(K key) {

        }

        private AVLNode<K, V> findLastNoSmallIndex(K key) {

        }

        private AVLNode<K, V> findLastNoBigIndex(K key) {

        }

        private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {

        }

        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {

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
