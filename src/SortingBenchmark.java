public class SortingBenchmark {
    public static void main(String[] args) {
        int[] sizes = {10000, 50000, 100000, 500000, 1_000_000, 2_000_000};
        for (int size : sizes) {
            System.out.println("Sorting with size: " + size);
            int[] arr = ArrayGenerator.generateRandomArray(size);

            //QuickSort Benchmark
            long startTime = System.currentTimeMillis();
            QuickSortSequential.quickSort(arr.clone(), 0, arr.length - 1);
            long endTime = System.currentTimeMillis();
            long quickSequentialTime = (endTime - startTime);
            System.out.println("QuickSortSequential: " + (endTime - startTime) + "ms");

            startTime = System.currentTimeMillis();
            QuickSortParallel.quickSort(arr.clone());
            endTime = System.currentTimeMillis();
            long quickParallelTime = (endTime - startTime);
            System.out.println("QuickSortParallel: " + (endTime - startTime) + "ms");
            System.out.println("QuickSort SpeedUp of: " + ((double)quickSequentialTime / (double)quickParallelTime) + "\n");

            //MergeSort Benchmark
            startTime = System.currentTimeMillis();
            MergeSortSequential.mergeSort(arr.clone(), 0, arr.length - 1);
            endTime = System.currentTimeMillis();
            long mergeSequentialTime = (endTime - startTime);
            System.out.println("MergeSortSequential: " + mergeSequentialTime + "ms");

            startTime = System.currentTimeMillis();
            MergeSortParallel.mergeSort(arr.clone(), 0, arr.length - 1);
            endTime = System.currentTimeMillis();
            long mergeParallelTime = (endTime - startTime);
            System.out.println("MergeSortParallel: " + (endTime - startTime) + "ms");
            System.out.println("MergeSort SpeedUp of: " + ((double)mergeSequentialTime / (double)mergeParallelTime) + "\n");
        }
    }
}
