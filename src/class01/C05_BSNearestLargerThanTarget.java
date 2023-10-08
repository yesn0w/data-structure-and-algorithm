package class01;

public class C05_BSNearestLargerThanTarget {

    public static int nearestLargerThanTarget(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int index = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= num) {
                index = mid;
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return index;
    }
}
