import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSortParallel {

    private static final int THRESHOLD = 10000;

    public static void mergeSort(int[] arr, int left, int right) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new MergeSortTask(arr, left, right));
        pool.shutdown();
    }

    private static class MergeSortTask extends RecursiveAction {
        private final int[] arr;
        private final int left;
        private final int right;

        public MergeSortTask(int[] arr, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (left < right) {
                if (right - left + 1 <= THRESHOLD) {
                    // Perform sequential sorting
                    Arrays.sort(arr, left, right + 1);
                } else {
                    int mid = (left + right) / 2;
                    MergeSortTask leftTask = new MergeSortTask(arr, left, mid);
                    MergeSortTask rightTask = new MergeSortTask(arr, mid + 1, right);
                    invokeAll(leftTask, rightTask);
                    MergeSortSequential.merge(arr, left, mid, right);
                }
            }
        }
    }
}