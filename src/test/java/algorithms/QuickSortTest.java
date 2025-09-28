package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class QuickSortTest {

    @Test
    void testEmptyArray() {
        int[] array = {};
        QuickSort.sort(array);
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    void testSingleElementArray() {
        int[] array = {1};
        QuickSort.sort(array);
        assertArrayEquals(new int[]{1}, array);
    }

    @Test
    void testAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        QuickSort.sort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testReverseSortedArray() {
        int[] array = {5, 4, 3, 2, 1};
        QuickSort.sort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testRandomArray() {
        int[] array = {3, 7, 1, 9, 2};
        QuickSort.sort(array);
        assertArrayEquals(new int[]{1, 2, 3, 7, 9}, array);
    }

    @Test
    void testWithDuplicates() {
        int[] array = {5, 1, 3, 3, 2, 5, 1};
        QuickSort.sort(array);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3, 5, 5}, array);
    }

    @Test
    void testLargeRandomArray() {
        Random rand = new Random();
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(10000);
        }
        int[] expected = array.clone();
        java.util.Arrays.sort(expected);
        QuickSort.sort(array);
        assertArrayEquals(expected, array);
    }
    @Test
    void testRecursionDepthBound(){
        int n = 10000;
        int[] array = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt(100000);
        }
        metrics.SortMetrics m = new metrics.SortMetrics();
        int[] expected = array.clone();
        java.util.Arrays.sort(expected);
        QuickSort.sort(array, m);
        assertArrayEquals(expected, array);

        assertTrue(m.getMaxDepth() <= 2 * (int)(Math.log(n) / Math.log(2)) + 5);


    }
}
