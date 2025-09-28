package algorithms;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DetermenisticSelectTest {
    @Test
    public void testSmallArray() {
        int[] arr = {3, 1, 2};
        assertEquals(1, DeterministicSelect.select(arr.clone(), 0));
        assertEquals(2, DeterministicSelect.select(arr.clone(), 1));
        assertEquals(3, DeterministicSelect.select(arr.clone(), 2));
    }

    @Test
    public void testOddSizeArray() {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        assertEquals(3, DeterministicSelect.select(arr.clone(), 0));
        assertEquals(4, DeterministicSelect.select(arr.clone(), 1));
        assertEquals(7, DeterministicSelect.select(arr.clone(), 3));
        assertEquals(26, DeterministicSelect.select(arr.clone(), arr.length - 1));
    }

    @Test
    public void testEvenSizeArray() {
        int[] arr = {10, 80, 30, 90, 40, 50, 70};
        assertEquals(10, DeterministicSelect.select(arr.clone(), 0));
        assertEquals(30, DeterministicSelect.select(arr.clone(), 1));
        assertEquals(40, DeterministicSelect.select(arr.clone(), 2));
        assertEquals(80, DeterministicSelect.select(arr.clone(), 5));
        assertEquals(90, DeterministicSelect.select(arr.clone(), 6));
    }

    @Test
    public void testSingleElement() {
        int[] arr = {42};
        assertEquals(42, DeterministicSelect.select(arr, 0));
    }

    @Test
    public void testInvalidInput() {
        int[] arr = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> DeterministicSelect.select(arr, -1));
        assertThrows(IllegalArgumentException.class, () -> DeterministicSelect.select(arr, 5));
        assertThrows(IllegalArgumentException.class, () -> DeterministicSelect.select(null, 0));
    }
}
