public class SortingBenchmark {
    public static void main(String[] args) {
        int[] sizes = {10000, 50000, 100000, 500000, 1_000_000, 10_000_000};
        for (int size : sizes) {
            System.out.println("Sorting with size: " + size);
            int[] arr = ArrayGenerator.generateRandomArray(size);

            long startTime = System.currentTimeMillis();
            QuickSortSequential.quickSort(arr.clone(), 0, arr.length - 1);
            long endTime = System.currentTimeMillis();
            System.out.println("QuickSortSequential: " + (endTime - startTime) + "ms");
//            for (int arrayVal :
//                    arr) {
//                System.out.println(arrayVal);
//            }

            startTime = System.currentTimeMillis();
            QuickSortParallel.quickSort(arr.clone());
            endTime = System.currentTimeMillis();
            System.out.println("QuickSortParallel: " + (endTime - startTime) + "ms");


            startTime = System.currentTimeMillis();
            MergeSortSequential.mergeSort(arr.clone(), 0, arr.length - 1);
            endTime = System.currentTimeMillis();
            System.out.println("MergeSortSequential: " + (endTime - startTime) + "ms");

            startTime = System.currentTimeMillis();
            MergeSortParallel.mergeSort(arr.clone(), 0, arr.length - 1);
            endTime = System.currentTimeMillis();
            System.out.println("MergeSortParallel: " + (endTime - startTime) + "ms\n");
        }
    }
}
