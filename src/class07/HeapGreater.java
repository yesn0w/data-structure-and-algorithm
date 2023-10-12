package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HeapGreater<T> {

    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comp;


    public HeapGreater(Comparator<? super T> c) {
        heap = new ArrayList<T>();
        indexMap = new HashMap<T, Integer>();
        heapSize = 0;
        comp = c;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        // 加两个：arraylist add, indexMap put
        heap.add(obj);
        indexMap.put(obj, heapSize);
        // 调整一个：heapInsert
        heapInsert(heapSize++);
    }

    public T pop() {
        // 找一个：arraylist get
        T res = heap.get(0);
        // 交换到最顶
        swap(0, heapSize--);
        // 减两个：arraylist remove, indexMap remove
        indexMap.remove(res);
        heap.remove(heapSize);
        // 调整一个
        heapify(0);
        return res;
    }

    public void remove(T obj) {
        // 找两个：arraylist get最后, indexMap get
        T replace = heap.get(--heapSize);
        int index = indexMap.get(obj);
        // 减两个：arraylist remove, indexMap remove
        heap.remove(heapSize);
        indexMap.remove(obj);
        // 不相等，放两个，调整两个：arraylist set, indexMap put
        if (obj != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            sift(replace);
        }
    }

    public void sift(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    public List<T> getAllElements() {
        List<T> res = new ArrayList<>();
        for (T c : heap) {
            res.add(c);
        }
        return res;
    }

    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) >> 1)) < 0) {
            swap(index, (index - 1) >> 1);
            index = (index -  1) >> 1;
        }
    }

    private void heapify(int index) {
        int left = index << 1 + 1;
        while (left < heapSize) {
            int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
            best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            left = index << 1 + 1;
        }
    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o2, i);
        indexMap.put(o1, j);
    }
}
