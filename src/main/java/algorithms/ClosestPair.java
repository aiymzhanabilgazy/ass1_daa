package algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {


    public static class Point {
        public final double x;
        public final double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }


    public static double closestPair(Point[] points) {
        Point[] pointsSortedByX = points.clone();
        Arrays.sort(pointsSortedByX, Comparator.comparingDouble(p -> p.x));

        Point[] aux = new Point[points.length];
        return closestPairRecursive(pointsSortedByX, aux, 0, points.length - 1);
    }

    private static double closestPairRecursive(Point[] points, Point[] aux, int left, int right) {
        if (right - left <= 3) {
            return bruteForce(points, left, right);
        }

        int mid = (left + right) / 2;
        double midX = points[mid].x;

        double dLeft = closestPairRecursive(points, aux, left, mid);
        double dRight = closestPairRecursive(points, aux, mid + 1, right);
        double d = Math.min(dLeft, dRight);


        int stripSize = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midX) < d) {
                aux[stripSize++] = points[i];
            }
        }


        Arrays.sort(aux, 0, stripSize, Comparator.comparingDouble(p -> p.y));


        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && (aux[j].y - aux[i].y) < d; j++) {
                d = Math.min(d, distance(aux[i], aux[j]));
            }
        }

        return d;
    }

    private static double bruteForce(Point[] points, int left, int right) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                min = Math.min(min, distance(points[i], points[j]));
            }
        }
        return min;
    }

    private static double distance(Point a, Point b) {
        return Math.hypot(a.x - b.x, a.y - b.y);
    }
}
