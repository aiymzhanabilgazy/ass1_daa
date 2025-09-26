package algorithms;

import java.util.Arrays;

public class MergeSort {

    public static void sort(int[] array) {
        int[] temp = new int[array.length]; //one buffer for the entire process
        mergeSort(array, temp, 0,array.length - 1);
    }

    private static void mergeSort(int[] array, int[] temp, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSort(array, temp, left, mid);
        mergeSort(array, temp,mid + 1, right);
        merge(array, temp, left, mid, right);
    }

    private static void merge(int[] array, int[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) { //copy the current diapason to the buffer
            temp[i] = array[i];
        }
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                array[k++] = temp[i++];
            }else{
                array[k++] = temp[j++];
            }
        }
        while (i <= mid) {
            array[k++] = temp[i++];
        }
    }
}

