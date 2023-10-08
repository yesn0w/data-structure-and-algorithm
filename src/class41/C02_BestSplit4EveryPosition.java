package class41;

public class C02_BestSplit4EveryPosition {

    /**
     * 把题目一中提到的，min{左部分累加和，右部分累加和}，定义为S(N-1)，也就是说：
     * S(N-1)：在arr[0…N-1]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
     * 现在要求返回一个长度为N的s数组，
     * s[i] = 在arr[0…i]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
     * 得到整个s数组的过程，做到时间复杂度O(N)
     */
    /**
     * 重点：这里要注意到的点是下一次再划分不会回退到上一次的边界之前
     * @param arr 数组
     * @return 最大值数组
     */
    public static int[] bestSplit(int[] arr) {
        if (arr == null || arr.length < 1) {
            return new int[]{0};
        }
        int[] res = new int[arr.length];
        res[0] = 0;
        int[] preSum = new int[arr.length + 1];
        preSum[0] = 0;
        preSum[1] = arr[0];
        for (int i = 2; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i - 1];
        }
        int preIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            // preIndex + 2的after就会判断大小关系了，所以这里用preIndex + 1避免越界
            while (preIndex + 1 < i) {
                int before = Math.min(getSum(preSum, 0, preIndex), getSum(preSum, preIndex + 1, arr.length));
                int after = Math.min(getSum(preSum, 0, preIndex + 1), getSum(preSum, preIndex + 2, arr.length));
                if (after >= before) {
                    preIndex++;
                }
                else {
                    break;
                }
            }
            res[i] = Math.min(getSum(preSum, 0, preIndex), getSum(preSum, preIndex + 1, arr.length));
        }
//        for (int i = 1; i < arr.length; i++) {
//            // 不能无条件的右滑到底，必须在下一个没有上一个好的时候跳出来才可以
//            // 有一个可优化的点，求数组任意范围的sum和时，最好把左右坐标都写上，这样更好计算范围，而不是只写一个划分点，
//            // 再去算0到划分点和划分点到右边
//            while (preIndex < i) {
//                int lSum = preSum[preIndex];
//                int rSum = preSum[i + 1] - lSum;
//                res[i] = Math.max(res[i], Math.min(lSum, rSum));
//                preIndex++;
//            }
//        }
        return res;
    }

    public static int getSum(int[] preSum, int l, int r) {
        return preSum[r + 1] - preSum[l];
    }

//    public static int[] bestSplit(int[] arr) {
//        if (arr == null || arr.length < 1) {
//            return new int[]{0};
//        }
//        int[] res = new int[arr.length];
//        res[1] = 0;
//        int preIndex = 0;
//        int preSum = arr[0];
//        int sum = 0;
//        for (int i = 0; i < arr.length; i++) {
//            sum += arr[i];
//        }
//        // 这是不对的，因为他要的是每个范围里的，所以必须得有前缀和
//        for (int i = 1; i < arr.length; i++) {
//            while (preIndex < i) {
//                res[i] = Math.max(res[i], Math.min(preSum, sum - preSum));
//                preIndex++;
//                preSum += arr[preIndex];
//            }
//        }
//        return res;
//    }

}
