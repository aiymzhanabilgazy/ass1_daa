package algorithms;

import metrics.SortMetrics;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPairWithMetrics {

    public static double closestPair(Point[] points, SortMetrics metrics) {
        metrics.startTimer();
        metrics.enterRecursion();
        Point[] pointsSortedByX = points.clone();
        Arrays.sort(pointsSortedByX, Comparator.comparingDouble(p -> p.x));
        metrics.incrementComparisons();

        Point[] aux = new Point[points.length];
        double result = closestPairRecursive(pointsSortedByX, aux, 0, points.length - 1, metrics);
        metrics.exitRecursion();
        metrics.stopTimer();
        return result;
    }

    private static double closestPairRecursive(Point[] points, Point[] aux, int left, int right, SortMetrics metrics) {
        metrics.enterRecursion();

        if (right - left <= 3) {
            double res = bruteForce(points, left, right, metrics);
            metrics.exitRecursion();
            return res;
        }

        int mid = (left + right) / 2;
        double midX = points[mid].x;

        double dLeft = closestPairRecursive(points, aux, left, mid, metrics);
        double dRight = closestPairRecursive(points, aux, mid + 1, right, metrics);
        double d = Math.min(dLeft, dRight);

        int stripSize = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midX) < d) {
                aux[stripSize++] = points[i];
            }
        }

        Arrays.sort(aux, 0, stripSize, Comparator.comparingDouble(p -> p.y));
        metrics.incrementComparisons();

        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && (aux[j].y - aux[i].y) < d; j++) {
                d = Math.min(d, distance(aux[i], aux[j], metrics));
            }
        }

        metrics.exitRecursion();
        return d;
    }

    private static double bruteForce(Point[] points, int left, int right, SortMetrics metrics) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                metrics.incrementComparisons();
                min = Math.min(min, distance(points[i], points[j], metrics));
            }
        }
        return min;
    }

    private static double distance(Point a, Point b, SortMetrics metrics) {
        metrics.incrementComparisons();
        return Math.hypot(a.x - b.x, a.y - b.y);
    }

    public static class Point {
        public final double x;
        public final double y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }
}

