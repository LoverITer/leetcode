package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 剑指Offer 04. 二维数组的查找
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/20 23:09
 */
public class 二维数组查找 {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int rows, cols;
        if (matrix == null || (rows = matrix.length) == 0 || (cols = matrix[0].length) == 0) {
            return false;
        }
        boolean find = false;
        int row = 0;
        int col = cols - 1;
        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                find = true;
                break;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                col--;
            }

        }

        return find;
    }



    public static void main(String[] args) {

    }

}
