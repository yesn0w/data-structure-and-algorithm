package class37;

public class C03_AddRemoveGetIndexGreat {

    /**
     * 设计一个结构包含如下两个方法：
     * void add(int index, int num)：把num加入到index位置
     * int get(int index) ：取出index位置的值
     * void remove(int index) ：把index位置上的值删除
     * 要求三个方法时间复杂度O(logN)
     */

    public static class SBTNode<V> {
        public V value;
        public SBTNode<V> left;
        public SBTNode<V> right;
        public int size;

        public SBTNode(V value) {
            this.value = value;
            size = 1;
        }
    }

    public static class SizeBalancedTreeList<V> {
        private SBTNode<V> root;

        private SBTNode<V> leftRotate(SBTNode<V> cur) {
            SBTNode<V> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            right.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            return right;
        }

        private SBTNode<V> rightRotate(SBTNode<V> cur) {
            SBTNode<V> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            left.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            return left;
        }

        private SBTNode<V> maintain(SBTNode<V> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.left == null ? 0 : cur.left.size;
            int rightSize = cur.right == null ? 0 : cur.right.size;
            int leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
            int leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
            int rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            int rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            else if (leftRightSize > rightSize) {
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            else if (rightLeftSize > leftSize) {
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            }
            return cur;
        }

        private SBTNode<V> add(SBTNode<V> node, int index, SBTNode<V> cur) {
            if (node == null) {
                return cur;
            }
            node.size++;
            int leftAndHeadSize = (node.left == null ? 0 : node.left.size) + 1;
            if (index < leftAndHeadSize) {
                node.left = add(node.left, index, cur);
            }
            else {
                node.right = add(node.right, index - leftAndHeadSize, cur);
            }
            node = maintain(node);
            return node;
        }

        private SBTNode<V> remove(SBTNode<V> node, int index) {
            node.size--;
            // 从0开始的index
            int nodeIndex = node.left == null ? 0 : node.left.size;
            if (index != nodeIndex) {
                if (index < nodeIndex) {
                    node.left = remove(node.left, index);
                }
                else {
                    node.right = remove(node.right, index - nodeIndex - 1);
                }
                return node;
            }
            if (node.left == null && node.right == null) {
                return null;
            }
            else if (node.left == null && node.right != null) {
                return node.right;
            }
            else if (node.left != null && node.right == null) {
                return node.left;
            }
            else {
                SBTNode<V> pre = null;
                SBTNode<V> tmp = node.left;
                tmp.size--;
                while (tmp.right != null) {
                    pre = tmp;
                    tmp = tmp.right;
                    tmp.size--;
                }
                if (pre != null) {
                    pre.right = tmp.left;
                    tmp.left = node.left;
                }
                tmp.right = node.right;
                // 首先到这个分支，说明左右都有，然后右边没有任何调整，所以右边一定存在，所以不需要校验
                tmp.size = tmp.right.size + (tmp.left == null ? 0 : tmp.left.size) + 1;
                return tmp;
            }
        }

        private SBTNode<V> get(SBTNode<V> node, int index) {
            int leftSize = node.left == null ? 0 : node.left.size;
            if (index < leftSize) {
                return get(node.left, index);
            }
            else if (index == leftSize) {
                return node;
            }
            else {
                return get(node.right, index - leftSize - 1);
            }
        }

        public void add(int index, V num) {
            SBTNode<V> cur = new SBTNode<V>(num);
            if (root == null) {
                root = cur;
            }
            else {
                if (index <= root.size) {
                    root = add(root, index, cur);
                }
            }
        }

        public V get(int index) {
            SBTNode<V> res = get(root, index);
            return res.value;
        }

        public void remove(int index) {
            if (index >= 0 && size() > index) {
                root = remove(root, index);
            }
        }

        public int size() {
            return root == null ? 0 : root.size;
        }
    }
}
