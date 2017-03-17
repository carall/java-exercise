import java.util.Arrays;

public class ArgsArraySort {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.print("请输入排序数据");
            System.exit(1);
        }
        double[] arr = new double[args.length];
        for (int i = 0; i < args.length; i++) {
            try {
                arr[i] = Double.parseDouble(args[i]);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println(Arrays.toString(arr));
        Sort s = new Sort();
        s.quickSort(arr);
//        s.selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
