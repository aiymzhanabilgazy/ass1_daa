package algorithms;

import metrics.SortMetrics;
import java.util.Arrays;

public class DeterministicSelectWithMetrics {

    public static int select(int[] arr, int k, SortMetrics metrics) {
        if (arr == null || arr.length == 0 || k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Invalid input");
        }
        metrics.startTimer();
        int result = selectHelper(arr, 0, arr.length - 1, k, metrics);
        metrics.stopTimer();
        return result;
    }

    private static int selectHelper(int[] arr, int left, int right, int k, SortMetrics metrics) {
        while (true) {
            if (left == right) {
                return arr[left];
            }

            int pivotIndex = medianOfMedians(arr, left, right, metrics);
            pivotIndex = partition(arr, left, right, pivotIndex, metrics);

            int rank = pivotIndex - left;
            if (k == rank) {
                return arr[pivotIndex];
            } else if (k < rank) {
                right = pivotIndex - 1;
            } else {
                k = k - rank - 1;
                left = pivotIndex + 1;
            }
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right, SortMetrics metrics) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            metrics.incrementComparisons();
            return left + n / 2;
        }

        int numMedians = (int) Math.ceil(n / 5.0);
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            metrics.incrementComparisons();
            int medianIndex = subLeft + (subRight - subLeft) / 2;
            swap(arr, left + i, medianIndex, metrics);
        }
        return medianOfMedians(arr, left, left + numMedians - 1, metrics);
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex, SortMetrics metrics) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right, metrics);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            metrics.incrementComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i, metrics);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right, metrics);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j, SortMetrics metrics) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        metrics.incrementArrayWrites();
    }
}
