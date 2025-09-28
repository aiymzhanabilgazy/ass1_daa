package algorithms;

import algorithms.ClosestPairWithMetrics;
import algorithms.ClosestPairWithMetrics.Point;
import metrics.SortMetrics;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ClosestPairMetricsTest {
    @Test
    public void runMetrics() throws IOException {
        int[] sizes = {100, 1000, 5000};
        Random rand = new Random();
        try (FileWriter out = new FileWriter("closest_pair_metrics.csv")) {
            out.write(SortMetrics.csvHeader() + "\n");
            for (int n : sizes) {
                for (int trial = 0; trial < 3; trial++) {
                    Point[] points = new Point[n];
                    for (int i = 0; i < n; i++) {
                        points[i] = new Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
                    }
                    SortMetrics metrics = new SortMetrics();
                    ClosestPairWithMetrics.closestPair(points, metrics);
                    out.write(metrics.toCsv("ClosestPair", n, trial) + "\n");
                }
            }
        }
    }
}
