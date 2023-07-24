package class01;

        import java.util.*;

public class FindKTimesAmongMAlarmNotK {

    // k < m
    public static int findKTimesAmongMAlarmNotK(int[] arr, int k, int m) {
        // int: 8 byte, 32 bits
        int[] bits = new int[32];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 32; j++) {
                if (((arr[i]>>j) & 1) == 1) {
                    bits[j]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (bits[i] % m != 0 && bits[i] % m == k) {
                ans |= (1<<i);
            }
            if (bits[i] % m != 0 && bits[i] % m != k) {
                return -1;
            }
        }
        if (ans == 0) {
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }
        return ans;
    }

    public static int toCompare(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            }
            else {
                map.put(i, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == k) {
                return entry.getKey();
            }
            if (entry.getValue() != k && entry.getValue() != m) {
                return -1;
            }
        }
        return -2;
    }

    public static int[] generateRandomArray(int maxNumOfNum, int maxValue, int k, int m) {
        if (k >= m) {
            System.out.println("wrong parameters with k and m!");
            return null;
        }

        int numOfNum = (int) (Math.random() * maxNumOfNum) + 3;
        int arraySize = k + (numOfNum - 1) * m;
        int magic = (int) (Math.random() * maxValue);
//        System.out.println(magic);

        int[] arr = new int[arraySize];
        Set<Integer> set = new HashSet<>();
        set.add(magic);
        int index = 0;
        while (index < k) {
            arr[index++] = magic;
        }
        for (; index < arraySize;) {
            int cur;
            do {
                cur = (int) (Math.random() * maxValue);
            }
            while (set.contains(cur));
//            System.out.print(cur + ",");
            set.add(cur);
            int scope = 0;
            while (scope++ < m) {
                arr[index++] = cur;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxNumOfNum = 10;
        int maxValue = 20;
        int maxK = 3;
        int maxBigger = 3;
        int tryTimes = 10000;
        for (int i = 0; i < tryTimes; i++) {
            int k = (int) (Math.random() * maxK) + 1;
            int m = k + (int) (Math.random() * maxBigger) + 1;
            int[] arr = generateRandomArray(maxNumOfNum, maxValue, k, m);
//            System.out.println(Arrays.toString(arr));
            if (findKTimesAmongMAlarmNotK(arr, k, m) != toCompare(arr, k, m)) {
                System.out.println("错啦！");
            }
        }
    }
}

