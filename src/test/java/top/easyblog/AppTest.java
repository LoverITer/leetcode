package top.easyblog;

import org.junit.Test;
import top.easyblog.array.ArrayUtils;
import top.easyblog.search.SearchAlgorithm;
import top.easyblog.sort.DirectInsertSort;
import top.easyblog.string.StringUtils;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testForArrayUtils(){
        ArrayUtils arrayUtils = new ArrayUtils();
        int[] nums = {1, 2, 5, 7, 12, 15};
        //System.out.println(Arrays.toString(arrayUtils.nextBiggerPermutationNum(nums)));
        //System.out.println(arrayUtils.dominantIndex(nums));
        //int[][] a = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 }};
        //arrayUtils.spiralOrder(a).forEach(obj->System.out.print(obj+" "));
        System.out.println(Arrays.toString(arrayUtils.twoSumEqualsTarget(nums, 8)));
    }

    @Test
    public void shouldAnswerWithTrue() {
        int[] array = {45,3,1,7,23,44};
        /*new BubbleSort().sort(array);
        System.out.println(Arrays.toString(array));*/
        DirectInsertSort insertSort = new DirectInsertSort();
        insertSort.binarySort(array);
        System.out.println(Arrays.toString(array));

    }

    @Test
    public void testForStringUtils() {
        StringUtils stringUtils = new StringUtils();
        String str = "apple is nice";
        //System.out.println(stringUtils.compressString(str));
        //System.out.println(stringUtils.isEmpty(str));
        System.out.println(stringUtils.reverseString(str));
    }


    @Test
    public void testForSearchUtils() {
        SearchAlgorithm searchAlgorithm = new SearchAlgorithm();
        int[] nums = new int[]{3,5,1};
        System.out.println(searchAlgorithm.search(nums, 3));
    }
}
