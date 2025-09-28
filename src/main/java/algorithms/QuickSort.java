package algorithms;

import java.util.Random;
import metrics.SortMetrics;

public class QuickSort {
    private static final Random rand = new Random();

    public static void sort(int[] array) {
        sort(array, null);
    }

    public static void sort(int[] array, SortMetrics m) {
        if (array == null || array.length <= 1) return;

        if (m != null) {
            m.reset();
            m.startTimer();
        }

        quicksort(array, 0, array.length - 1, m);

        if (m != null) {
            m.stopTimer();
        }
    }

    private static void quicksort(int[] array, int left, int right, SortMetrics m) {
        while (left < right) {
            if (m != null) m.enterRecursion();
            try {
                int pivotIndex = left + rand.nextInt(right - left + 1); //randomized pivot
                int pivotValue = array[pivotIndex];
                swap(array, pivotIndex, right, m);

                int partitionIndex = partition(array, left, right, pivotValue, m);

                if (partitionIndex - left < right - partitionIndex) { //recurse on the smaller partition
                    quicksort(array, left, partitionIndex - 1, m);
                    left = partitionIndex + 1; //iterate over the larger one
                } else {
                    quicksort(array, partitionIndex + 1, right, m);
                    right = partitionIndex - 1; //iterate over the larger one
                }
            } finally {
                if (m != null) m.exitRecursion();
            }
        }
    }

    private static int partition(int[] array, int left, int right, int pivotValue, SortMetrics m) {
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (m != null) m.incrementComparisons();
            if (array[i] < pivotValue) {
                swap(array, i, storeIndex, m);
                storeIndex++;
            }
        }
        swap(array, storeIndex, right, m);
        return storeIndex;
    }

    private static void swap(int[] array, int i, int j, SortMetrics m) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            if (m != null) m.incrementArrayWrites();
        }
    }
}
