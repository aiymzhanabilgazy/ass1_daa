package algorithms;

import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Invalid input");
        }
        return selectHelper(arr, 0, arr.length - 1, k);
    }

    private static int selectHelper(int[] arr, int left, int right, int k) {
        while (true) {
            if (left == right) {
                return arr[left];
            }


            int pivotIndex = medianOfMedians(arr, left, right); //Choose pivot using median-of-medians


            pivotIndex = partition(arr, left, right, pivotIndex); // Partition around pivot
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

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;

        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return left + n / 2;
        }

        int numMedians = (int) Math.ceil(n / 5.0);
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            int medianIndex = subLeft + (subRight - subLeft) / 2;
            swap(arr, left + i, medianIndex);
        }

        return medianOfMedians(arr, left, left + numMedians - 1);
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right); // move pivot to end
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right); // move pivot to its final place
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
