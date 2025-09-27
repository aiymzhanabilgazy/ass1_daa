package algorithms;

import java.util.Arrays;
import metrics.SortMetrics;

public class MergeSort {
    private static final int CUTOFF = 10;

    public static void sort(int[] array) {
    sort(array, null);
    }

    public static void sort(int[] array, SortMetrics m) {

        if (array == null || array.length <= 1) return;
        if (m != null){
            m.reset();
            m.startTimer();
            m.incrementAllocations();
        }
        int[] temp = new int[array.length]; //reusable buffer
        mergeSort(array, temp, 0, array.length - 1, m);
        if (m != null) m.stopTimer();

    }
    private static void mergeSort(int[] array, int[] temp, int left, int right, SortMetrics m) {
        if (left >= right) return;

        if (m != null) m.enterRecursion();
        try{
            if(right - left <= CUTOFF){
                insertionSort(array,left,right, m);
                return;
            }
            int mid = left + (right - left) / 2;
            mergeSort(array, temp, left, mid, m);
            mergeSort(array, temp, mid + 1, right, m);
            merge(array, temp, left,mid, right, m);
        }finally{
            if(m != null) m.exitRecursion();
        }
    }

    private static void merge(int[] array, int[] temp, int left, int mid, int right, SortMetrics m) {
        for (int i = left; i <= right; i++) {
            temp[i] = array[i];
            if (m != null) m.incrementArrayWrites();
        }
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if( m != null) m.incrementComparisons();
            if (temp[i] <= temp[j]) {
                array[k++] = temp[i++];
                if (m != null) m.incrementArrayWrites();
            }else{
                array[k++] = temp[j++];
                if (m != null) m.incrementArrayWrites();
            }
        }
        while (i <= mid) {
            array[k++] = temp[i++];
            if (m != null) m.incrementArrayWrites();
        }
    }

    private static void insertionSort(int[] array, int left, int right, SortMetrics m) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            if (m != null) m.incrementArrayWrites();
            int j = i - 1;
            while (j >= left) {
                if (m != null) m.incrementComparisons();
                if (array[j] > key) {
                    array[j + 1] = array[j];
                    if (m != null) m.incrementArrayWrites();
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = key;
            if (m != null) m.incrementArrayWrites();
        }

    }
}

