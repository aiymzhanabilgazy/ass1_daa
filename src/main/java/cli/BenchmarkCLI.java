package cli;

import metrics.SortMetrics;
import algorithms.MergeSort;
import algorithms.QuickSort;
import algorithms.DeterministicSelectWithMetrics;
import algorithms.ClosestPairWithMetrics;
import algorithms.ClosestPairWithMetrics.Point;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class BenchmarkCLI {


    private static void usage() {
        System.out.println(
                "Usage: java -cp target/... cli.BenchmarkCLI "
                        + "[--algo <mergesort|quicksort|select|closest>] "
                        + "[--n <size>] "
                        + "[--trials <t>] "
                        + "[--seed <seed>] "
                        + "[--out <file>] "
                        + "[--k <index-for-select>]"
        );
        System.out.println(
                "Example: --algo mergesort --n 10000 --trials 5 --out merge_results.csv"
        );

    }

    public static void main(String[] args) throws IOException {

        String algo = "mergesort";
        int n = 1000;
        int trials = 3;
        long seed = System.currentTimeMillis();
        String outFile = "results.csv";
        Integer selectK = null;

        // simple arg parser
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--algo":
                case "-a":
                    algo = args[++i].toLowerCase();
                    break;
                case "--n":
                    n = Integer.parseInt(args[++i]);
                    break;
                case "--trials":
                case "-t":
                    trials = Integer.parseInt(args[++i]);
                    break;
                case "--seed":
                    seed = Long.parseLong(args[++i]);
                    break;
                case "--out":
                    outFile = args[++i];
                    break;
                case "--k":
                    selectK = Integer.parseInt(args[++i]);
                    break;
                case "--help":
                case "-h":
                default:
                    usage();
                    return;
            }
        }

        Path outPath = Path.of(outFile);
        boolean needHeader = !Files.exists(outPath) || Files.size(outPath) == 0;

        try (BufferedWriter bw = Files.newBufferedWriter(outPath,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            if (needHeader) {
                bw.write(SortMetrics.csvHeader());
                bw.newLine();
            }

            Random rnd = new Random(seed);

            for (int t = 0; t < trials; t++) {
                SortMetrics m = new SortMetrics();

                switch (algo) {
                    case "mergesort": {
                        int[] arr = rnd.ints(n, -n, n).toArray();
                        MergeSort.sort(arr, m);
                        break;
                    }
                    case "quicksort": {
                        int[] arr = rnd.ints(n, -n, n).toArray();
                        QuickSort.sort(arr, m);
                        break;
                    }
                    case "select": {
                        int[] arr = rnd.ints(n, 0, Math.max(1, n*10)).toArray();
                        int k = (selectK == null) ? rnd.nextInt(n) : selectK;
                        DeterministicSelectWithMetrics.select(arr, k, m);
                        break;
                    }
                    case "closest": {
                        Point[] pts = new Point[n];
                        for (int i = 0; i < n; i++) {
                            pts[i] = new Point(rnd.nextDouble() * 1_000, rnd.nextDouble() * 1_000);
                        }
                        ClosestPairWithMetrics.closestPair(pts, m);
                        break;
                    }
                    default:
                        System.err.println("Unknown algorithm: " + algo);
                        usage();
                        return;
                }

                String csvLine = m.toCsv(algo, n, t);
                bw.write(csvLine);
                bw.newLine();
                bw.flush();

                System.out.println(csvLine);
            }
        }

    }
}
