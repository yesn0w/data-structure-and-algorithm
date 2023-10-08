package class01;

public class C06_RangeSmallest {

    /**
     * 局部最小值问题
     * 定义何为局部最小值：
     * arr[0] < arr[1]，0位置是局部最小；
     * arr[N-1] < arr[N-2]，N-1位置是局部最小；
     * arr[i-1] > arr[i] < arr[i+1]，i位置是局部最小；
     * 给定一个数组arr，已知任何两个相邻的数都不相等，找到随便一个局部最小位置返回
     */

    /**
     * 用左右边界的条件来限制：左边下降，右边上扬，则中间必有低点
     * @param arr 数组
     * @return index
     */
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int l = 1;
        int r = arr.length - 2;
        int mid = 0;
        while (l < r) {
            mid = (l + r) / 2;
            if (arr[mid] > arr[mid - 1]) {
                r = mid - 1;
            }
            else if (arr[mid] > arr[mid + 1]) {
                l = mid + 1;
            }
            else {
                return mid;
            }
        }
        return l;
    }
}
