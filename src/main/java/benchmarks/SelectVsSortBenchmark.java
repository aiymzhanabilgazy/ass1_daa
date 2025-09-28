package benchmarks;

import algorithms.DeterministicSelect; // или DeterministicSelectWithMetrics
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class SelectVsSortBenchmark {

    @Param({"1000","5000","10000"})
    public int n;

    private int[] arr;
    private Random rnd = new Random(12345);

    @Setup(Level.Invocation)
    public void setup() {
        arr = rnd.ints(n, 0, n*10).toArray();
    }

    @Benchmark
    public int deterministicSelect() {
        int[] copy = Arrays.copyOf(arr, arr.length);
        int k = n/2;
        return DeterministicSelect.select(copy, k);
    }

    @Benchmark
    public int sortThenIndex() {
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        return copy[n/2];
    }
}

