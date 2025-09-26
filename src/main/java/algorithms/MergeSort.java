package algorithms;

import java.util.Arrays;

public class MergeSort {

    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);

        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        
        int[] leftArr = Arrays.copyOfRange(array, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(array, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                array[k++] = leftArr[i++];
            } else {
                array[k++] = rightArr[j++];
            }
        }
        while (i < leftArr.length) {
            array[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            array[k++] = rightArr[j++];
        }
    }
}

