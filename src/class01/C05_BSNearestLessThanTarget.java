package class01;

public class C05_BSNearestLessThanTarget {

    /*
    有序数组中找到>=num最左的位置
     */

    public static int nearestLessThanTarget(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        int index = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] <= target) {
                index = mid;
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 3, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 12};
        System.out.println(nearestLessThanTarget(arr, 3));
    }

}
