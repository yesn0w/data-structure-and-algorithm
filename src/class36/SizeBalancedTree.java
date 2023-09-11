package class36;

public class SizeBalancedTree {

    public static class SBTNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public SBTNode<K, V> l;
        public SBTNode<K, V> r;
        public int size;

        public SBTNode(K key, V value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>, V> {
        private SBTNode<K, V> root;


        // 内部方法，核心方法
        // 一级核心方法，关键的关键
        private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
            return null;
        }

        private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
            return null;
        }

        private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
            return null;
        }

        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
            return null;
        }

        private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
            return null;
        }

        // 二级核心方法
        private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth) {
            return null;
        }

        private SBTNode<K, V> findLastIndex(K key) {
            return null;
        }

        private SBTNode<K, V> findLastNoSmallIndex(K key) {
            return null;
        }

        private SBTNode<K, V> findLastNoBigIndex(K key) {
            return null;
        }



        // 普通方法，外部调用的方法
        public int size() {
            return 0;
        }

        public boolean containsKey(K key) {
            return false;
        }

        public void put(K key, V value) {

        }

        public V get(K key) {
            return null;
        }

        public void remove(K key) {

        }

        public K getIndexKey(int index) {
            return null;
        }

        public V getIndexValue(int index) {
            return null;
        }

        public K firstKey() {
            return null;
        }

        public K lastKey() {
            return null;
        }

        public K floorKey(K key) {
            return null;
        }

        public K ceilingKey(K key) {
            return null;
        }
    }
}
