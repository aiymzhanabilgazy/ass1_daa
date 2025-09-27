package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class MergeSortTest {

    @Test
    void testWithDuplicates() {
        int[] array = {5, 3, 2, 3, 8, 1, 1};
        int[] expected = {1, 1, 2, 3, 3, 5, 8};
        MergeSort.sort(array);
        assertArrayEquals(expected, array);
    }
    @Test
    void testWithEmptyArray() {
        int[] array = {};
        int[] expected = {};
        MergeSort.sort(array);
        assertArrayEquals(expected, array);
    }
    @Test
    void testSingleElement() {
        int[] array = {3};
        int[] expected = {3};
        MergeSort.sort(array);
        assertArrayEquals(expected, array);
    }
    @Test
    void testSmallArraySortedCorrectly() {
        int[] array = {7, 3, 5, 1, 2};
        int[] expected = {1, 2, 3, 5, 7};
        MergeSort.sort(array);
        assertArrayEquals(expected, array);
    }
    @Test
    void testSmallArrayWithDuplicates() {
        int[] array = {4, 2, 2, 1, 3};
        int[] expected = {1, 2, 2, 3, 4};
        MergeSort.sort(array);
        assertArrayEquals(expected, array);
    }
}

