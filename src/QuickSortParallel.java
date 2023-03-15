import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSortParallel {

    private static final int THRESHOLD = 10000;

    public static void quickSort(int[] arr) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new QuickSortTask(arr, 0, arr.length - 1));
    }

    private static class QuickSortTask extends RecursiveAction {

        private int[] arr;
        private int left;
        private int right;

        public QuickSortTask(int[] arr, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (left < right) {
                if (right - left < THRESHOLD) {
                    QuickSortSequential.quickSort(arr, left, right);
                } else {
                    int pivotIndex =  QuickSortSequential.partition(arr, left, right);
                    QuickSortTask leftTask = new QuickSortTask(arr, left, pivotIndex - 1);
                    QuickSortTask rightTask = new QuickSortTask(arr, pivotIndex + 1, right);
                    invokeAll(leftTask, rightTask);
                }
            }
        }
    }
}
