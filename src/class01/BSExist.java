package class01;

public class BSExist {
    public static boolean exist(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int l = 0;
        int r = arr.length - 1;
        int mid = 0;
        while (l < r) {
            mid = l + (r - l) >> 1;
            if (arr[mid] == target) {
                return true;
            }
            else if (arr[mid] > target) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }

        return arr[mid] == target;
    }

}
