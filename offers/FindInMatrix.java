package offers;

/**
 * Created by song on 17-3-18.
 */
public class FindInMatrix {
    public static void main(String[] args) {
        /*
        int row = 5, col = 4;
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int temp = (int)(Math.random() * 10);
                matrix[i][j] = temp;
                System.out.print(temp);
            }
        }
        */
        int[][] matrix = {
                {1,2,8,9},
                {2,4,9,12},
                {4,7,10,13},
                {6,8,11,15}
        };
        int num = 13;
        boolean result = findNumInMatrix(matrix, num, true);
        System.out.println("");
        boolean result2 = findNumInMatrix(matrix, num, false);
        System.out.println(result);
        System.out.println(result2);
    }

    public static boolean findNumInMatrix(int[][] mat, int num, boolean onlyFindOnce) {
        if (mat == null | mat.length == 0 | mat[0].length == 0) {
            System.out.println("Please select the legal matrix!");
            return false;
        }
        int row = mat.length;
        int col = mat[0].length;
        boolean isFound = false;
        for (int i = row - 1, j = 0; i >= 0 | j <= col - 1;) {
            if (i == -1 | j == col) {
                break;
            }
            if (mat[i][j] < num) {
                j++;
            } else if (mat[i][j] > num) {
                i--;
            } else {
                isFound = true;
                System.out.println("[" + i + "," + j + "]");
                if (onlyFindOnce) {
                    break;
                }
                i--;
                j++;
            }
        }
        return isFound;
    }
}
