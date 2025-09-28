package algorithms;

import algorithms.DeterministicSelectWithMetrics;
import metrics.SortMetrics;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DeterministicSelectMetricsTest {
    @Test
    public void runMetrics() throws IOException {
        int[] sizes = {100, 1000, 5000, 10000};
        Random rand = new Random();
        try (FileWriter out = new FileWriter("deterministic_select_metrics.csv")) {
            out.write(SortMetrics.csvHeader() + "\n");
            for (int n : sizes) {
                for (int trial = 0; trial < 3; trial++) {
                    int[] arr = rand.ints(n, 0, 100000).toArray();
                    int k = rand.nextInt(n);
                    SortMetrics metrics = new SortMetrics();
                    DeterministicSelectWithMetrics.select(arr.clone(), k, metrics);
                    out.write(metrics.toCsv("DeterministicSelect", n, trial) + "\n");
                }
            }
        }
    }
}

