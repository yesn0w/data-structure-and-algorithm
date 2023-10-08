package class05;

import genericmethods.MatchingMachine;
import genericmethods.Swap;

import java.util.Arrays;
import java.util.Stack;

public class QuickSortRecursiveNUnrecursive {

    public static void quickSortRecursive(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int randomIndex = (l + (int) (Math.random() * (r - l + 1)));
        Swap.arraySwap(arr, randomIndex, r);
        int[] equalRange = netherlandsFlag(arr, l, r);
        process(arr, l, equalRange[0] - 1);
        process(arr, equalRange[1] + 1, r);
    }

    public static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            }
            else if (arr[index] < arr[r]) {
                Swap.arraySwap(arr, index++, ++less);
            }
            else {
                Swap.arraySwap(arr, index, --more);
            }
        }
        Swap.arraySwap(arr, more, r);
        return new int[]{less + 1, more};
    }

    public static class OpRange {
        public int l;
        public int r;

        public OpRange(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static void quickSortUnrecursiveWithStack(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
//        System.out.println("current array length:" + n);
        int randomIndex = (int) (Math.random() * n);
        Swap.arraySwap(arr, randomIndex, n - 1);
        int[] equalRange = netherlandsFlag(arr, 0, n - 1);
        int l = equalRange[0];
        int r = equalRange[1];
        Stack<OpRange> stack = new Stack<>();
        stack.push(new OpRange(0, l -1));
        stack.push(new OpRange(r + 1, n - 1));
        while (!stack.isEmpty()) {
            OpRange opRange = stack.pop();
            if (opRange.l < opRange.r) {
                randomIndex = opRange.l + ((int) (Math.random() * (opRange.r - opRange.l + 1)));
//                System.out.println(randomIndex);
                Swap.arraySwap(arr, randomIndex, opRange.r);
                equalRange = netherlandsFlag(arr, opRange.l, opRange.r);
                l = equalRange[0];
                r = equalRange[1];
                stack.push(new OpRange(opRange.l, l - 1));
                stack.push(new OpRange(r + 1, opRange.r));
            }
        }
    }

    public static void quickSortUnrecursiveWithQueue(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
    }


    public static void toCompare(int[] arr) {
        Arrays.sort(arr);
    }

    public static void main(String[] args) {
        int times = 20;
        for (int i = 0; i < times; i++) {
            int[] arr = MatchingMachine.generateRandomArray(20, 100);
            int[] arr1 = MatchingMachine.deepCopyArray(arr);
            int[] arr2 = MatchingMachine.deepCopyArray(arr);
//            quickSortRecursive(arr1);
            quickSortUnrecursiveWithStack(arr1);
            toCompare(arr2);
            if (!MatchingMachine.isEqual(arr1, arr2)) {
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                System.out.println("错了！");
            }
        }
    }
}
