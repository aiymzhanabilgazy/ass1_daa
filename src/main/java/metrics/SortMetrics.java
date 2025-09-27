package metrics;

public class SortMetrics {
    private long comparisons = 0;
    private long arrayWrites = 0;
    private long allocations = 0;
    private int currentDepth = 0;
    private int maxDepth = 0;
    private long startTime = 0;
    private long endTime = 0;

    public void startTimer() { startTime = System.nanoTime(); }
    public void  stopTimer() { endTime = System.nanoTime(); }
    public long elapsedNanos(){ return endTime - startTime; }

    public void incrementComparisons() { comparisons++; }
    public void incrementArrayWrites() { arrayWrites++; }
    public void incrementAllocations() { allocations++; }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }
    public void exitRecursion() {
        currentDepth--;
        if (currentDepth < 0) currentDepth = 0;
    }
    public void reset() {
        comparisons = 0;
        arrayWrites = 0;
        allocations = 0;
        currentDepth = 0;
        maxDepth = 0;
        startTime = 0;
        endTime = 0;
    }
    public long getComparisons() { return comparisons; } //getters
    public long getArrayWrites() { return arrayWrites; }
    public long getAllocations() { return allocations; }
    public int getMaxDepth() { return maxDepth; }
    public long getStartTime() { return startTime; }
    public long getEndTime() { return endTime; }

    public static String csvHeader() {
        return "algorithm,n,trial,time_ns,comparisons,array_writes,allocations,maxRecDepth";
    }
    public String toCsv(String algorithm, int n, int trial) {
        return String.join(",",
                algorithm,
                Integer.toString(n),
                Integer.toString(trial),
                Long.toString(elapsedNanos()),
                Long.toString(comparisons),
                Long.toString(arrayWrites),
                Long.toString(allocations),
                Integer.toString(maxDepth)
        );
    }
    @Override
    public String toString() {
        return "Time(ns)=" + elapsedNanos()
                + ", comparisons=" + comparisons
                + ", arrayWrites=" + arrayWrites
                + ", allocations=" + allocations
                + ", maxDepth=" + maxDepth;
    }

}
