package class01;

public class BSNearestLarger {

    public static int nearestLarger(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int mid;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (arr[mid] >= target) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return arr[l] == target ? l : -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 3, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 12};
        System.out.println(nearestLarger(arr, 3));
    }

}
