package algorithms;

import metrics.SortMetrics;

import java.util.Random;

public class QuickSort {

    public static void sort(int[] array) {
        sort(array, new SortMetrics());
    }

    public static void sort(int[] array, SortMetrics m) {
        if (!guardArray(array)) return;
        shuffle(array, new Random(), m);
        quicksort(array, 0, array.length - 1, m);
    }

    private static void quicksort(int[] a, int lo, int hi, SortMetrics m) {
        while (lo < hi) {
            m.enterRecursion();

            int p = partition(a, lo, hi, m);

            // Recurse into smaller partition first
            if (p - lo < hi - p) {
                quicksort(a, lo, p - 1, m);
                lo = p + 1; // tail recursion on larger side
            } else {
                quicksort(a, p + 1, hi, m);
                hi = p - 1;
            }

            m.exitRecursion();
        }
    }
    private static int partition(int[] a, int lo, int hi, SortMetrics m) {
        int pivot = a[hi];
        int i = lo - 1;

        for (int j = lo; j < hi; j++) {
            if (m != null) m.incrementComparisons();
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j, m);
            }
        }
        swap(a, i + 1, hi, m);
        return i + 1;
    }


    private static void swap(int[] a, int i, int j, SortMetrics m) {
        if (i == j) return;
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        if (m != null) m.incrementArrayWrites();
    }

    private static void shuffle(int[] a, Random rand, SortMetrics m) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(a, i, j, m);
        }
    }

    private static boolean guardArray(int[] a) {
        return a != null && a.length > 1;
    }
}
