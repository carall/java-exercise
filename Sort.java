public class Sort {
    /**
     *一些排序里通用的方法：swap
     */
    private void swap(double[] arr, int i, int j) {
        if (i == j) return;
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private <T extends Comparable<? super T>> void swap(T[] arr, int i, int j) {
        if (i == j) return;
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    /**
     * 快速排序
     * @param arr 待排序数组
     */
    public void quickSort(double[] arr) {
        quickSort(arr, 0, arr.length - 1);
        System.out.println("start sorting!");
    }
    private void quickSort(double[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int flag = partition(arr, start, end);
        quickSort(arr, start, flag);
        quickSort(arr, flag + 1, end);
    }
    private int partition(double[] arr, int start, int end) {
        //找到要比较的值
        int midIndex = (start + end) / 2;
        double mid = arr[midIndex];

        int i = start, j = end;
        swap(arr, start, midIndex);
        i++;
        while (i < j) {
            while (arr[i] <= mid && i < end) {
                i++;
            }
            while (arr[j] > mid && j > start) {
                j--;
            }
            if (i < j) {
                swap(arr, i++, j--);
            }
        }
        swap(arr, start, --i);
        return i;
    }
    /**
     * 选择排序
     * 高效的选择排序 视频：数组-07
     */
    public <T extends Comparable<? super T>> void selectSort(T[] arr) {
        int k;
        for (int i = 0; i < arr.length; i++) {
            k = i;
            for (int j = i + 1; j < arr.length; j++) {
                try {
                    if (arr[j].compareTo(arr[k]) < 0) {
                        k = j;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            swap(arr, i, k);
        }
    }
    /**
     * 冒泡排序
     * 高效的冒泡排序 视频：数组-09
     * n--
     */
    public <T extends Comparable<? super T>> void bubbleSort2(T[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                try {
                    if (arr[j].compareTo(arr[j+1]) > 0) {
                        swap(arr, j, j+1);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public <T extends Comparable<? super T>> void bubbleSort(T[] arr) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < arr.length; i++) {
                try {
                    if (arr[i].compareTo(arr[i+1]) > 0) {
                        swap(arr, i, i+1);
                        flag = true;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
