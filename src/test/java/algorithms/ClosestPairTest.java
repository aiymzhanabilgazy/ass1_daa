package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    public void testSimpleCase() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(7, 7),
                new ClosestPair.Point(1, 1)
        };

        double result = ClosestPair.closestPair(points);
        assertEquals(Math.sqrt(2), result, 1e-6);
    }

    @Test
    public void testIdenticalPoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(2, 2),
                new ClosestPair.Point(2, 2),
                new ClosestPair.Point(5, 5)
        };

        double result = ClosestPair.closestPair(points);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    public void testLargeDistance() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(-100, -100),
                new ClosestPair.Point(100, 100),
                new ClosestPair.Point(50, 50),
                new ClosestPair.Point(-50, -50)
        };

        double result = ClosestPair.closestPair(points);
        assertEquals(Math.sqrt(5000), result, 1e-6);
    }
}
