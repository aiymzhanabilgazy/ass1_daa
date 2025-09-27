package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class MergeSortTest {

    @Test
    void testEmptyArray() {
        int[] array = {};
        MergeSort.sort(array);
        assertArrayEquals(new int[]{}, array);
    }
    @Test
    void testSingleElementArray() {
        int[] array = {1};
        MergeSort.sort(array);
        assertArrayEquals(new int[]{1}, array);
    }
    @Test
    void testAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        MergeSort.sort(array);
        assertArrayEquals(expected, array);
    }
    @Test
    void testReverseSortedArray() {
        int[] array = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        MergeSort.sort(array);
        assertArrayEquals(expected, array);
    }
    @Test
    void testRandomArray() {
        int[] array = {3, 7, 1, 9, 2};
        int[] expected = {1, 2, 3, 7, 9};
        MergeSort.sort(array);
        assertArrayEquals(expected, array);
    }
    @Test
    void testWithDuplicates() {
        int[] array = {5, 1, 3, 3, 2, 5, 1};
        int[] expected = {1, 1, 2, 3, 3, 5, 5};
        MergeSort.sort(array);
        assertArrayEquals(expected, array);
    }

}

