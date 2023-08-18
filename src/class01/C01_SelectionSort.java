package class01;

import java.util.Arrays;

public class C01_SelectionSort {
	
	private static void sort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}

		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				minIndex = arr[j] < arr[minIndex] ? j : minIndex;
			}
			swap(arr, i, minIndex);
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}


	public static void main(String[] args) {
		int[] arr = new int[]{2, 1, 8, 5, 7};
		System.out.println(Arrays.toString(arr));
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
