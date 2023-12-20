package class37;

import java.util.HashSet;

public class C01_CountOfRangeSum {

    /**
     * 给定一个数组arr，和两个整数a和b（a<=b）。求arr中有多少个子数组，累加和在[a,b]这个范围上。返回达标的子数组数量
     */

    public static class SBTNode {
        public long key;
        public long size;
        public long all;
        public SBTNode left;
        public SBTNode right;

        public SBTNode(long key) {
            this.key = key;
            size = 1;
            all = 1;
        }
    }

    public static class SizeBalancedTreeSet {
        private SBTNode root;
        private HashSet<Long> set = new HashSet<>();

        // core methods
        private SBTNode rightRotate(SBTNode cur) {
            SBTNode left = cur.left;
            cur.left = left.right;
            left.right = cur;
            left.size = cur.size;
            left.all = cur.all;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            long same = cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
            cur.all = same + (cur.left == null ? 0 : cur.left.all) + (cur.right == null ? 0 : cur.right.all);
            return left;
        }

        private SBTNode leftRotate(SBTNode cur) {
            SBTNode right = cur.right;
            cur.right = right.left;
            right.left = cur;
            right.size = cur.size;
            right.all = cur.all;
            cur.size = (cur.right == null ? 0 : cur.right.size) + (cur.left == null ? 0 : cur.left.size) + 1;
            long same = cur.all = (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
            cur.all = same + (cur.left == null ? 0 : cur.left.all) + (cur.right == null ? 0 : cur.right.all);
            return right;
        }

        private SBTNode maintain(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            long leftSize = cur.left == null ? 0 : cur.left.size;
            long rightSize = cur.right == null ? 0 : cur.right.size;
            long leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
            long leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
            long rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            long rightRightSize = cur.right != null & cur.right.right != null ? cur.right.right.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            else if (leftRightSize > rightSize) {
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
                cur.right = maintain(cur.right);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            }
            else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            }
            else if (rightLeftSize > leftSize) {
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            return cur;
        }

        // todo: 难点，多多复习
        private SBTNode add(SBTNode cur, long key, boolean contains) {
            if (cur == null) {
                // 创建一个新的节点本来就自动初始化all和size了，所以不用再写了
                return new SBTNode(key);
            }
            else {
                cur.all++;
                // 相等就代表包含，那么all的数量+1之后，直接返回就可以了，平衡性也不会变化
                if (key == cur.key) {
                    return cur;
                }
                else {
                    // 不包含的话，size就要加，这是为了平衡性调整，所以要有contains
                    if (!contains) {
                        cur.size++;
                    }
                    if (key < cur.key) {
                        cur.left = add(cur.left, key, contains);
                    }
                    else {
                        cur.right = add(cur.right, key, contains);
                    }
                    return maintain(cur);
                }
            }
        }

        public void add(long sum) {
            boolean contains = set.contains(sum);
            root = add(root, sum, contains);
            set.add(sum);
        }

        // specific methods
        public long lessThanCount(long key) {
            SBTNode cur = root;
            long res = 0;
            while (cur != null) {
                if (key == cur.key) {
                    return res + (cur.left == null ? 0 : cur.left.all);
                }
                else if (key < cur.key) {
                    cur = cur.left;
                }
                else {
                    res += cur.all - (cur.right == null ? 0 : cur.right.all);
                    cur = cur.right;
                }
            }
            return res;
        }
    }

    public static int countRangeSum(int[] nums, int lower, int upper) {
        SizeBalancedTreeSet treeSet = new SizeBalancedTreeSet();
        long sum = 0;
        int res = 0;
        treeSet.add(0);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            long a = treeSet.lessThanCount(sum - lower + 1);
            long b = treeSet.lessThanCount(sum - upper);
            res += (a - b);
            treeSet.add(sum);
        }
        return res;
    }

}
