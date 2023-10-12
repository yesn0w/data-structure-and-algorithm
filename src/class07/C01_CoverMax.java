package class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class C01_CoverMax {

    /**
     * 给定很多线段，每个线段都有两个数[start, end]，
     * 表示线段开始位置和结束位置，左右都是闭区间
     * 规定：
     * 1）线段的开始和结束位置一定都是整数值
     * 2）线段重合区域的长度必须>=1
     * 返回线段最多重合区域中，包含了几条线段
     */

    public static int maxCover(int[][] lines) {
        if (lines == null || lines.length == 0) {
            return 0;
        }
        Line[] linesArray = new Line[lines.length];
        Arrays.sort(linesArray, new LineComparator());
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int res = 0;
        for (int i = 0; i < lines.length; i++) {
            Line cur = linesArray[i];
            while (!heap.isEmpty() && heap.peek() <= cur.start) {
                heap.poll();
            }
            heap.add(cur.end);
            res = Math.max(res, heap.size());
        }
        return res;
    }

    public static class Line {
        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class LineComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }


    public static int maxCoverWithoutLineClass(int[][] lines) {
        // lambda表达式写法
        Arrays.sort(lines, (a, b) -> (a[0] - b[0]));
        // 复杂写法
        // Arrays.sort(lines, new Comparator<int[]>() {
        //     @Override
        //     public int compare(int[] o1, int[] o2) {
        //         return 0;
        //     }
        // });
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int res = 0;
        for (int[] line : lines) {
            while (!heap.isEmpty() && heap.peek() <= line[0]) {
                heap.poll();
            }
            heap.add(line[1]);
            res = Math.max(res, heap.size());
        }
        return res;
    }
}
