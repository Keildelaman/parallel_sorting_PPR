import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class
QuickSortParallel {

    private static final int THRESHOLD = 1000;

    public static void quickSort(int[] arr) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new QuickSortTask(arr, 0, arr.length - 1));
    }

    private static int partition(int[] arr, int left, int right) {
        int pivotValue = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivotValue) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
                    quickSortSequential(arr, left, right);
                } else {
                    int pivotIndex = partition(arr, left, right);
                    QuickSortTask leftTask = new QuickSortTask(arr, left, pivotIndex - 1);
                    QuickSortTask rightTask = new QuickSortTask(arr, pivotIndex + 1, right);
                    invokeAll(leftTask, rightTask);
                }
            }
        }

        private void quickSortSequential(int[] arr, int left, int right) {
            if (left < right) {
                int pivotIndex = partition(arr, left, right);
                quickSortSequential(arr, left, pivotIndex - 1);
                quickSortSequential(arr, pivotIndex + 1, right);
            }
        }

        private int partition(int[] arr, int left, int right) {
            int pivotValue = arr[right];
            int i = left - 1;
            for (int j = left; j < right; j++) {
                if (arr[j] <= pivotValue) {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, right);
            return i + 1;
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
