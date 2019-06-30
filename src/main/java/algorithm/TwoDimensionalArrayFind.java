package algorithm;

import org.junit.Test;

/**
 * @author lihui
 * @version 1.0
 * @ClassName TwoDimensionalArrayFind
 * @Description :
 * 在一个二维数组中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * @Date 2019/6/30 22:03
 */
public class TwoDimensionalArrayFind {
    /**
     * @return boolean
     * @Description 遍历查找时间复杂度O(mn)
     * @Param [target, array]
     * @Date 2019/6/30 22:08
     **/
    public boolean find1(int target, int[][] array) {
        if ((array == null || array.length == 0) || (array.length == 1 && array[0].length == 0))
            return false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == target)
                    return true;
            }
        }
        return false;
    }

    /**
     * @return boolean
     * @throws
     * @Description 利用二维数组由上到下，由左到右递增的规律，
     * 那么选取左下角或者右上角的元素a[row][col]与target进行比较，
     * 要查找的数值如果比右上角的值大，那么它将大于整个行；row++
     * 要查找的数值比如果右上角的值小，那么它将小于整个列。col--
     * 时间复杂度m+n
     * @Param [target, array]
     * @Date 2019/6/30 22:27
     **/
    public boolean find2(int target, int[][] array) {
        if ((array == null || array.length == 0) || (array.length == 1 && array[0].length == 0))
            return false;
        int row = 0;
        int col = array[0].length - 1;
        while (col >= 0 && row <= array.length - 1) {
            if (target > array[row][col]) {
                row++;
            } else if (target < array[row][col]) {
                col--;
            } else {
                System.out.println(row);
                System.out.println(col);
                return true;
            }
        }
        return false;
    }

    @Test
    public void testFind1() {
        int[][] arr = new int[][]{{1, 2, 3, 4, 5}, {2, 4, 7, 8, 10}};
        System.out.println(find1(10, arr));
    }

    @Test
    public void testFind2() {
        int[][] arr = new int[][]{{1, 2, 3, 4, 5}, {2, 4, 7, 8, 10}};
        System.out.println(find2(4, arr));
    }
}
