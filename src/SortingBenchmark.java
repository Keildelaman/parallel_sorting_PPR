public class SortingBenchmark {
    public static void main(String[] args) {
        int[] sizes = {10000, 50000, 100000, 500000, 1000000};
        for (int size : sizes) {
            int[] arr = ArrayGenerator.generateRandomArray(size);

            long startTime = System.currentTimeMillis();
            QuickSortSequential.quickSort(arr, 0, arr.length - 1);
            long endTime = System.currentTimeMillis();
            System.out.println("QuickSortSequential: " + (endTime - startTime) + "ms");

            startTime = System.currentTimeMillis();
            MergeSortSequential.mergeSort(arr, 0, arr.length - 1);
            endTime = System.currentTimeMillis();
            System.out.println("MergeSortSequential: " + (endTime - startTime) + "ms");
        }
    }
}
