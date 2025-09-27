package algorithms;

import metrics.SortMetrics;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MergeSortMetricsTest{
    private static boolean isSorted(int[] array){
        for(int i = 1; i < array.length; i++){
            if(array[i-1] > array[i]) return false;
        }
        return true;
    }
    @Test
    void runAndCollectMetrics() throws IOException {
        int[] sizes = {100, 1000, 5000, 10000};
        int trials = 3;
        Path out = Path.of("merge_metrics.csv");

        try (BufferedWriter bw = Files.newBufferedWriter(out)) {
            bw.write(SortMetrics.csvHeader());
            bw.newLine();

            Random rnd = new Random(12345);

            for(int n: sizes){
                for(int t = 0; t < trials; t++){
                    int[] array = rnd.ints(n, -n, n).toArray();
                    int[] copy = Arrays.copyOf(array, array.length);

                    SortMetrics m = new SortMetrics();
                    MergeSort.sort(array, m);

                    Arrays.sort(copy);
                    assertArrayEquals(copy, array);
                    assertTrue(isSorted(array));


                    bw.write(m.toCsv("MergeSort", n, t));
                    bw.newLine();

                    System.out.println("n=" + n + ", trial=" + t + ", =" + m);
                }
            }
        }
        System.out.println("Metrics written to" + out.toAbsolutePath());
    }
}

